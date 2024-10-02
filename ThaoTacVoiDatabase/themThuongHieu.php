<?php 
    require("model/thuonghieu.php");

    $th = new ThuongHieu();

    // Lấy giá trị từ sever
    $tenThuongHieu = $_POST['tenThuongHieu'];
    $moTaThuongHieu = $_POST['moTaThuongHieu'];
    $trangWebThuongHieu = $_POST['trangWebThuongHieu'];
    $logoBase64 = $_POST['logoBase64'];
    // TestCode
    // echo "Tên thương hiệu: $tenThuongHieu, Mô tả: $moTaThuongHieu, Trang web: $trangWebThuongHieu";
    //xử lý hình ảnh đã được app chuyển thành chuổi base64 ở phía sever
    // bước 1 Chuyển đổi hình ảnh từ dạng chuổi base64 sang dạng file
    $logoFileAnh = base64_decode($logoBase64);

    //bước 2 đặt tên tệp ảnh sao cho không bị trùng
    $logoName = $tenThuongHieu . "_" . date("Ymd_His") . ".jpg";

    //bước 3 xác định đường dẫn lưu ở sever
    $target_file = "images/".$logoName;

    //bước 4 tiến hành lưu tệp $hinh_anh vào đường dẫn $target_file bằng hàm có sẵn file_put_contents (hàm này sẽ trả về số byte nếu thành công ngược lại thì false)
    if (file_put_contents($target_file, $logoFileAnh)) {
        // Nếu lưu hình ảnh thành công, tiến hành thêm loại sản phẩm vào cơ sở dữ liệu
        $idThuongHieu = $th->themThuongHieu($tenThuongHieu, $moTaThuongHieu, $trangWebThuongHieu, $logoName);
        if ($idThuongHieu) {
            // Nếu thêm sản phẩm thành công thì báo thành công
            echo "success";
        } else {
            // Nếu thêm thất bại thì báo lỗi
            echo "erro";
        }
    } else {
        echo "erro them hinh".$logoBase64;
    }


    

?>