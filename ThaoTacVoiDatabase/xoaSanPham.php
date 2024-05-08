<?php 
require("model/sanpham.php");

$sp = new SanPham();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $id = $_POST['id_san_pham_tu_app'];
    if($sp->xoaSanPham($id)){
        echo "success";
    }else{
        echo "erro";
    }
}else{
    echo "erro from app";
}




?>