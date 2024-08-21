<?php 

require("model/nguoidung.php");

//Các trạng thái thông báo của web
$dangNhapThanhCong = "Success";
$dangNhapThatBai = "Fail";
$dangNhapLoi = "Erro";

$nd = new NGUOIDUNG();

if ($_SERVER['REQUEST_METHOD'] === 'POST'){
    if(isset($_POST['tenDangNhap']) && isset($_POST['matKhau'])){
        $tenDangNhap = $_POST['tenDangNhap'];
        $matKhauMD5 = $_POST['matKhau'];
        if($nd->kiemTraNguoiDungHopLeDaMaHoa($tenDangNhap, $matKhauMD5)){
            echo $dangNhapThanhCong;
        }
        else
            echo $dangNhapThatBai;
    }
    else{
        echo $dangNhapLoi;
    }
}
else{
    echo $dangNhapLoi;
}


?>