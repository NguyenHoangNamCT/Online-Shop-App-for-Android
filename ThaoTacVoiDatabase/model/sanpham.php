<?php 

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

    
}

?>