<?php 
    require("model/thuonghieu.php");

    $thuongHieu = new ThuongHieu();
    
    $idThuongHieu = $_POST['idThuongHieuCanXoa'];
    
    if($thuongHieu->xoaThuongHieu($idThuongHieu))
        echo "success";
    else
        echo $idThuongHieu;
    
?>
