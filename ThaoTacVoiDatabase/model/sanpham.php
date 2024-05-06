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

    public function layLoaiVaThuongHieuSP(){
        $conn = DATABASE::connect();
        try{
            $sql = "SELECT sp.id_loai_san_pham, sp.id_thuong_hieu, l.ten_loai_san_pham, th.TenThuongHieu 
                FROM sanpham sp
                JOIN loaisanpham l ON sp.id_loai_san_pham = l.id
                JOIN thuonghieu th ON sp.id_thuong_hieu = th.id";
            $cmd = $conn->prepare($sql);
            $cmd->execute();
            $result = $cmd->fetchAll(PDO::FETCH_ASSOC);
            return $result;
        }
        catch(PDOException $e){
            echo "Lỗi ở phương thức layLoaiVaThuongHieuSP() trong model sanpham: " . $e->getMessage();
            exit();
        }
    }
    

    public function themSanPham($id_loai_san_pham, $id_thuong_hieu, $ten_san_pham, $mo_ta, $gia_tien, $giam_gia, $so_luong, $hinh_anh, $don_vi_tinh){
        $conn = DATABASE::connect();
        try{
            $sql = "INSERT INTO `sanpham` (`id_loai_san_pham`, `id_thuong_hieu`, `ten_san_pham`, `mo_ta`, `gia_tien`, `giam_gia`, `so_luong`, `hinh_anh`, `don_vi_tinh`) VALUES (:id_loai_san_pham, :id_thuong_hieu, :ten_san_pham, :mo_ta, :gia_tien, :giam_gia, :so_luong, :hinh_anh, :don_vi_tinh)";
            $cmd = $conn->prepare($sql);
            $cmd->bindParam(':id_loai_san_pham', $id_loai_san_pham);
            $cmd->bindParam(':id_thuong_hieu', $id_thuong_hieu);
            $cmd->bindParam(':ten_san_pham', $ten_san_pham);
            $cmd->bindParam(':mo_ta', $mo_ta);
            $cmd->bindParam(':gia_tien', $gia_tien);
            $cmd->bindParam(':giam_gia', $giam_gia);
            $cmd->bindParam(':so_luong', $so_luong);
            $cmd->bindParam(':hinh_anh', $hinh_anh);
            $cmd->bindParam(':don_vi_tinh', $don_vi_tinh);
            $cmd->execute();
            return $conn->lastInsertId(); // Hoặc bạn có thể trả về ID của bản ghi mới được thêm vào nếu cần
        }
        catch(PDOException $e){
            echo "Lỗi truy vấn ở themSanPham() trong model SanPham: " . $e->getMessage();
            exit();
        }
    }
    
}

?>