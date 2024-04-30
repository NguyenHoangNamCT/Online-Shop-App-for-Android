<?php 
require('model/sanpham.php');


// class SP {
//     public $Id;
//     public $IdLoaiSanPham;
//     public $IdThuongHieu;
//     public $TenSanPham;
//     public $MoTa;
//     public $GiaTien;
//     public $GiamGia;
//     public $SoLuong;
//     public $HinhAnh;
//     public $LuotXem;
//     public $LuotMua;

//     // Phương thức khởi tạo
//     public function __construct($id, $idLoaiSanPham, $idThuongHieu, $tenSanPham, $moTa, $giaTien, $giamGia, $soLuong, $hinhAnh, $luotXem, $luotMua) {
//         $this->Id = $id;
//         $this->IdLoaiSanPham = $idLoaiSanPham;
//         $this->IdThuongHieu = $idThuongHieu;
//         $this->TenSanPham = $tenSanPham;
//         $this->MoTa = $moTa;
//         $this->GiaTien = $giaTien;
//         $this->GiamGia = $giamGia;
//         $this->SoLuong = $soLuong;
//         $this->HinhAnh = $hinhAnh;
//         $this->LuotXem = $luotXem;
//         $this->LuotMua = $luotMua;
//     }
// }

//lấy dssp từ csdl
$sp = new SanPham();
$danhSachSP = $sp->layDanhSachSanPham();

//để khỏi cần tạo thêm class SP, nhưng chưa test nên để ở comment
// //tự code để xoá các chỉ mục là số trong mảng con
foreach ($danhSachSP as $key_i => $value_i) {
    foreach($value_i as $key_j => $value_j){
        if (is_numeric($key_j)) {
            unset($danhSachSP[$key_i][$key_j]);
        }
    }
}

// //sử dụng phương thức có sẵn để xoá các chỉ mục là số trong mảng con
// foreach ($danhSachSP as &$item) {
//     $item = array_filter($item, 'is_string', ARRAY_FILTER_USE_KEY);
// }
// unset($item);

// Khởi tạo mảng trống
// $mangSP = array(); 
//duyệt dssp
// foreach ($danhSachSP as $sanPham) {
//     // Tạo một đối tượng SP mới
//     $sp = new SP(
//         $sanPham['id'],
//         $sanPham['id_loai_san_pham'],
//         $sanPham['id_thuong_hieu'],
//         $sanPham['ten_san_pham'],
//         $sanPham['mo_ta'],
//         $sanPham['gia_tien'],
//         $sanPham['giam_gia'],
//         $sanPham['so_luong'],
//         $sanPham['hinh_anh'],
//         $sanPham['luot_xem'],
//         $sanPham['luot_mua']
//     );
//     // Thêm đối tượng SP vào mảng $mang
//     array_push($mangSP, $sp);
// }
// echo json_encode($mangSP);

echo json_encode($danhSachSP);




?>