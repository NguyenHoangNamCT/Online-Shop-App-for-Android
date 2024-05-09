<?php 
require("model/sanpham.php");

$sp = new SanPham();

// Kiểm tra xem có dữ liệu được gửi từ phương thức POST không
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Kiểm tra xem tất cả các trường cần thiết đã được gửi chưa
    if(isset($_POST['sanPhamID']) && isset($_POST['idLoaiSanPham']) && isset($_POST['idThuongHieu']) && isset($_POST['tenSanPham']) && isset($_POST['moTa']) && isset($_POST['giaTien']) && isset($_POST['giamGia']) && isset($_POST['soLuong']) && isset($_POST['hinhAnh']) && isset($_POST['luotXem']) && isset($_POST['luotMua']) && isset($_POST['donViTinh'])) {
        // Lấy dữ liệu từ biến POST
        $sanPhamID = $_POST['sanPhamID'];
        $idLoaiSanPham = $_POST['idLoaiSanPham'];
        $idThuongHieu = $_POST['idThuongHieu'];
        $tenSanPham = $_POST['tenSanPham'];
        $moTa = $_POST['moTa'];
        $giaTien = $_POST['giaTien'];
        $giamGia = $_POST['giamGia'];
        $soLuong = $_POST['soLuong'];
        $hinhAnhBase64 = $_POST['hinhAnh'];
        $luotXem = $_POST['luotXem'];
        $luotMua = $_POST['luotMua'];
        $donViTinh = $_POST['donViTinh'];


        //nếu app gửi lên kiểu null thì biến $hinhAnhBase64 sẽ có giá trị là chuổi rổng
        if($hinhAnhBase64 !== ""){
            // Chuyển đổi hình ảnh từ dạng base64 sang dạng file
            $hinhAnh = base64_decode($hinhAnhBase64);

            // Lưu hình ảnh vào thư mục trên server
            //dòng này dùng nối chuổi bình thường cũng được không cần dùng base name cũng không sao
            $imageName = basename($tenSanPham . "_" . date("Ymd_His") . ".jpg");
            $target_file = "images/".$imageName;
            //nếu lưu thành công
            if (file_put_contents($target_file, $hinhAnh)) {
                // Gọi phương thức suaSanPham để cập nhật sản phẩm
                if($sp->suaSanPham($sanPhamID, $idLoaiSanPham, $idThuongHieu, $tenSanPham, $moTa, $giaTien, $giamGia, $soLuong, $luotXem, $luotMua, $donViTinh, $imageName))
                    echo "success";
                else
                    echo "erro";
            } else {
                echo "erro them hinh";
            }
        } else { //nếu hình ảnh null thì không sửa hình
            if($sp->suaSanPham($sanPhamID, $idLoaiSanPham, $idThuongHieu, $tenSanPham, $moTa, $giaTien, $giamGia, $soLuong, $luotXem, $luotMua, $donViTinh))
                    echo "success";
                else
                    echo "erro";
        }

    } else {
        // Thông báo lỗi nếu thiếu các trường dữ liệu cần thiết
        echo "gui thieu thong tin";
        if(!isset($_POST['sanPhamID'])) {
            echo "Biến sanPhamID không tồn tại";
        }
        if(!isset($_POST['idLoaiSanPham'])) {
            echo "Biến idLoaiSanPham không tồn tại";
        }
        if(!isset($_POST['idThuongHieu'])) {
            echo "Biến idThuongHieu không tồn tại";
        }
        if(!isset($_POST['tenSanPham'])) {
            echo "Biến tenSanPham không tồn tại";
        }
        if(!isset($_POST['moTa'])) {
            echo "Biến moTa không tồn tại";
        }
        if(!isset($_POST['giaTien'])) {
            echo "Biến giaTien không tồn tại";
        }
        if(!isset($_POST['giamGia'])) {
            echo "Biến giamGia không tồn tại";
        }
        if(!isset($_POST['soLuong'])) {
            echo "Biến soLuong không tồn tại";
        }
        if(!isset($_POST['hinhAnh'])) {
            echo "Biến hinhAnh không tồn tại";
        }
        if(!isset($_POST['luotXem'])) {
            echo "Biến luotXem không tồn tại";
        }
        if(!isset($_POST['luotMua'])) {
            echo "Biến luotMua không tồn tại";
        }
        if(!isset($_POST['donViTinh'])) {
            echo "Biến donViTinh không tồn tại";
        }
        
    }
}else{
    echo "khong phai post";
}

?>