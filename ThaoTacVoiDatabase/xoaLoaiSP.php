<?php 
    require("model/loaisanpham.php");

    $loaiSP = new LoaiSanPham();
    
    $idLoaiSP = $_POST['idLoaiSPCanXoa'];
    
    if($loaiSP->xoaLoaiSP($idLoaiSP))
        echo "success";
    else
        echo $idLoaiSP;
    
?>