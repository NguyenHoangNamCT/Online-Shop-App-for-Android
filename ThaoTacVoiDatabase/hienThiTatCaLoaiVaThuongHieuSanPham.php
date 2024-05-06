<?php 
require("model/thuonghieu.php");
require("model/loaisanpham.php");

$th = new ThuongHieu();
$l = new LoaiSanPham();

$mangLoaiVaThuongHieu = array();

$mangLoaiVaThuongHieu["danhSachThuongHieu"] = $th->layDanhSachThuongHieu();
$mangLoaiVaThuongHieu["danhSachLoaiSanPham"] = $l->layDanhSachLoaiSanPham();

echo json_encode($mangLoaiVaThuongHieu);


?>