<?php 
require_once('model/giohang.php');
require_once('model/nguoidung.php');

// Nhận các biến từ yêu cầu
$username = isset($_POST['userName']) ? $_POST['userName'] : null;
$password = isset($_POST['password']) ? $_POST['password'] : null;
$idSanPham = isset($_POST['idSanPham']) ? $_POST['idSanPham'] : null;

// Kiểm tra các biến đã được truyền vào hay chưa (cái này là hàm ở dưới)
if (!kiemTraBien($username, $password, $idSanPham)) {
    return; // Dừng thực thi nếu có lỗi
}

// Khởi tạo đối tượng giỏ hàng và thực hiện thêm sản phẩm
$gioHang = new GioHang();
$nd = new NGUOIDUNG();

$idNguoiDung = $nd->layIdNguoiDungBangTenDangNhapVaMatKhau($username, $password);
if($idNguoiDung === null){
    echo "Nguoi_dung_khong_ton_tai_ID_tim_duoc_la_null";
    echo "
    Username: ".$username;
    echo "
    password: ".$password;
    return;
}

$result = $gioHang->themVaoGioHang($idNguoiDung, $idSanPham);

// Kiểm tra kết quả
if ($result) {
    echo "them_san_pham_vao_gio_hang_thanh_cong!";
} else {
    if($result != null)
        echo $result;
    echo "them_san_pham_vao_gio_hang_that_bai!";
}


//------------------------------ danh sách các hàm ------------------------------
function kiemTraBien($username, $password, $idSanPham) {
    if ($username === null) {
        echo "serser_khong_nhan_duoc_user_name!";
        return false; // Dừng thực thi và trả về false nếu có lỗi
    }

    if ($password === null) {
        echo "serser_khong_nhan_duoc_mat_khau!";
        return false; // Dừng thực thi và trả về false nếu có lỗi
    }

    if ($idSanPham === null) {
        echo "serser_khong_nhan_duoc_id_san_pham!";
        return false; // Dừng thực thi và trả về false nếu có lỗi
    }

    return true; // Trả về true nếu tất cả các biến hợp lệ
}



?>
