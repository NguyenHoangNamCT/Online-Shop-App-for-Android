<?php 
class ThuongHieu {
    // Phương thức để lấy danh sách thương hiệu từ cơ sở dữ liệu
    public function layDanhSachThuongHieu(){
        $conn = DATABASE::connect();
        try{
            $sql = "SELECT id, TenThuongHieu FROM thuonghieu";
            $cmd = $conn->prepare($sql);
            $cmd->execute();
            $result = $cmd->fetchAll();
            return $result;
        }
        catch(PDOException $e){
            echo "Lỗi truy vấn ở layDanhSachThuongHieu: " . $e->getMessage();
            exit();
        }
    }


}


?>