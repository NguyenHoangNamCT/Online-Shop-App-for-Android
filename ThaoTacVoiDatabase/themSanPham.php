<?php 
    require("model/sanpham.php");
    $sp = new SanPham();

    $id_loai_san_pham = $_POST['id_loai_san_pham'];
    $id_thuong_hieu = $_POST['id_thuong_hieu'];
    $ten_san_pham = $_POST['ten_san_pham'];
    $mo_ta = $_POST['mo_ta'];
    $gia_tien = $_POST['gia_tien'];
    $giam_gia = $_POST['giam_gia'];
    $so_luong = $_POST['so_luong'];
    $hinh_anh = $_POST['hinh_anh'];
    $don_vi_tinh = $_POST['don_vi_tinh'];

// Kiểm tra xem các trường đã được điền đầy đủ chưa
if (!empty($id_loai_san_pham) && !empty($id_thuong_hieu) && !empty($ten_san_pham) && !empty($mo_ta) && !empty($gia_tien) && !empty($giam_gia) && !empty($so_luong) && !empty($hinh_anh) && !empty($don_vi_tinh)) {
    $idSP = $sp->themSanPham($id_loai_san_pham, $id_thuong_hieu, $ten_san_pham, $mo_ta, $gia_tien, $giam_gia, $so_luong, $hinh_anh, $don_vi_tinh);
    if($idSP !== FALSE)
        echo "success";
    else
        echo "erro";
} 
else {
    echo "erro";
}
?>