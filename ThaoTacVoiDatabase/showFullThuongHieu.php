<?php 
    require("model/thuonghieu.php");

    $thuonghieu = new ThuongHieu();

    //lưu mảng vào 1 biến cho dễ đọc
    $mangThuongHieu = $thuonghieu->layFullDanhSachThuongHieu();

    
    echo json_encode($mangThuongHieu);
?>