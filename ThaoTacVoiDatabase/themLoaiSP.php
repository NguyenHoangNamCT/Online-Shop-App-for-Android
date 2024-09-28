<?php 
require("model/loaisanpham.php");

$loaiSP = new LoaiSanPham();


// Các tham số đầu vào 
$tenLoaiSanPham = $_POST['tenLoaiSP'];
$moTa = $_POST['moTa'];
$hinhAnhDangChuoiBase64 = $_POST['hinhAnh']; 
$trangThai = false;  // mặt định là hếT hàng khi mới thêm

// // Check lỗi
// // In ra giá trị của các biến
// echo "Tên loại sản phẩm: " . $tenLoaiSanPham . "<br>";
// echo "Mô tả: " . $moTa . "<br>";
// // echo "Hình ảnh: " . $hinhAnh . "<br>";
// echo "Trạng thái: " . ($trangThai ? 'Còn hàng' : 'Hết hàng') . "<br>";


//xử lý hình ảnh đã được app chuyển thành chuổi base64 ở phía sever
// bước 1 Chuyển đổi hình ảnh từ dạng chuổi base64 sang dạng file
$hinh_anh = base64_decode($hinhAnhDangChuoiBase64);

//bước 2 đặt tên tệp ảnh sao cho không bị trùng
$imageName = $tenLoaiSanPham . "_" . date("Ymd_His") . ".jpg";

//bước 3 xác định đường dẫn lưu ở sever
$target_file = "images/".$imageName;

//bước 4 tiến hành lưu tệp $hinh_anh vào đường dẫn $target_file bằng hàm có sẵn file_put_contents (hàm này sẽ trả về số byte nếu thành công ngược lại thì false)
if (file_put_contents($target_file, $hinh_anh)) {
    // Nếu lưu hình ảnh thành công, tiến hành thêm loại sản phẩm vào cơ sở dữ liệu
    $loaiSanPhamId = $loaiSP->themLoaiSP($tenLoaiSanPham, $moTa, $trangThai, $imageName);
    if ($loaiSanPhamId) {
        // Nếu thêm sản phẩm thành công thì báo thành công
        echo "success";
    } else {
        // Nếu thêm thất bại thì báo lỗi
        echo "erro";
    }
} else {
    echo "erro them hinh";
}

// // Gọi phương thức themLoaiSP
// $result = $loaiSP->themLoaiSP($tenLoaiSanPham, $moTa, $trangThai, $hinhAnh);

// // Kiểm tra kết quả trả về
// if ($result) {
//     echo "Thêm loại sản phẩm thành công với ID: " . $result;
// } else {
//     echo "Thêm loại sản phẩm thất bại!";
// }
?>