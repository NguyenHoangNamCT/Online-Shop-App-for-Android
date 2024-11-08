<?php
require_once("database.php");

class ChiTietDonHang {

    public function themChiTietDonHang($idDonHang, $idSanPham, $soLuong, $donGia) {
        $conn = DATABASE::connect();
        try {
            $sql = "INSERT INTO chitietdonhang (id_don_hang, id_san_pham, so_luong, don_gia) 
                    VALUES (:idDonHang, :idSanPham, :soLuong, :donGia)";
            $cmd = $conn->prepare($sql);
            $cmd->bindParam(':idDonHang', $idDonHang, PDO::PARAM_INT);
            $cmd->bindParam(':idSanPham', $idSanPham, PDO::PARAM_INT);
            $cmd->bindParam(':soLuong', $soLuong, PDO::PARAM_INT);
            $cmd->bindParam(':donGia', $donGia);

            $cmd->execute();
            return true; // Thêm thành công
        } catch (PDOException $e) {
            echo "Lỗi truy vấn ở themChiTietDonHang: " . $e->getMessage();
            return false;
        }
    }
}

?>
