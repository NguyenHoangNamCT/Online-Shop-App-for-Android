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

    public function suaThuongHieu($idThuongHieu, $tenThuongHieu, $moTa, $trangWeb, $logo = null) {
        $conn = DATABASE::connect();
        try {
            // Tạo chuỗi SQL để cập nhật thông tin thương hiệu
            $sql = "UPDATE `thuonghieu` SET 
                `TenThuongHieu` = :tenThuongHieu, 
                `MoTa` = :moTa, 
                `TrangWeb` = :trangWeb";
            
            // Nếu logo được truyền vào hoặc không phải null, bao gồm nó trong câu lệnh SQL
            if ($logo !== null) {
                $sql .= ", `Logo` = :logo";
            }
    
            $sql .= " WHERE `id` = :idThuongHieu";
    
            $cmd = $conn->prepare($sql);
    
            // Bind các giá trị mới vào câu lệnh SQL
            $cmd->bindParam(':tenThuongHieu', $tenThuongHieu);
            $cmd->bindParam(':moTa', $moTa);
            $cmd->bindParam(':trangWeb', $trangWeb);
            $cmd->bindParam(':idThuongHieu', $idThuongHieu);
    
            // Nếu logo được truyền vào, bind giá trị của logo
            if ($logo !== null) {
                $cmd->bindParam(':logo', $logo);
            }
    
            // Thực thi câu lệnh
            $result = $cmd->execute();
    
            // Trả về true nếu có ít nhất một hàng được cập nhật, ngược lại trả về false
            return $result;
        } catch (PDOException $e) {
            echo "Lỗi truy vấn ở suaThuongHieu() trong model ThuongHieu: " . $e->getMessage();
            exit();
        }
    }
    
    
    


}


?>