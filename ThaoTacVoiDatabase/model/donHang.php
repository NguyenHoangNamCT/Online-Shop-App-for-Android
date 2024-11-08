<?php
require_once("database.php");

class DonHang {
    //lấy tt đơn hàng bằng id
    public function layDonHangTheoId($donHangId) {
        $conn = DATABASE::connect();
        try {
            $sql = "SELECT * FROM donhang WHERE id = :donHangId";
            $cmd = $conn->prepare($sql);
            $cmd->bindParam(':donHangId', $donHangId, PDO::PARAM_INT);
            $cmd->execute();
            $result = $cmd->fetch(PDO::FETCH_ASSOC);
            return $result;
        } catch (PDOException $e) {
            echo "Lỗi truy vấn ở layDonHangTheoId: " . $e->getMessage();
            return false;
        }
    }

    public function themDonHang($idNguoiDung, $ngayDat, $diaChiGiaoHang, $dienThoaiNguoiNhan, $tongTien, $tinhTrangDonHang, $daHuy) {
        $conn = DATABASE::connect();
        try {
            $sql = "INSERT INTO donhang (id_nguoi_dung, ngay_dat, dia_chi_giao_hang, dien_thoai_nguoi_nhan, tong_tien, tinh_trang_don_hang, da_huy)
                    VALUES (:idNguoiDung, :ngayDat, :diaChiGiaoHang, :dienThoaiNguoiNhan, :tongTien, :tinhTrangDonHang, :daHuy)";
            $cmd = $conn->prepare($sql);
            $cmd->bindParam(':idNguoiDung', $idNguoiDung, PDO::PARAM_INT);
            $cmd->bindParam(':ngayDat', $ngayDat);
            $cmd->bindParam(':diaChiGiaoHang', $diaChiGiaoHang);
            $cmd->bindParam(':dienThoaiNguoiNhan', $dienThoaiNguoiNhan);
            $cmd->bindParam(':tongTien', $tongTien);
            $cmd->bindParam(':tinhTrangDonHang', $tinhTrangDonHang, PDO::PARAM_INT);
            $cmd->bindParam(':daHuy', $daHuy, PDO::PARAM_INT);
            $cmd->execute();
            
            // Lấy ID của bản ghi vừa thêm
            return $conn->lastInsertId(); //lệNh if của php sẽ nhận false khi là 0 không null
        } catch (PDOException $e) {
            echo "Lỗi truy vấn ở themDonHang: " . $e->getMessage();
            return false;
        }
    }
}

?>
