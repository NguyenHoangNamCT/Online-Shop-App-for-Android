<?php 
require("database.php");

class GIOHANG {
    public function layGioHang($idNguoiDung) {
        $conn = DATABASE::connect();
        try {
            // Câu lệnh SQL để lấy giỏ hàng dựa trên id người dùng
            $sql = "SELECT * FROM giohang WHERE nguoi_dung_id = :idNguoiDung";
            $cmd = $conn->prepare($sql);

            $cmd->bindParam(':idNguoiDung', $idNguoiDung, PDO::PARAM_INT);
            $cmd->execute();
            $result = $cmd->fetchAll(PDO::FETCH_ASSOC); // Sử dụng PDO::FETCH_ASSOC để lấy kết quả dưới dạng mảng kết hợp
            return $result;
        } catch (PDOException $e) {
            echo "Lỗi truy vấn ở layGioHang: " . $e->getMessage();
            exit();
        }
    }
}
?>
