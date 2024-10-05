<?php 
require("model/giohang.php");



// $idNguoiDung = $_SESSION['user_id']; // Lấy ID người dùng từ phiên làm việc

$gioHang = new GIOHANG(); // Khởi tạo đối tượng

// $idNguoiDung = $_SESSION['user_id'];
$idNguoiDung = 42;

$mangGioHang = $gioHang->layGioHang($idNguoiDung); // Lấy giỏ hàng của người dùng

// Xuất giỏ hàng ra JSON
echo json_encode($mangGioHang);
?>
