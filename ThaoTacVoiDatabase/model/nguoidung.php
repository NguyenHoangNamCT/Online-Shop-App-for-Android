<?php
require_once("database.php");

class NGUOIDUNG {
    // Hàm kiểm tra người dùng hợp lệ
    public function kiemTraNguoiDungHopLe($tendangnhap, $matkhau) {
        $db = DATABASE::connect(); // Kết nối đến cơ sở dữ liệu
        try {
            $sql = "SELECT * FROM nguoidung WHERE ten_dang_nhap = :tendangnhap AND mat_khau = :matkhau AND trang_thai = 1"; // Chuẩn bị câu lệnh SQL để kiểm tra người dùng
            $cmd = $db->prepare($sql); // Chuẩn bị câu lệnh SQL để thực thi
            $cmd->bindValue(":tendangnhap", $tendangnhap); // Gán giá trị biến vào tham số của câu lệnh SQL
            $cmd->bindValue(":matkhau", md5($matkhau)); // Gán giá trị biến vào tham số của câu lệnh SQL và mã hóa mật khẩu bằng MD5
            $cmd->execute(); // Thực thi câu lệnh SQL
            $valid = ($cmd->rowCount() == 1); // Kiểm tra xem người dùng có hợp lệ hay không
            // if ($valid) { // Nếu người dùng hợp lệ
            //     // $user = $cmd->fetch(PDO::FETCH_ASSOC); // Lấy thông tin của người dùng
            //     // Tạo token phiên
            //     // $this->taoTokenPhien($user['id']); // Gọi phương thức để tạo token phiên
            // }
            $cmd->closeCursor(); // Đóng con trỏ của câu lệnh SQL
            return $valid; // Trả về kết quả xác nhận hợp lệ của người dùng
        } catch (PDOException $e) { // Xử lý ngoại lệ nếu có lỗi khi thực thi câu lệnh SQL
            $error_message = $e->getMessage(); // Lấy thông báo lỗi
            echo "<p>Lỗi truy vấn ở kiemtranguoidunghople: $error_message</p>"; // Xuất thông báo lỗi
            exit(); // Thoát khỏi chương trình
        }
    }

    // Hàm kiểm tra người dùng hợp lệ: // dữ liệu đầu vào là tk mk đã mã hóa md5
    public function kiemTraNguoiDungHopLeDaMaHoa($tendangnhap, $matkhau) {
        $db = DATABASE::connect(); // Kết nối đến cơ sở dữ liệu
        try {
            $sql = "SELECT * FROM nguoidung WHERE ten_dang_nhap = :tendangnhap AND mat_khau = :matkhau AND trang_thai = 1"; // Chuẩn bị câu lệnh SQL để kiểm tra người dùng
            $cmd = $db->prepare($sql); // Chuẩn bị câu lệnh SQL để thực thi
            $cmd->bindValue(":tendangnhap", $tendangnhap); // Gán giá trị biến vào tham số của câu lệnh SQL
            $cmd->bindValue(":matkhau", $matkhau); // Gán giá trị biến vào tham số của câu lệnh SQL và mã hóa mật khẩu bằng MD5
            $cmd->execute(); // Thực thi câu lệnh SQL
            $valid = ($cmd->rowCount() == 1); // Kiểm tra xem người dùng có hợp lệ hay không
            // if ($valid) { // Nếu người dùng hợp lệ
            //     // $user = $cmd->fetch(PDO::FETCH_ASSOC); // Lấy thông tin của người dùng
            //     // Tạo token phiên
            //     // $this->taoTokenPhien($user['id']); // Gọi phương thức để tạo token phiên
            // }
            return $valid; // Trả về kết quả xác nhận hợp lệ của người dùng
        } catch (PDOException $e) { // Xử lý ngoại lệ nếu có lỗi khi thực thi câu lệnh SQL
            $error_message = $e->getMessage(); // Lấy thông báo lỗi
            echo "<p>Lỗi truy vấn ở kiemTraNguoiDungHopLeDaMaHoa: $error_message</p>"; // Xuất thông báo lỗi
            exit(); // Thoát khỏi chương trình
        }
        finally {
            // Đảm bảo luôn đóng con trỏ, ngay cả khi có lỗi
            $cmd->closeCursor();
        }
    }

    // Hàm kiểm tra người dùng hợp lệ và trả về ID nếu hợp lệ
    //đầu vào là tài khoản mật khẩu đã được mã hóa, đầu ra là id người dùng nếu tk mật khẩu đúng ngoặc lại là false
    public function kiemTraNguoiDungHopLeTraVeID($tendangnhap, $matkhau) {
        $db = DATABASE::connect(); // Kết nối đến cơ sở dữ liệu
        try {
            $sql = "SELECT id FROM nguoidung WHERE ten_dang_nhap = :tendangnhap AND mat_khau = :matkhau AND trang_thai = 1"; // Chuẩn bị câu lệnh SQL để kiểm tra người dùng
            $cmd = $db->prepare($sql); // Chuẩn bị câu lệnh SQL để thực thi
            $cmd->bindValue(":tendangnhap", $tendangnhap); // Gán giá trị biến vào tham số của câu lệnh SQL
            $cmd->bindValue(":matkhau", $matkhau); // Gán giá trị biến vào tham số của câu lệnh SQL và mã hóa mật khẩu bằng MD5
            $cmd->execute(); // Thực thi câu lệnh SQL
            
            if ($cmd->rowCount() == 1) { // Kiểm tra xem người dùng có hợp lệ hay không
                $user = $cmd->fetch(PDO::FETCH_ASSOC); // Lấy thông tin của người dùng
                $cmd->closeCursor(); // Đóng con trỏ của câu lệnh SQL
                return $user['id']; // Trả về ID của người dùng nếu hợp lệ
            } 
            

            return false; // Trả về false nếu không hợp lệ
            
        } catch (PDOException $e) { // Xử lý ngoại lệ nếu có lỗi khi thực thi câu lệnh SQL
            $error_message = $e->getMessage(); // Lấy thông báo lỗi
            echo "<p>Lỗi truy vấn ở kiemtranguoidunghople: $error_message</p>"; // Xuất thông báo lỗi
            exit(); // Thoát khỏi chương trình
        }
        finally {
            // Đảm bảo luôn đóng con trỏ, ngay cả khi có lỗi
            $cmd->closeCursor();
        }
    }


    // // Hàm tạo token phiên
    // private function taoTokenPhien($userId) {
    //     $db = DATABASE::connect(); // Kết nối đến cơ sở dữ liệu
    //     try {
    //         $token = bin2hex(random_bytes(32)); // Tạo một token phiên ngẫu nhiên
    //         $expiry = date("Y-m-d H:i:s", strtotime('+1 hour')); // Tính toán thời gian hết hạn của token
    //         $sql = "INSERT INTO user_sessions (user_id, token, expiry) VALUES (:user_id, :token, :expiry)"; // Chuẩn bị câu lệnh SQL để chèn dữ liệu vào bảng user_sessions
    //         $cmd = $db->prepare($sql); // Chuẩn bị câu lệnh SQL để thực thi
    //         $cmd->bindValue(":user_id", $userId); // Gán giá trị biến vào tham số của câu lệnh SQL
    //         $cmd->bindValue(":token", $token); // Gán giá trị biến vào tham số của câu lệnh SQL
    //         $cmd->bindValue(":expiry", $expiry); // Gán giá trị biến vào tham số của câu lệnh SQL
    //         $cmd->execute(); // Thực thi câu lệnh SQL
    //         $_SESSION['token'] = $token; // Lưu token vào phiên
    //         $_SESSION['user_id'] = $userId; // Lưu ID người dùng vào phiên
    //     } catch (PDOException $e) { // Xử lý ngoại lệ nếu có lỗi khi thực thi câu lệnh SQL
    //         $error_message = $e->getMessage(); // Lấy thông báo lỗi
    //         echo "<p>Lỗi truy vấn ở taoTokenPhien: $error_message</p>"; // Xuất thông báo lỗi
    //         exit(); // Thoát khỏi chương trình
    //     }
    // }

    // Hàm kiểm tra token phiên
    public function kiemTraTokenPhien() {
        if (!isset($_SESSION['token']) || !isset($_SESSION['user_id'])) { // Kiểm tra xem token và ID người dùng có tồn tại trong phiên không
            return false; // Nếu không tồn tại, trả về false
        }
        $token = $_SESSION['token']; // Lấy token từ phiên
        $userId = $_SESSION['user_id']; // Lấy ID người dùng từ phiên
        $db = DATABASE::connect(); // Kết nối đến cơ sở dữ liệu
        try {
            $sql = "SELECT * FROM user_sessions WHERE user_id = :user_id AND token = :token AND expiry > NOW()"; // Chuẩn bị câu lệnh SQL để kiểm tra token phiên
            $cmd = $db->prepare($sql); // Chuẩn bị câu lệnh SQL để thực thi
            $cmd->bindValue(":user_id", $userId); // Gán giá trị biến vào tham số của câu lệnh SQL
            $cmd->bindValue(":token", $token); // Gán giá trị biến vào tham số của câu lệnh SQL
            $cmd->execute(); // Thực thi câu lệnh SQL
            $valid = ($cmd->rowCount() == 1); // Kiểm tra xem token phiên có hợp lệ hay không
            $cmd->closeCursor(); // Đóng con trỏ của câu lệnh SQL
            return $valid; // Trả về kết quả xác nhận hợp lệ của token phiên
        } catch (PDOException $e) { // Xử lý ngoại lệ nếu có lỗi khi thực thi câu lệnh SQL
            $error_message = $e->getMessage(); // Lấy thông báo lỗi
            echo "<p>Lỗi truy vấn ở kiemTraTokenPhien: $error_message</p>"; // Xuất thông báo lỗi
            exit(); // Thoát khỏi chương trình
        }
    }
            // Hàm hủy token phiên
    public function huyTokenPhien() {
        if (isset($_SESSION['token']) && isset($_SESSION['user_id'])) { // Kiểm tra xem token và ID người dùng có tồn tại trong phiên không
            $token = $_SESSION['token']; // Lấy token từ phiên
            $userId = $_SESSION['user_id']; // Lấy ID người dùng từ phiên
            $db = DATABASE::connect(); // Kết nối đến cơ sở dữ liệu
            try {
                $sql = "DELETE FROM user_sessions WHERE user_id = :user_id AND token = :token"; // Chuẩn bị câu lệnh SQL để xóa token phiên
                $cmd = $db->prepare($sql); // Chuẩn bị câu lệnh SQL để thực thi
                $cmd->bindValue(":user_id", $userId); // Gán giá trị biến vào tham số của câu lệnh SQL
                $cmd->bindValue(":token", $token); // Gán giá trị biến vào tham số của câu lệnh SQL
                $cmd->execute(); // Thực thi câu lệnh SQL
                unset($_SESSION['token']); // Xóa token khỏi phiên
                unset($_SESSION['user_id']); // Xóa ID người dùng khỏi phiên
            } catch (PDOException $e) { // Xử lý ngoại lệ nếu có lỗi khi thực thi câu lệnh SQL
                $error_message = $e->getMessage(); // Lấy thông báo lỗi
                echo "<p>Lỗi truy vấn ở huyTokenPhien: $error_message</p>"; // Xuất thông báo lỗi
                exit(); // Thoát khỏi chương trình
            }
        }
    }

    // Hàm lấy ID người dùng thông qua tên đăng nhập và mật khẩu
    public function layIdNguoiDungBangTenDangNhapVaMatKhau($tendangnhap, $matkhau) {
        $db = DATABASE::connect(); // Kết nối đến cơ sở dữ liệu
        try {
            $sql = "SELECT id FROM nguoidung WHERE ten_dang_nhap = :tendangnhap AND mat_khau = :matkhau"; // Chuẩn bị câu lệnh SQL để lấy ID người dùng
            $cmd = $db->prepare($sql); // Chuẩn bị câu lệnh SQL để thực thi
            $cmd->bindValue(":tendangnhap", $tendangnhap); // Gán giá trị biến vào tham số của câu lệnh SQL
            $cmd->bindValue(":matkhau", $matkhau); // Gán giá trị biến vào tham số của câu lệnh SQL và mã hóa mật khẩu bằng MD5
            $cmd->execute(); // Thực thi câu lệnh SQL
            
            if ($cmd->rowCount() == 1) { // Kiểm tra xem người dùng có hợp lệ hay không
                $user = $cmd->fetch(PDO::FETCH_ASSOC); // Lấy thông tin người dùng
                return $user['id']; // Trả về ID người dùng
            } else {
                return null; // Nếu không tìm thấy người dùng, trả về null
            }
        } catch (PDOException $e) { // Xử lý ngoại lệ nếu có lỗi khi thực thi câu lệnh SQL
            $error_message = $e->getMessage(); // Lấy thông báo lỗi
            echo "<p>Lỗi truy vấn ở layIdNguoiDung: $error_message</p>"; // Xuất thông báo lỗi
            exit(); // Thoát khỏi chương trình
        }
    }

// Các hàm khác của lớp NGUOIDUNG như laySoLuongNguoiDung, layNguoiDungPhanTrang, etc.
}
?>