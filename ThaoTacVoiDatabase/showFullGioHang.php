<?php 
require("model/giohang.php");



// $idNguoiDung = $_SESSION['user_id']; // Lấy ID người dùng từ phiên làm việc

$gioHang = new GIOHANG(); // Khởi tạo đối tượng

if (isset($_POST['userName']) && isset($_POST['password']) && !empty($_POST['userName']) && !empty($_POST['password'])) {
    $tenDangNhap = $_POST['userName'];
    $matKhau = $_POST['password'];

    // Xử lý tiếp với $tenDangNhap và $matKhau
}

// $idNguoiDung = $_SESSION['user_id'];
$idNguoiDung = 42;

$mangGioHang = $gioHang->layGioHang($idNguoiDung); // Lấy giỏ hàng của người dùng

// Xuất giỏ hàng ra JSON
echo json_encode($mangGioHang);
?>
