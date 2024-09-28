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


    public function layFullDanhSachLoaiSanPham(){
        $conn = DATABASE::connect();
        try{
            $sql = "SELECT * FROM loaisanpham";
            $cmd = $conn->prepare($sql);
            $cmd->execute();
            $result = $cmd->fetchAll();
            return $result;
        }
        catch(PDOException $e){
            echo "Lỗi truy vấn ở layFullDanhSachLoaiSanPham: " . $e->getMessage();
            exit();
        }
    }

    public function themLoaiSP($tenLoaiSanPham, $moTa, $trangThai, $hinhAnh) {
        $conn = DATABASE::connect();  // Kết nối cơ sở dữ liệu
        try {
            // Câu lệnh SQL để chèn dữ liệu vào bảng loaisanpham
            $sql = "INSERT INTO loaisanpham (ten_loai_san_pham, mo_ta, trang_thai, hinh_anh) 
                    VALUES (:tenLoaiSanPham, :moTa, :trangThai, :hinhAnh)";
            
            // Chuẩn bị câu truy vấn
            $cmd = $conn->prepare($sql);
            
            // Gán giá trị cho các tham số
            $cmd->bindParam(':tenLoaiSanPham', $tenLoaiSanPham);
            $cmd->bindParam(':moTa', $moTa);
            $cmd->bindParam(':trangThai', $trangThai, PDO::PARAM_BOOL);  // Trang thái kiểu boolean
            $cmd->bindParam(':hinhAnh', $hinhAnh);
    
            // Thực thi câu lệnh
            $cmd->execute();
            
            // Kiểm tra xem có bản ghi nào bị ảnh hưởng không
            if ($cmd->rowCount() > 0) {
                return $conn->lastInsertId(); // Nếu truy vấn thành công, trả về ID của loại sản phẩm vừa thêm
            } else {
                return false; // Ngược lại, trả về false
            }
        } catch (PDOException $e) {
            // Xử lý lỗi nếu có
            echo "Lỗi truy vấn ở themLoaiSP: " . $e->getMessage();
            exit();
        }
    }
    
    
}

?>