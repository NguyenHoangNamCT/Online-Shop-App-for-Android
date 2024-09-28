<?php 
    require("model/loaisanpham.php");

    $loaiSP = new LoaiSanPham();

    //lưu mảng vào 1 biến cho dễ đọc
    $mangLoaiSP = $loaiSP->layFullDanhSachLoaiSanPham();

    //in mảng ra dưới dạng json. 
    //cấu trúc mảng là 1 mảng lớn ở ngoài, và các object con bên trong, 
    //mỗi onject con là 1 mảng key - value có cả index - value muốn tiết kiệm có thể xóa
    echo json_encode($mangLoaiSP);
?>