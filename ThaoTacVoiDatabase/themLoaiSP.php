<?php 
require("model/loaisanpham.php");

$loaiSP = new LoaiSanPham();

// Các tham số đầu vào (có thể lấy từ form hoặc từ nguồn khác)
$tenLoaiSanPham = "Đồ Điện Tử_xóa đi lỗi không có hình";
$moTa = "Các sản phẩm liên quan đến đồ điện tử như máy tính, điện thoại, TV, ...";
$trangThai = true;  // Trang thái có thể là true (còn hàng) hoặc false (hết hàng)
$hinhAnh = "images/dientu.jpg";  // Đường dẫn tới hình ảnh sản phẩm

// Gọi phương thức themLoaiSP
$result = $loaiSP->themLoaiSP($tenLoaiSanPham, $moTa, $trangThai, $hinhAnh);

// Kiểm tra kết quả trả về
if ($result) {
    echo "Thêm loại sản phẩm thành công với ID: " . $result;
} else {
    echo "Thêm loại sản phẩm thất bại!";
}
?>