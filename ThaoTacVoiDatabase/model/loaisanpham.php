<?php 

require("database.php");

class LoaiSanPham{
    public function layDanhSachLoaiSanPham(){
        $conn = DATABASE::connect();
        try{
            $sql = "SELECT id, ten_loai_san_pham FROM loaisanpham";
            $cmd = $conn->prepare($sql);
            $cmd->execute();
            $result = $cmd->fetchAll();
            return $result;
        }
        catch(PDOException $e){
            echo "Lỗi truy vấn ở layDanhSachLoaiSanPham: " . $e->getMessage();
            exit();
        }
    }
}

?>