<?php 
require("model/giohang.php");
require('model/nguoidung.php');

$gioHang = new GIOHANG(); // Khởi tạo đối tượng
$nguoiDung = new NGUOIDUNG();



// // test code
// $tenDangNhap = "A";
//     $matKhau = "202cb962ac59075b964b07152d234b70";
// inGioHangDuoiDangJson($tenDangNhap, $matKhau, $nguoiDung, $gioHang);


if (isset($_POST['userName']) && isset($_POST['password'])) {
    $tenDangNhap = $_POST['userName'];
    $matKhau = $_POST['password'];

    inGioHangDuoiDangJson($tenDangNhap, $matKhau, $nguoiDung, $gioHang);

} else {
    inBaoLoiDuoiDangJsonArray("Du_lieu_gui_len_sever_co_van_de");
}



//----------- các hàm -------------------
// Phương thức xử lý giỏ hàng
//hàm in giỏ hàng dưới dạng json, đầu ra là: in ra giỏ hàng của người dùng dưỚi dạng json hoặc là 1 câu báo lỗi nếu người dùng không hợp lệ
function inGioHangDuoiDangJson($tenDangNhap, $matKhau, $nguoiDung, $gioHang) {
    if ($idNguoiDung = $nguoiDung->kiemTraNguoiDungHopLeTraVeID($tenDangNhap, $matKhau)) {
        $mangGioHang = $gioHang->layGioHang($idNguoiDung); // Lấy giỏ hàng của người dùng
        // Xuất giỏ hàng ra JSON
        echo json_encode($mangGioHang);
    } else {
        $response = array("error" => "Nguoi_dung_khong_hop_le"); //cách 2
        echo json_encode($response); // Chuyển đổi mảng thành JSON và in ra
    }
    inBaoLoiDuoiDangJsonArray("Nguoi_dung_khong_hop_le");
}

// // làm như này nó lại in ra một json object :D
// function inBaoLoiDuoiDangJsonArray($cauBaoLoi){
//     $array = array();
//     $array["error"] = $cauBaoLoi;
//     echo json_encode($array);
// }

// // làm như này nó lại in ra 1 json array trong 1 json array khác
// function inBaoLoiDuoiDangJsonArray($cauBaoLoi){
//     $array = array();
//     $errorObject = array("error", $cauBaoLoi);
//     $array[] = $errorObject;
//     echo json_encode($array);
// }

//làm như này nó in ra 1 jsonarray chứa 1 jsonobject
function inBaoLoiDuoiDangJsonArray($cauBaoLoi) {
    // Tạo một mảng chứa một JSON object
    $array = array(); // Tạo mảng rỗng lớn
    $errorObject = array(); // Tạo một mảng rỗng cho JSON object
    $errorObject["error"] = $cauBaoLoi; // Thêm key "error" và giá trị vào mảng JSON object
    
    $array[] = $errorObject; // Thêm JSON object vào mảng lớn

    echo json_encode($array); // Chuyển đổi mảng lớn thành JSON và in ra
}

?>
