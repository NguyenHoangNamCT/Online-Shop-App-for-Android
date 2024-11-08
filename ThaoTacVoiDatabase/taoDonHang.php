<?php 
require_once('model/giohang.php');
require_once('model/nguoidung.php');

echo "hello";

// Nhận các biến từ yêu cầu
$username = isset($_POST['tenDangNhap']) ? $_POST['tenDangNhap'] : null;
$password = isset($_POST['matKhau']) ? $_POST['matKhau'] : null;
$gioHang = isset($_POST['gioHang']) ? $_POST['gioHang'] : null;

// Kiểm tra xem đã nhận được các biến hay chưa
if (!kiemTraBien($username, $password, $gioHang)) {
    return; // Dừng thực thi nếu có lỗi
}

// Xác thực người dùng
$nd = new NGUOIDUNG();
$idNguoiDung = $nd->layIdNguoiDungBangTenDangNhapVaMatKhau($username, $password);

if($idNguoiDung === null){
    echo "Nguoi_dung_khong_ton_tai_ID_tim_duoc_la_null";
    echo "\nUsername: ".$username;
    echo "\nPassword: ".$password;
    return;
}

// Giải mã JSON giỏ hàng và xử lý từng sản phẩm
$gioHangArray = json_decode($gioHang, true);

if ($gioHangArray === null) {
    echo "server_that_bai_trong_viec_doc_arraylist_cac_san_pham_chon_mua_tu_app";
    return;
}

$gioHang = new GIOHANG();
foreach ($gioHangArray as $sanPham) {
    $idSanPham = $sanPham['idSanPham'];
    $soLuong = $sanPham['soLuong'];
    //múa code đi
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
?>