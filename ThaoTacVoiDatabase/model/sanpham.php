<?php 

require("database.php");

class SanPham{
    public function layDanhSachSanPham(){
        $conn = DATABASE::connect();
        try{
            $sql = "SELECT * FROM sanpham";
            $cmd = $conn->prepare($sql);
            $cmd->execute();
            $result = $cmd->fetchAll();
            return $result;
        }
        catch(PDOException $e){
            echo "Lỗi truy vấn ở layDanhSachSanPham: " . $e->getMessage();
            exit();
        }
    }

    public function layTatCaHinhAnhSanPham(){
        $conn = DATABASE::connect();
        try{
            $sql = "SELECT hinh_anh FROM sanpham";
            $cmd = $conn->prepare($sql);
            $cmd->execute();
            $result = $cmd->fetchAll();
            return $result;
        }
        catch(PDOException $e){
            echo "Lỗi truy vấn ở layHinhAnhSanPham() trong model SanPham: " . $e->getMessage();
            exit();
        }
    }
}

?>