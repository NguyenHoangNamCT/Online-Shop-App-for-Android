<?php 
require_once('model/giohang.php');
require_once('model/nguoidung.php');
require_once('model/sanpham.php');
require_once('model/donHang.php');
require_once('model/chitietdonhang.php');
require_once('model/thongtindonhang.php');

$nd = new NGUOIDUNG();
$gh = new GIOHANG();
$sp = new SanPham();
$dh = new DonHang();
$ctdh = new ChiTietDonHang();
$ttdh = new ThongTinDonHang();



// Nhận các biến từ yêu cầu
$username = isset($_POST['tenDangNhap']) ? $_POST['tenDangNhap'] : null;
$password = isset($_POST['matKhau']) ? $_POST['matKhau'] : null;
$gioHang = isset($_POST['gioHang']) ? $_POST['gioHang'] : null;


// Kiểm tra xem đã nhận được các biến hay chưa
if (!kiemTraBien($username, $password, $gioHang)) {
    return; // Dừng thực thi nếu có lỗi
}

// Xác thực người dùng
$idNguoiDung = $nd->layIdNguoiDungBangTenDangNhapVaMatKhau($username, $password);

if($idNguoiDung === null){
    echo "Nguoi_dung_khong_ton_tai_ID_tim_duoc_la_null";
    echo "\nUsername: ".$username;
    echo "\nPassword: ".$password;
    return;
}

// Giải mã JSON giỏ hàng và xử lý từng sản phẩm
$sanPhamChonMuaArray = json_decode($gioHang, true);

if ($sanPhamChonMuaArray === null) {
    echo "server_that_bai_trong_viec_doc_arraylist_cac_san_pham_chon_mua_tu_app";
    return;
}


//--------- Thêm Đơn Hàng ---------
$thongTinNguoiDung = $nd->layThongTinNguoiDungBangID($idNguoiDung);

$tongTienDonHang = tinhTongTienDonHang($sanPhamChonMuaArray, $sp);

// Lấy thông tin người dùng dựa trên ID
$thongTinNguoiDung = $nd->layThongTinNguoiDungBangID($idNguoiDung);

// Khởi tạo giá trị cho các trường của đơn hàng
$ngayDat = date("Y-m-d H:i:s");
$diaChiGiaoHang = $thongTinNguoiDung['dia_chi']; // Địa chỉ giao hàng lấy từ thông tin người dùng
$dienThoaiNguoiNhan = $thongTinNguoiDung['dien_thoai']; // Số điện thoại lấy từ thông tin người dùng
$tinhTrangDonHang = 1; // Trạng thái đơn hàng (ví dụ: 1 = Đang xử lý)
$daHuy = 0; // Đơn hàng chưa hủy

// Thực hiện thêm đơn hàng vào cơ sở dữ liệu
$maDonHang = $dh->themDonHang($idNguoiDung, $ngayDat, $diaChiGiaoHang, $dienThoaiNguoiNhan, $tongTienDonHang, $tinhTrangDonHang, $daHuy);


//--------- Thêm Chi Tiết Đơn Hàng ---------
foreach ($sanPhamChonMuaArray as $sanPham){
    $idSanPham = $sanPham['idSanPham']; // Lấy id sản phẩm
    $soLuong = $sanPham['soLuong']; // Lấy số lượng sản phẩm chọn mua

    $thongTinSanPham = $sp->laySanPhamTheoId($idSanPham);
    $donGia = $thongTinSanPham['gia_tien'] * (1 - $thongTinSanPham['giam_gia']/100);
    $ctdh->themChiTietDonHang($maDonHang, $idSanPham, $soLuong, $donGia);
}

//--------- Thêm Thông Tin Đơn Hàng ---------
$ttdh->themThongTinDonHang(
    $thongTinNguoiDung['ho_ten'],
    $thongTinNguoiDung['dia_chi'],
    $thongTinNguoiDung['dien_thoai'],
    $ngayDat,
    0,
    ""
);


//--------- Xóa Giỏ Hàng ---------
foreach($sanPhamChonMuaArray as $sanPham){
    echo "" . $idNguoiDung . " và " . $sanPham['idSanPham'];
    $gh->xoaSanPhamKhoiGio($idNguoiDung, $sanPham['idSanPham']);
}

// ------------------------------ danh sách các hàm ------------------------------
function kiemTraBien($username, $password, $gioHang) {
    if ($username === null) {
        echo "serser_khong_nhan_duoc_user_name!_Khi_dat_hang";
        return false;
    }

    if ($password === null) {
        echo "serser_khong_nhan_duoc_mat_khau!_Khi_dat_hang";
        return false;
    }

    if ($gioHang === null) {
        echo "serser_khong_nhan_duoc_gio_hang!_Khi_dat_hang";
        return false;
    }

    return true;
}

// Tính tổng tiền đơn hàng
function tinhTongTienDonHang($sanPhamChonMuaArray, $sp) {
    $tongTien = 0; // Biến lưu tổng tiền

    foreach ($sanPhamChonMuaArray as $sanPham) {
        $idSanPham = $sanPham['idSanPham']; // Lấy id sản phẩm
        $soLuong = $sanPham['soLuong']; // Lấy số lượng sản phẩm trong giỏ

        // Lấy giá tiền và giảm giá của sản phẩm
        $mangKQ = $sp->layGiaTienVaGiamGiaTheoId($idSanPham);

        // Kiểm tra nếu có kết quả trả về
        if ($mangKQ) {
            $giaTien = $mangKQ['gia_tien'];    // Giá tiền của sản phẩm
            $giamGia = 1 - $mangKQ['giam_gia']/100;    // Giảm giá

            // Tính tổng tiền cho sản phẩm
            $thanhTien = $giaTien * $giamGia * $soLuong; // Bạn có thể tính giảm giá theo cách khác tùy vào yêu cầu
            $tongTien += $thanhTien;
        } else {
            // Xử lý nếu không có dữ liệu sản phẩm (có thể bỏ qua hoặc thông báo lỗi)
            echo "server_gap_loi_khi_tinh_thanh_tien_cho_san_pham_san_pham_khong_ton_tai_hoac_co_loi!";
            return false;
        }
        return $tongTien;
    }

    // Trả về tổng tiền của đơn hàng
    return $tongTien;
}



?>