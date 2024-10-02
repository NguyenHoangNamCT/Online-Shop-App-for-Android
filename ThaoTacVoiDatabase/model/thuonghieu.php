<?php

require("database.php");

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

    public function layFullDanhSachThuongHieu(){
        $conn = DATABASE::connect();
        try{
            $sql = "SELECT * FROM thuonghieu";
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

    public function themThuongHieu($tenThuongHieu, $moTaThuongHieu, $trangWebThuongHieu, $logoPath) {
        $conn = DATABASE::connect();
        try {
            // Câu lệnh SQL để thêm thương hiệu mới
            $sql = "INSERT INTO thuonghieu (TenThuongHieu, MoTa, TrangWeb, Logo) 
                    VALUES (:tenThuongHieu, :moTa, :trangWeb, :logo)";
            
            // Chuẩn bị câu lệnh
            $cmd = $conn->prepare($sql);
            
            // Gán giá trị cho các tham số
            $cmd->bindParam(':tenThuongHieu', $tenThuongHieu);
            $cmd->bindParam(':moTa', $moTaThuongHieu);
            $cmd->bindParam(':trangWeb', $trangWebThuongHieu);
            $cmd->bindParam(':logo', $logoPath);
    
            // Thực thi câu lệnh SQL
            $cmd->execute();
            
            // Kiểm tra xem có bản ghi nào bị ảnh hưởng không
            if ($cmd->rowCount() > 0) {
                return $conn->lastInsertId(); // Nếu truy vấn thành công, trả về ID của thương hiệu vừa thêm
            } else {
                return false; // Ngược lại, trả về false
            }
        } 
        catch (PDOException $e) {
            echo "Lỗi truy vấn ở themThuongHieu: " . $e->getMessage();
            return false; // Trả về false nếu có lỗi
        }
    }
    
    


}


?>