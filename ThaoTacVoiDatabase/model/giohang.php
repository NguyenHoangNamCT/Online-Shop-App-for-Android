<?php 
require_once("database.php");

class GIOHANG {
    public function layGioHang($idNguoiDung) {
        $conn = DATABASE::connect();
        try {
            // Câu lệnh SQL để lấy thông giỏ hàng dựa trên id người dùng
            $sql = "
            SELECT 
            giohang.nguoi_dung_id, 
            giohang.san_pham_id, 
            giohang.so_luong, 
            sanpham.ten_san_pham, 
            sanpham.mo_ta,
            sanpham.id, 
            sanpham.gia_tien, 
            sanpham.giam_gia, 
            sanpham.so_luong AS so_luong_san_pham_cua_shop, 
            sanpham.hinh_anh
        FROM 
            giohang
        INNER JOIN 
            sanpham ON giohang.san_pham_id = sanpham.id
        WHERE 
            giohang.nguoi_dung_id = :idNguoiDung";
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

    public function themVaoGioHang($idNguoiDung, $idSanPham) {
        $conn = DATABASE::connect();
        try {
            // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng hay chưa
            $kiemTra = $this->kiemTraSanPhamTrongGioHang($idNguoiDung, $idSanPham);
            
            if ($kiemTra) {
                // Nếu sản phẩm đã tồn tại, tăng số lượng lên 1
                if ($this->tangSoLuongSanPham($idNguoiDung, $idSanPham)) {
                    return "san_pham_da_co_trong_gio_hang_so_luong_da_duoc_tang_them_1"; //lệnh if sẽ nhận là true (chỉ cần khong phải chuổi rỗng, null, hoặc chuổi "0" thì là true)
                } else {
                    return false; // Trả về false nếu không tăng được số lượng
                }
            } else {
                // Nếu sản phẩm chưa tồn tại, thêm sản phẩm mới vào giỏ hàng
                $sqlThemSanPham = "INSERT INTO giohang (nguoi_dung_id, san_pham_id, so_luong) 
                                   VALUES (:idNguoiDung, :idSanPham, 1)";
                $cmdThemSanPham = $conn->prepare($sqlThemSanPham);
                $cmdThemSanPham->bindParam(':idNguoiDung', $idNguoiDung);
                $cmdThemSanPham->bindParam(':idSanPham', $idSanPham);
                $cmdThemSanPham->execute();
    
                if ($cmdThemSanPham->rowCount() > 0) {
                    $result = [
                        'nguoi_dung_id' => $idNguoiDung,
                        'san_pham_id' => $idSanPham
                    ];
                    return $result; // Trả về ID của bản ghi vừa thêm nếu thành công (khi truyền vào lệnh if tương ứng với true, nếu mảng không null, hoặc rỗng thì là true)
                } else {
                    return false; // Trả về false nếu không thêm được bản ghi nào
                }
            }
        } catch (PDOException $e) {
            echo "Lỗi truy vấn ở themVaoGioHang: " . $e->getMessage();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }
    

    public function kiemTraSanPhamTrongGioHang($idNguoiDung, $idSanPham) {
        $conn = DATABASE::connect();
        try {
            // Câu lệnh SQL kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng hay chưa
            $sql = "SELECT so_luong FROM giohang WHERE nguoi_dung_id = :idNguoiDung AND san_pham_id = :idSanPham";
            $cmd = $conn->prepare($sql);
            $cmd->bindParam(':idNguoiDung', $idNguoiDung);
            $cmd->bindParam(':idSanPham', $idSanPham);
            $cmd->execute();
            
            // Trả về kết quả
            if ($cmd->rowCount() > 0) {
                return $cmd->fetch(PDO::FETCH_ASSOC); // Trả về số lượng sản phẩm nếu có (số khác 0 lệnh if sẽ tự hiểu là true)
            } else {
                return false; // Trả về false nếu không có sản phẩm
            }
        } catch (PDOException $e) {
            echo "Lỗi truy vấn ở kiemTraSanPhamTrongGioHang: " . $e->getMessage();
            return false;
        }
    }

    public function tangSoLuongSanPham($idNguoiDung, $idSanPham) {
        $conn = DATABASE::connect();
        try {
            // Câu lệnh SQL để tăng số lượng sản phẩm trong giỏ hàng
            $sql = "UPDATE giohang SET so_luong = so_luong + 1 WHERE nguoi_dung_id = :idNguoiDung AND san_pham_id = :idSanPham";
            $cmd = $conn->prepare($sql);
            $cmd->bindParam(':idNguoiDung', $idNguoiDung);
            $cmd->bindParam(':idSanPham', $idSanPham);
            $cmd->execute();
            
            return $cmd->rowCount() > 0; // Trả về true nếu tăng thành công, ngược lại là false
        } catch (PDOException $e) {
            echo "Lỗi truy vấn ở tangSoLuongSanPham: " . $e->getMessage();
            return false;
        }
    }

    public function capNhatSoLuongSanPhamTrongGio($idNguoiDung, $idSanPham, $soLuongMoi) {
        $conn = DATABASE::connect();
        try {
            // Câu lệnh SQL để cập nhật số lượng sản phẩm trong giỏ hàng
            $sql = "UPDATE giohang SET so_luong = :soLuongMoi WHERE nguoi_dung_id = :idNguoiDung AND san_pham_id = :idSanPham";
            $cmd = $conn->prepare($sql);
            $cmd->bindParam(':soLuongMoi', $soLuongMoi, PDO::PARAM_INT);
            $cmd->bindParam(':idNguoiDung', $idNguoiDung, PDO::PARAM_INT);
            $cmd->bindParam(':idSanPham', $idSanPham, PDO::PARAM_INT);
            $cmd->execute();
    
            // Kiểm tra xem câu lệnh có thực hiện thành công hay không
            return ($cmd->rowCount() > 0);
        } catch (PDOException $e) {
            echo "Lỗi truy vấn ở capNhatSoLuongSanPham: " . $e->getMessage();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    public function laySoLuongSanPhamTrongGio($idNguoiDung, $idSanPham) {
        $conn = DATABASE::connect(); // Kết nối đến cơ sở dữ liệu
        try {
            // Câu lệnh SQL để lấy số lượng sản phẩm trong giỏ hàng
            $sql = "SELECT so_luong FROM giohang WHERE nguoi_dung_id = :idNguoiDung AND san_pham_id = :idSanPham";
            $cmd = $conn->prepare($sql);
            $cmd->bindParam(':idNguoiDung', $idNguoiDung, PDO::PARAM_INT);
            $cmd->bindParam(':idSanPham', $idSanPham, PDO::PARAM_INT);
            $cmd->execute();
    
            // Lấy kết quả
            if ($row = $cmd->fetch(PDO::FETCH_ASSOC)) {
                return $row['so_luong']; // Trả về số lượng sản phẩm
            } else {
                return 0; // Trả về 0 nếu không có sản phẩm trong giỏ hàng
            }
        } catch (PDOException $e) {
            echo "Lỗi truy vấn ở laySoLuongSanPhamTrongGio: " . $e->getMessage();
            return null; // Trả về null nếu có lỗi xảy ra
        }
    }    
    
    
}
?>
