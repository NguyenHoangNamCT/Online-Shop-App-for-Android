<?php 
//     require("model/sanpham.php");
//     $sp = new SanPham();

//     $id_loai_san_pham = $_POST['id_loai_san_pham'];
//     $id_thuong_hieu = $_POST['id_thuong_hieu'];
//     $ten_san_pham = $_POST['ten_san_pham'];
//     $mo_ta = $_POST['mo_ta'];
//     $gia_tien = $_POST['gia_tien'];
//     $giam_gia = $_POST['giam_gia'];
//     $so_luong = $_POST['so_luong'];
//     $hinh_anh = $_POST['hinh_anh'];
//     $don_vi_tinh = $_POST['don_vi_tinh'];

// // Kiểm tra xem các trường đã được điền đầy đủ chưa
// if (!empty($id_loai_san_pham) && !empty($id_thuong_hieu) && !empty($ten_san_pham) && !empty($mo_ta) && !empty($gia_tien) && !empty($giam_gia) && !empty($so_luong) && !empty($hinh_anh) && !empty($don_vi_tinh)) {
//     $idSP = $sp->themSanPham($id_loai_san_pham, $id_thuong_hieu, $ten_san_pham, $mo_ta, $gia_tien, $giam_gia, $so_luong, $hinh_anh, $don_vi_tinh);
//     if($idSP !== FALSE)
//         echo "success";
//     else
//         echo "erro";
// } 
// else {
//     echo "erro";
// }
?>

<?php


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
    $loai_san_pham = $_POST['loai_san_pham'];
    $thuong_hieu = $_POST['thuong_hieu'];
    $id_loai_san_pham = $_POST["id_loai_san_pham"];
    $id_thuong_hieu = $_POST["id_thuong_hieu"]; 

    // Hiển thị thông tin
    echo "Tên sản phẩm: " . $ten_san_pham . "<br>";
    echo "Mô tả: " . $mo_ta . "<br>";
    echo "Giá tiền: " . $gia_tien . "<br>";
    echo "Giảm giá: " . $giam_gia . "<br>";
    echo "Số lượng: " . $so_luong . "<br>";
    echo "Đơn vị tính: " . $don_vi_tinh . "<br>";
    echo "Loại sản phẩm: " . $loai_san_pham . "<br>";
    echo "Thương hiệu: " . $thuong_hieu . "<br>";
    echo "id_loai_san_pham: " . $id_loai_san_pham . "<br>";
    echo "id_thuong_hieu: " . $id_thuong_hieu . "<br>";
    // echo "Bố Hình ảnh của các con(Base64): " . $hinh_anh_base64 . "<br>";

    

    // // Chuyển đổi hình ảnh từ dạng base64 sang dạng file
    // $hinh_anh = base64_decode($hinh_anh_base64);

    // // Lưu hình ảnh vào thư mục trên server
    // $target_dir = "uploads/";
    // $target_file = $target_dir . basename($ten_san_pham . "_" . date("Ymd_His") . ".jpg");
    // if (file_put_contents($target_file, $hinh_anh)) {
    //     // Nếu lưu hình ảnh thành công, tiến hành thêm sản phẩm vào cơ sở dữ liệu
    //     $sanPhamModel = new SanPham();
    //     $sanPhamId = $sanPhamModel->themSanPham($loai_san_pham, $thuong_hieu, $ten_san_pham, $mo_ta, $gia_tien, $giam_gia, $so_luong, $target_file, $don_vi_tinh);
    //     if ($sanPhamId) {
    //         // Nếu thêm sản phẩm thành công, trả về ID của sản phẩm
    //         echo json_encode(array("status" => "success", "san_pham_id" => $sanPhamId));
    //     } else {
    //         // Nếu có lỗi xảy ra khi thêm sản phẩm, trả về thông báo lỗi
    //         echo json_encode(array("status" => "error", "message" => "Lỗi khi thêm sản phẩm vào cơ sở dữ liệu."));
    //     }
    // } else {
    //     // Nếu có lỗi xảy ra khi lưu hình ảnh, trả về thông báo lỗi
    //     echo json_encode(array("status" => "error", "message" => "Lỗi khi lưu hình ảnh."));
    // }
} 
else {
    // Nếu yêu cầu không phải là POST, trả về thông báo lỗi
    echo json_encode(array("status" => "error", "message" => "Yêu cầu không hợp lệ."));
}

?>