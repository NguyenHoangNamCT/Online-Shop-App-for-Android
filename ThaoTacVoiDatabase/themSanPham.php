<?php 
    require("model/sanpham.php");

// Xử lý yêu cầu từ ứng dụng Android
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Lấy dữ liệu từ ứng dụng Android
    $ten_san_pham = $_POST['ten_san_pham'];
    $mo_ta = $_POST['mo_ta'];
    $gia_tien = $_POST['gia_tien'];
    $giam_gia = $_POST['giam_gia'];
    $so_luong = $_POST['so_luong'];
    $hinh_anh_base64 = $_POST['hinh_anh'];
    $don_vi_tinh = $_POST['don_vi_tinh'];
    //2 dòng này không cần nhưng lười xoá :D
    $loai_san_pham = $_POST['loai_san_pham'];
    $thuong_hieu = $_POST['thuong_hieu'];
    $id_loai_san_pham = $_POST["id_loai_san_pham"];
    $id_thuong_hieu = $_POST["id_thuong_hieu"]; 
    

    // Chuyển đổi hình ảnh từ dạng base64 sang dạng file
    $hinh_anh = base64_decode($hinh_anh_base64);

    // Lưu hình ảnh vào thư mục trên server
    // basename tức là lấy tên tệp mà không kèm đường dẫn, trong trường hợp này không cần thiết
    //dòng này dùng nối chuổi bình thường cũng được không cần dùng base name cũng không sao
    $imageName = basename($ten_san_pham . "_" . date("Ymd_His") . ".jpg");
    $target_file = "images/".$imageName;
    if (file_put_contents($target_file, $hinh_anh)) {
        // Nếu lưu hình ảnh thành công, tiến hành thêm sản phẩm vào cơ sở dữ liệu
        $sanPhamModel = new SanPham();
        $sanPhamId = $sanPhamModel->themSanPham($id_loai_san_pham, $id_thuong_hieu, $ten_san_pham, $mo_ta, $gia_tien, $giam_gia, $so_luong, $imageName, $don_vi_tinh);
        if ($sanPhamId) {
            // Nếu thêm sản phẩm thành công thì báo thành công
            echo "success";
        } else {
            // Nếu thêm thất bại thì báo lỗi
            echo "erro";
        }
    } else {
        echo "erro them hinh";
    }
} 
else {
    // Nếu yêu cầu không phải là POST, trả về thông báo lỗi
    echo "erro from app";
}

?>