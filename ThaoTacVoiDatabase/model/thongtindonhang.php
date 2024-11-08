<?php
require_once("database.php");

class ThongTinDonHang {

    public function themThongTinDonHang($tenKhachHang, $diaChiNguoiNhan, $soDienThoaiNguoiNhan, $ngayGiaoHang, $phiVanChuyen, $ghiChu) {
        $conn = DATABASE::connect();
        try {
            $sql = "INSERT INTO thongtindonhang (ten_khach_hang, dia_chi_nguoi_nhan, so_dien_thoai_nguoi_nhan, ngay_giao_hang, phi_van_chuyen, ghi_chu) 
                    VALUES (:tenKhachHang, :diaChiNguoiNhan, :soDienThoaiNguoiNhan, :ngayGiaoHang, :phiVanChuyen, :ghiChu)";
            $cmd = $conn->prepare($sql);
            $cmd->bindParam(':tenKhachHang', $tenKhachHang);
            $cmd->bindParam(':diaChiNguoiNhan', $diaChiNguoiNhan);
            $cmd->bindParam(':soDienThoaiNguoiNhan', $soDienThoaiNguoiNhan);
            $cmd->bindParam(':ngayGiaoHang', $ngayGiaoHang);
            $cmd->bindParam(':phiVanChuyen', $phiVanChuyen);
            $cmd->bindParam(':ghiChu', $ghiChu);

            $cmd->execute();
            return $conn->lastInsertId(); // Trả về ID của bản ghi vừa thêm
        } catch (PDOException $e) {
            echo "Lỗi truy vấn ở themThongTinDonHang: " . $e->getMessage();
            return false;
        }
    }
}

?>
