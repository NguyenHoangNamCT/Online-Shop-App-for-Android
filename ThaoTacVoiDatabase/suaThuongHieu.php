<?php 
    require("model/thuonghieu.php");

    $th = new ThuongHieu();
    
    // Nhận các giá trị từ request POST
    $idThuongHieu = $_POST['id'];
    $tenThuongHieu = $_POST['ten_thuong_hieu'];
    $moTaThuongHieu = $_POST['mo_ta'];
    $trangWebThuongHieu = $_POST['trang_web'];
    $hinhAnhDangChuoiBase64 = $_POST['logo'];

    //nếu người dùng không chọn ảnh thì mặt định biến sẽ là null
    $imageName = null;

    //nếu người dùng có chọn ảnh thì tiến hành lưu ảnh mới lên sever và gán giá trị cho biến $imageName
    if($hinhAnhDangChuoiBase64 != ""){
        //xử lý hình ảnh đã được app chuyển thành chuổi base64 ở phía sever
        // bước 1 Chuyển đổi hình ảnh từ dạng chuổi base64 sang dạng file
        $hinh_anh = base64_decode($hinhAnhDangChuoiBase64);

        //bước 2 đặt tên tệp ảnh sao cho không bị trùng
        $imageName = $tenThuongHieu . "_" . date("Ymd_His") . ".jpg";

        //bước 3 xác định đường dẫn lưu ở sever
        $target_file = "images/".$imageName;

        //bước 4 tiến hành lưu tệp $hinh_anh vào đường dẫn $target_file bằng hàm có sẵn file_put_contents (hàm này sẽ trả về số byte nếu thành công ngược lại thì false)
        if (!file_put_contents($target_file, $hinh_anh)) {
            //nếu lỗi thêm hình thì thông báo và kết thúc chương trình
            echo "erro them hinh";
            exit();
        }   
        
    }
    
    if ($th->suaThuongHieu($idThuongHieu, $tenThuongHieu, $moTaThuongHieu, $trangWebThuongHieu, $imageName)) {
        echo "success";
    } else {
        echo "false";
    }
    
    
    
?>