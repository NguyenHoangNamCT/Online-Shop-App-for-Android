<?php 
require_once("database.php");

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

    public function xoaSanPham($sanPhamID){
        $conn = DATABASE::connect();
        try{
            $sql = "DELETE FROM `sanpham` WHERE `id` = :sanPhamID";
            $cmd = $conn->prepare($sql);
            $cmd->bindParam(':sanPhamID', $sanPhamID);
            $cmd->execute();
            // Kiểm tra xem có bao nhiêu hàng đã bị xóa
             $rowCount = $cmd->rowCount();
            // Trả về true nếu có ít nhất một hàng được xóa, ngược lại trả về false
            return $rowCount > 0;
        }
        catch(PDOException $e){
            echo "Lỗi truy vấn ở xoaSanPham() trong model SanPham: " . $e->getMessage();
            exit();
        }
    }

    public function laySanPhamTheoId($sanPhamID){
        $conn = DATABASE::connect();
        try{
            $sql = "SELECT * FROM sanpham WHERE id = :sanPhamID";
            $cmd = $conn->prepare($sql);
            $cmd->bindParam(':sanPhamID', $sanPhamID, PDO::PARAM_INT);
            $cmd->execute();
            $result = $cmd->fetch(PDO::FETCH_ASSOC);
            
            // Kiểm tra nếu kết quả tồn tại, trả về dữ liệu, ngược lại trả về thông báo lỗi
            if ($result) {
                return $result;
            } else {
                echo "Khong_tim_thay_san_pham";
                return false;
            }
        }
        catch(PDOException $e){
            echo "Lỗi truy vấn ở laySanPhamTheoId() trong model SanPham: " . $e->getMessage();
            exit();
        }
    }

    // Lấy giá tiền và giảm giá của sản phẩm theo id
    public function layGiaTienVaGiamGiaTheoId($sanPhamId) {
        $conn = DATABASE::connect();
        try {
            // Truy vấn lấy giá tiền và giảm giá từ bảng sanpham
            $sql = "SELECT gia_tien, giam_gia FROM sanpham WHERE id = :sanPhamId";
            $cmd = $conn->prepare($sql);
            $cmd->bindParam(':sanPhamId', $sanPhamId, PDO::PARAM_INT);
            $cmd->execute();

            $result = $cmd->fetch(PDO::FETCH_ASSOC);

            return $result;
        } catch (PDOException $e) {
            // Xử lý lỗi nếu có
            echo "Lỗi truy vấn ở layGiaTienVaGiamGiaTheoId: " . $e->getMessage();
            return false;
        }
    }

    public function suaSanPham($sanPhamID, $idLoaiSanPham, $idThuongHieu, $tenSanPham, $moTa, $giaTien, $giamGia, $soLuong, $luotXem, $luotMua, $donViTinh, $hinhAnh = null){
        $conn = DATABASE::connect();
        try{
                // Tạo chuỗi SQL để cập nhật thông tin của sản phẩm
            $sql = "UPDATE `sanpham` SET 
                `id_loai_san_pham` = :idLoaiSanPham, 
                `id_thuong_hieu` = :idThuongHieu, 
                `ten_san_pham` = :tenSanPham, 
                `mo_ta` = :moTa, 
                `gia_tien` = :giaTien, 
                `giam_gia` = :giamGia, 
                `so_luong` = :soLuong, 
                `luot_xem` = :luotXem, 
                `luot_mua` = :luotMua, 
                `don_vi_tinh` = :donViTinh";

            // Nếu hình ảnh được truyền vào, bao gồm nó trong câu lệnh SQL
            if ($hinhAnh !== null) {
                $sql .= ", `hinh_anh` = :hinhAnh";
            }

            $sql .= " WHERE `id` = :sanPhamID";
    
            $cmd = $conn->prepare($sql);
            // Bind các giá trị mới vào câu lệnh SQL
            $cmd->bindParam(':idLoaiSanPham', $idLoaiSanPham);
            $cmd->bindParam(':idThuongHieu', $idThuongHieu);
            $cmd->bindParam(':tenSanPham', $tenSanPham);
            $cmd->bindParam(':moTa', $moTa);
            $cmd->bindParam(':giaTien', $giaTien);
            $cmd->bindParam(':giamGia', $giamGia);
            $cmd->bindParam(':soLuong', $soLuong);
            $cmd->bindParam(':luotXem', $luotXem);
            $cmd->bindParam(':luotMua', $luotMua);
            $cmd->bindParam(':donViTinh', $donViTinh);
            $cmd->bindParam(':sanPhamID', $sanPhamID);

            // Nếu hình ảnh được truyền vào, bind giá trị của hình ảnh
            if ($hinhAnh !== null) {
                $cmd->bindParam(':hinhAnh', $hinhAnh);
            }
    
            $result = $cmd->execute();
            
    
            // Trả về true nếu có ít nhất một hàng được cập nhật, ngược lại trả về false
            return $result;
        }
        catch(PDOException $e){
            echo "Lỗi truy vấn ở suaSanPham() trong model SanPham: " . $e->getMessage();
            exit();
        }
    }
    
}

?>