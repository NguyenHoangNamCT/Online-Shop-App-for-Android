-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th4 29, 2024 lúc 11:58 AM
-- Phiên bản máy phục vụ: 5.7.25
-- Phiên bản PHP: 7.1.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shop_vlxd`
--
CREATE DATABASE IF NOT EXISTS `shop_vlxd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `shop_vlxd`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `id_don_hang` int(11) NOT NULL,
  `id_san_pham` int(11) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `don_gia` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `id_don_hang`, `id_san_pham`, `so_luong`, `don_gia`) VALUES
(40, 56, 1, 10, 140000),
(41, 56, 2, 1, 80000),
(42, 56, 3, 1, 170000),
(43, 57, 8, 1, 250000),
(44, 58, 1, 1, 140000),
(45, 59, 2, 1, 80000),
(46, 60, 2, 1, 80000),
(48, 63, 1, 1, 140000),
(49, 63, 8, 1, 250000),
(50, 64, 1, 1, 140000),
(51, 65, 1, 10, 140000),
(52, 66, 1, 1, 140000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhgia`
--

CREATE TABLE `danhgia` (
  `id` int(11) NOT NULL,
  `id_nguoi_dung` int(11) NOT NULL,
  `id_san_pham` int(11) NOT NULL,
  `diem_danh_gia` int(11) NOT NULL,
  `noi_dung` varchar(255) DEFAULT NULL,
  `hinh_anh` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `danhgia`
--

INSERT INTO `danhgia` (`id`, `id_nguoi_dung`, `id_san_pham`, `diem_danh_gia`, `noi_dung`, `hinh_anh`) VALUES
(10, 1, 66, 5, 'Sản phẩm quá ok luôn', '643feb4846aaa_1.jpg, 643feb4846be8_2.jpg, 643feb4846cad_3.jpg'),
(11, 1, 55, 5, 'ádfasdfasdf', '6440ee0e8b102_'),
(12, 1, 54, 5, '1111', '644362a57c1c6_'),
(13, 1, 54, 5, '1111', '64436f0b2c8be_'),
(14, 1, 1, 5, 'âdasdsad', '644a250914255_tải xuống (1).jpg, 644a250914878_tải xuống.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `id_nguoi_dung` int(11) NOT NULL,
  `ngay_dat` datetime NOT NULL,
  `dia_chi_giao_hang` varchar(200) NOT NULL,
  `dien_thoai_nguoi_nhan` varchar(20) NOT NULL,
  `tong_tien` float NOT NULL,
  `tinh_trang_don_hang` int(50) NOT NULL,
  `da_huy` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `id_nguoi_dung`, `ngay_dat`, `dia_chi_giao_hang`, `dien_thoai_nguoi_nhan`, `tong_tien`, `tinh_trang_don_hang`, `da_huy`) VALUES
(56, 14, '2023-04-30 15:53:40', '456 Lê Văn Việt, Hà Nội', '0123456789', 1675000, 0, 0),
(57, 14, '2023-04-30 15:53:48', '456 Lê Văn Việt, Hà Nội', '0123456789', 275000, 0, 1),
(58, 18, '2023-04-30 15:54:20', '456 Nguyễn Thị Minh Khai, Quận 3, TP Hồ Chí Minh', '0932123456', 165000, 0, 1),
(59, 18, '2023-04-30 15:54:25', '456 Nguyễn Thị Minh Khai, Quận 3, TP Hồ Chí Minh', '0932123456', 105000, 0, 1),
(60, 16, '2023-04-30 15:55:08', '123 Lê Lợi, Thành phố Huế', '0865432109', 105000, 0, 1),
(63, 1, '2023-05-01 19:14:02', 'Ha Noi', '0987654321', 415000, 0, 0),
(64, 3, '2023-05-08 18:32:19', 'Da Nang', '0912345678', 165000, 0, 1),
(65, 3, '2023-05-08 18:45:53', 'Da Nang', '0912345678', 1425000, 0, 1),
(66, 3, '2023-05-08 18:46:37', 'Da Nang', '0912345678', 165000, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `giohang`
--

CREATE TABLE `giohang` (
  `id_nguoi_dung` int(11) NOT NULL,
  `id_san_pham` int(11) NOT NULL,
  `so_luong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `giohang`
--

INSERT INTO `giohang` (`id_nguoi_dung`, `id_san_pham`, `so_luong`) VALUES
(1, 1, 1),
(3, 1, 75);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `id` int(11) NOT NULL,
  `id_don_hang` int(11) NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `tong_tien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`id`, `id_don_hang`, `ngay_tao`, `tong_tien`) VALUES
(6, 56, '2023-04-30 15:53:40', 10),
(7, 57, '2023-04-30 15:53:48', 275000),
(8, 58, '2023-04-30 15:54:20', 165000),
(9, 59, '2023-04-30 15:54:25', 105000),
(10, 60, '2023-04-30 15:55:08', 105000),
(11, 63, '2023-05-01 19:14:02', 415000),
(12, 64, '2023-05-08 18:32:19', 165000),
(13, 65, '2023-05-08 18:45:53', 1425000),
(14, 66, '2023-05-08 18:46:37', 165000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lienhe`
--

CREATE TABLE `lienhe` (
  `id` int(11) NOT NULL,
  `ho_ten` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `so_dien_thoai` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `lienhe`
--

INSERT INTO `lienhe` (`id`, `ho_ten`, `email`, `so_dien_thoai`) VALUES
(1, 'Nguyễn Hoàng Nam', 'admin@gmail.com', '0987654321'),
(2, 'nvtvkh', 'nvtvkh@gmail.com', '0912345678');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `ten_loai_san_pham` varchar(100) NOT NULL,
  `mo_ta` varchar(500) NOT NULL,
  `trang_thai` bit(1) NOT NULL,
  `hinh_anh` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `ten_loai_san_pham`, `mo_ta`, `trang_thai`, `hinh_anh`) VALUES
(1, 'Gạch', ' là vật liệu xây dựng phổ biến nhất được làm từ đất sét hoặc bê tông. Có nhiều loại gạch khác nhau như gạch xi măng, gạch nung, gạch xây tường, gạch lát sàn, vv.', b'1', 'gach.jpg'),
(2, 'Xi măng', 'là vật liệu xây dựng chính để tạo ra bê tông, được làm từ chất liệu khoáng như đá vôi và đá granit.', b'1', 'ximang.jpg'),
(3, 'Bê tông', ' là vật liệu xây dựng cứng và chắc chắn được làm từ xi măng, cát, nước và chất liệu bổ sung như cát, đá vụn hoặc sắt thép.', b'1', 'betong.jpg'),
(4, 'Gỗ', 'Sản phẩm chuyên dùng cho công trình xây dựng như: nhà xưởng, cầu đường, kết cấu công trình.là vật liệu xây dựng tự nhiên được sử dụng rộng rãi trong xây dựng nhà cửa và công trình. Các loại gỗ phổ biến như gỗ thông, gỗ sồi, gỗ teak, vv.', b'1', 'go.jpg'),
(5, 'Kim loại', 'là vật liệu xây dựng đa dạng bao gồm sắt, thép, nhôm và đồng. Chúng được sử dụng để làm kết cấu nhà, vách ngăn, cửa sổ và cửa ra vào.', b'1', 'kimloai.jpg'),
(6, 'Thủy tinh', 'là vật liệu xây dựng được sử dụng rộng rãi để tạo ra cửa sổ, cửa ra vào, vách ngăn và các vật dụng trang trí khác.', b'1', 'thuytinh.jpg'),
(7, 'Vật liệu cách âm và cách nhiệt', 'là loại vật liệu được thiết kế để giảm thiểu tiếng ồn và giữ nhiệt trong nhà. Các loại vật liệu này bao gồm xốp polyurethane, xốp polystyrene, bông thủy tinh, vv.', b'1', 'cachnhiet.jpg'),
(8, 'Lót sàn', 'là loại vật liệu được sử dụng để phủ lên sàn nhà như đá, gạch, đá cẩm thạch, gỗ hoặc các vật liệu nhân tạo.', b'1', 'lotsan.jpg'),
(10, 'Vật liệu trang trí', 'bao gồm sơn, giấy dán tường, gạch mosaic, đá tự nhiên và các vật liệu trang trí khác để tạo ra một không gian sống đẹp và tiện nghi.', b'1', 'trangtri.jpg'),
(11, 'Ngói', 'là vật liệu xây dựng được làm từ đất sét nung hoặc từ các chất liệu khác như gốm sứ hoặc composite. Ngói được sử dụng để làm mái nhà hoặc lợp vách.', b'1', 'ngoi.jpg'),
(13, 'Đá', 'là loại vật liệu tự nhiên được đánh bóng để tạo ra các bề mặt trơn nhẵn. Đá được sử dụng để lát sàn, tường hoặc các bề mặt khác trong nhà và ngoài trời.', b'1', 'da.jpg'),
(15, 'Gạch block', 'là loại gạch lớn được làm từ bê tông hoặc xi măng, được sử dụng để xây tường hoặc kết cấu nhà.', b'0', 'block.jpg'),
(16, 'Vật liệu composite', ' là loại vật liệu xây dựng được làm từ một số chất liệu khác nhau như sợi thủy tinh, nhựa và keo. Vật liệu composite được sử dụng để tạo ra các kết cấu nhẹ, chịu lực tốt và kháng nước.', b'0', 'composite.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoidung`
--

CREATE TABLE `nguoidung` (
  `id` int(11) NOT NULL,
  `ten_dang_nhap` varchar(50) NOT NULL,
  `mat_khau` varchar(50) NOT NULL,
  `ho_ten` varchar(100) NOT NULL,
  `dia_chi` varchar(200) NOT NULL,
  `dien_thoai` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `ngay_dang_ky` datetime NOT NULL,
  `loai_nguoi_dung` int(11) NOT NULL,
  `hinh_anh` varchar(255) DEFAULT NULL,
  `trang_thai` bit(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `nguoidung`
--

INSERT INTO `nguoidung` (`id`, `ten_dang_nhap`, `mat_khau`, `ho_ten`, `dia_chi`, `dien_thoai`, `email`, `ngay_dang_ky`, `loai_nguoi_dung`, `hinh_anh`, `trang_thai`) VALUES
(1, 'user1', '202cb962ac59075b964b07152d234b70', 'Nguyen Van Aaa', 'Ha Noi', '0987654321', 'user1@gmail.com', '2023-04-07 15:08:00', 2, '644fa903ab6cd_tải xuống (2).jpg', b'001'),
(2, 'user2', '202cb962ac59075b964b07152d234b70', 'Tran Thi B', 'Ho Chi Minh', '0123456789', 'user2@gmail.com', '2023-04-07 15:08:00', 2, 'user1.jpg', b'001'),
(3, 'admin1', '202cb962ac59075b964b07152d234b70', 'Le Thi C', 'Da Nang', '0912345678', 'admin1@gmail.comm', '2023-04-07 15:08:00', 1, '64509c37b9808_images.jpg', b'001'),
(4, 'admin2', '202cb962ac59075b964b07152d234b70', 'Pham Van D', 'Hai Phong', '0888888888', 'admin2@gmail.com', '2023-04-07 15:08:00', 1, 'user1.jpg', b'001'),
(5, 'manager1', '202cb962ac59075b964b07152d234b70', 'Nguyen Thi E', 'Can Tho', '0333333333', 'manager1@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(6, 'hoangnguyen', '202cb962ac59075b964b07152d234b70', 'Nguyễn Hoàng', 'Hà Nội, Việt Nam', '0987654321', 'hoangnguyen@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(7, 'trangphan', '202cb962ac59075b964b07152d234b70', 'Phan Trang', 'Hồ Chí Minh, Việt Nam', '0987654321', 'trangphan@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(8, 'tuandinh', '202cb962ac59075b964b07152d234b70', 'Đinh Tuấn', 'Đà Nẵng, Việt Nam', '0987654321', 'tuandinh@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(9, 'thanhnguyen', '202cb962ac59075b964b07152d234b70', 'Nguyễn Thanh', 'Hải Phòng, Việt Nam', '0987654321', 'thanhnguyen@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(10, 'anhvo', '202cb962ac59075b964b07152d234b70', 'Võ Anh', 'Hồ Chí Minh, Việt Nam', '0987654321', 'anhvo@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(11, 'hongnguyen', '202cb962ac59075b964b07152d234b70', 'Nguyễn Hồng', 'Hà Nội, Việt Nam', '0987654321', 'hongnguyen@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(12, 'thanhpham', '202cb962ac59075b964b07152d234b70', 'Phạm Thanh', 'Hải Phòng, Việt Nam', '0987654321', 'thanhpham@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(13, 'ngocdiep', '202cb962ac59075b964b07152d234b70', 'Đinh Ngọc Diệp', '123 Nguyễn Văn Linh, TP Hồ Chí Minh', '0987654321', 'ngocdiep@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(14, 'hongngoc', '202cb962ac59075b964b07152d234b70', 'Vũ Hồng Ngọc', '456 Lê Văn Việt, Hà Nội', '0123456789', 'hongngoc@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(15, 'thanhhoa', '202cb962ac59075b964b07152d234b70', 'Nguyễn Thanh Hòa', '789 Phạm Văn Đồng, Đà Nẵng', '0912345678', 'thanhhoa@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(16, 'quynhanh', '202cb962ac59075b964b07152d234b70', 'Lê Quỳnh Anh', '123 Lê Lợi, Thành phố Huế', '0865432109', 'quynhanh@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(17, 'hoangkhanh', '202cb962ac59075b964b07152d234b70', 'Nguyễn Hoàng Khánh', '789 Nguyễn Văn Cừ, Quận 5, TP Hồ Chí Minh', '0978098765', 'hoangkhanh@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(18, 'thanhthao', '202cb962ac59075b964b07152d234b70', 'Lê Thanh Thảo', '456 Nguyễn Thị Minh Khai, Quận 3, TP Hồ Chí Minh', '0932123456', 'thanhthao@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(19, 'hoanglong', '202cb962ac59075b964b07152d234b70', 'Nguyễn Hoàng Long', '123 Nguyễn Công Trứ, TP Đà Lạt', '0943216789', 'hoanglong@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(20, 'vietnguyen', '202cb962ac59075b964b07152d234b70', 'Nguyễn Việt', 'Hà Nội', '0987654321', 'vietnguyen@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(21, 'thanhnguyen', '202cb962ac59075b964b07152d234b70', 'Nguyễn Thanh', 'Hà Nội', '0987654321', 'thanhnguyen@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(22, 'thanhtran', '202cb962ac59075b964b07152d234b70', 'Trần Thanh', 'Đà Nẵng', '0987654321', 'thanhtran@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(23, 'tuananhle', '202cb962ac59075b964b07152d234b70', 'Lê Tuấn Anh', 'TP.HCM', '0987654321', 'tuananhle@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(24, 'thanhhoang', '202cb962ac59075b964b07152d234b70', 'Hoàng Thanh', 'Hà Nội', '0987654321', 'thanhhoang@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(25, 'quynhtran', '202cb962ac59075b964b07152d234b70', 'Trần Quỳnh', 'Hải Phòng', '0987654321', 'quynhtran@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(26, 'thuytrinh', '202cb962ac59075b964b07152d234b70', 'Trịnh Thuỳ', 'Hà Nội', '0987654321', 'thuytrinh@gmail.com', '2023-04-07 15:08:00', 3, 'user1.jpg', b'001'),
(27, 'nguyenvana', '202cb962ac59075b964b07152d234b70', 'Nguyễn Văn A', 'Hà Nội', '0987654321', 'nguyenvana@gmail.com', '2023-04-06 10:00:00', 3, 'user1.jpg', b'001'),
(28, 'tranvanchi', '202cb962ac59075b964b07152d234b70', 'Trần Văn Chi', 'Hải Phòng', '0912345678', 'tranvanchi@gmail.com', '2023-04-06 11:00:00', 3, 'user1.jpg', b'001'),
(29, 'lethuylinh', '202cb962ac59075b964b07152d234b70', 'Lê Thuỳ Linh', 'Hà Tĩnh', '0987654321', 'lethuylinh@gmail.com', '2023-04-06 12:00:00', 3, 'user1.jpg', b'001'),
(30, 'nguyenhoang', '202cb962ac59075b964b07152d234b70', 'Nguyễn Hoàng', 'Nghệ An', '0912345678', 'nguyenhoang@gmail.com', '2023-04-06 13:00:00', 3, 'user1.jpg', b'001'),
(31, 'tranhungcuong', '202cb962ac59075b964b07152d234b70', 'Trần Hùng Cường', 'Quảng Ninh', '0987654321', 'tranhungcuong@gmail.com', '2023-04-06 14:00:00', 3, 'user1.jpg', b'001'),
(32, 'lethimai', '202cb962ac59075b964b07152d234b70', 'Lê Thị Mai', 'Thanh Hóa', '0912345678', 'lethimai@gmail.com', '2023-04-06 15:00:00', 3, 'user1.jpg', b'001'),
(33, 'phamthuan', '202cb962ac59075b964b07152d234b70', 'Phạm Thuận', 'Hà Nội', '0987654321', 'phamthuan@gmail.com', '2023-04-06 16:00:00', 3, 'user1.jpg', b'001'),
(34, 'test them ndddddddd', '202cb962ac59075b964b07152d234b70', 'test them ndddddddd', 'Cần Thơooooooooooo', '0123456789', 'abc@abc.co', '2023-04-08 13:27:18', 1, 'user1.jpg', b'001'),
(35, 'nhnam', '202cb962ac59075b964b07152d234b70', 'Nguyễn Hoàng Nam', '123', '0123456789', 'nhnam_20th@student.agu.edu.vn', '2023-04-16 10:53:37', 1, '', b'001'),
(36, 'nhn', '202cb962ac59075b964b07152d234b70', 'Nam', 'CT', '0123456789', 'nhn@gmail.com', '2023-04-16 11:24:35', 3, 'a2.jpg', b'001'),
(42, 'AA', '202cb962ac59075b964b07152d234b70', '123', 'AA', '0364337001', 'soyum001@gmail.com', '2023-04-30 08:11:25', 3, 'tải xuống.jpg', b'001'),
(43, 'ADD', '202cb962ac59075b964b07152d234b70', '123', 'AA', '0364337001', 'soyum001@gmail.com', '2023-04-30 08:12:30', 3, 'tải xuống (1).jpg', b'001');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanhoi`
--

CREATE TABLE `phanhoi` (
  `id` int(11) NOT NULL,
  `id_nguoi_dung` int(11) NOT NULL,
  `id_danh_gia` int(11) NOT NULL,
  `noi_dung` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quangcao`
--

CREATE TABLE `quangcao` (
  `id` int(11) NOT NULL,
  `tieu_de` varchar(200) NOT NULL,
  `hinh_anh` varchar(200) NOT NULL,
  `url` varchar(200) NOT NULL,
  `trang_thai` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `quangcao`
--

INSERT INTO `quangcao` (`id`, `tieu_de`, `hinh_anh`, `url`, `trang_thai`) VALUES
(11, 'Hoà Phát', 'thephpg.png', 'https://www.hoaphat.com.vn/', 1),
(12, 'Hoa Sen', 'hoa_sen.jpg', 'https://info.hoasengroup.vn/vi/trang-chu/', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `id_loai_san_pham` int(11) NOT NULL,
  `id_thuong_hieu` int(11) NOT NULL,
  `ten_san_pham` varchar(200) NOT NULL,
  `mo_ta` varchar(5000) NOT NULL,
  `gia_tien` float NOT NULL,
  `giam_gia` float NOT NULL,
  `so_luong` int(11) NOT NULL,
  `hinh_anh` varchar(255) DEFAULT NULL,
  `luot_xem` int(11) DEFAULT '0',
  `luot_mua` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `id_loai_san_pham`, `id_thuong_hieu`, `ten_san_pham`, `mo_ta`, `gia_tien`, `giam_gia`, `so_luong`, `hinh_anh`, `luot_xem`, `luot_mua`) VALUES
(1, 1, 2, 'Gạch men lát nền giả gỗ GR05', 'là loại gạch men sử dụng để lát nền với màu sắc và họa tiết giả gỗ. Được làm từ chất liệu men sứ cao cấp, đảm bảo độ bền cao và khả năng chịu lực tốt.\r\n\r\nKích thước của gạch GR05 thường là 60x60 cm, độ dày khoảng 8-10 mm. Với họa tiết giả gỗ, gạch GR05 tạo nên vẻ đẹp tự nhiên, ấm cúng và sang trọng cho không gian sử dụng.\r\n\r\nGạch men lát nền giả gỗ GR05 được sử dụng phổ biến trong các công trình xây dựng như: phòng khách, phòng ngủ, nhà hàng, khách sạn, quán cafe, trung tâm thương mại,...', 200000, 30, 75, '1.jpg', 25, 25),
(2, 1, 2, 'Gạch men bóng kính 60x60 VJ-66018', 'à một sản phẩm gạch men có kích thước lớn 60x60cm, bề mặt được thiết kế bóng kính tạo ra vẻ đẹp sang trọng, sáng bóng. Gạch men VJ-66018 thường được sử dụng để lát sàn trong các không gian như phòng khách, phòng ăn, nhà bếp, nhà tắm, cửa ra vào, nhà hàng, khách sạn, văn phòng... Sản phẩm được sản xuất bằng công nghệ hiện đại, đảm bảo chất lượng, độ bền và độ chịu lực tốt. Ngoài ra, với khả năng chống thấm, chống trơn trượt, dễ vệ sinh và bảo dưỡng, gạch men bóng kính VJ-66018 là sự lựa chọn lý tưởng cho các công trình xây dựng và nội thất.', 100000, 20, 97, '2.jpg', 1, 3),
(3, 1, 2, 'Gạch Porcerlain kim cương CMC KC89004', 'là loại gạch ốp lát có bề mặt sáng bóng, kích thước 60x60 cm. Được sản xuất bằng công nghệ tiên tiến, gạch này có độ cứng và độ bền cao, chịu được áp lực và ma sát mạnh mẽ.\r\n\r\nBề mặt gạch Porcelain kim cương CMC KC89004 được tráng men sáng bóng, mang lại cảm giác sang trọng và hiện đại cho không gian. Màu sắc của gạch là màu trắng pha chút xám, tạo nên sự đơn giản nhưng đẳng cấp cho không gian.\r\n\r\nGạch Porcelain kim cương CMC KC89004 thường được sử dụng để lát sàn và tường trong các không gian sang trọng như phòng khách, phòng ngủ, nhà hàng, khách sạn, văn phòng...', 200000, 15, 99, '3.jpg', 1, 1),
(4, 1, 2, 'Gạch ốp lát 60x60 giả đá hoa cương', 'là một loại gạch men được thiết kế và sản xuất với mục đích tái tạo hoa cương tự nhiên. Sản phẩm này thường được sử dụng để ốp lát các mặt ngoài và nội thất trong các công trình xây dựng, như sàn nhà, bức tường, vỉa hè, hành lang, nhà tắm, bếp, phòng khách, phòng ngủ, v.v.\r\n\r\nGạch ốp lát giả đá hoa cương có kích thước thông dụng là 60x60cm và độ dày từ 8-10mm. Sản phẩm được sản xuất với độ chính xác cao để tạo ra các viên gạch giả đá hoa cương đồng nhất về kích thước và màu sắc. Gạch được thiết kế với các họa tiết và đường vân tự nhiên để tái tạo lại vẻ đẹp tự nhiên của đá hoa cương. Bề mặt của gạch được xử lý mịn, có độ bóng nhẹ để tạo ra cảm giác sang trọng, đẳng cấp cho không gian sử dụng.\r\n\r\nGạch ốp lát giả đá hoa cương có đặc tính kháng nước, chống trơn trượt, chịu lực tốt, dễ dàng vệ sinh và bảo trì. Sản phẩm này được sản xuất với công nghệ tiên tiến, đảm bảo chất lượng và độ bền cao trong quá trình sử dụng.', 100000, 35, 100, '4.jpg', 1, 0),
(5, 1, 2, 'Gạch Mosaic Thủy Tinh MS48056', 'à loại gạch mosaic được làm từ chất liệu thủy tinh, với kích thước mỗi viên gạch là 48mm x 48mm và độ dày là 8mm. Gạch có màu sắc đa dạng với nhiều mẫu mã khác nhau, được sắp xếp trên một tấm lưới nhỏ để dễ dàng lát và tạo nên các họa tiết phong phú trên bề mặt. Loại gạch này thường được sử dụng để trang trí các bề mặt tường, sàn, các vách ngăn hoặc trang trí nội thất, tạo nên một không gian độc đáo và hiện đại. Gạch Mosaic Thủy Tinh MS48056 thường được sử dụng trong các công trình thiết kế nội thất cao cấp, các khách sạn, nhà hàng, phòng tắm hoặc phòng khách sang trọng.', 100000, 35, 100, '5.jpg', 1, 0),
(6, 11, 2, 'Ngói tráng men Marusugi Eagle-Exa', 'Ngói tráng men Marusugi còn gọi là ngói Kent, là loại ngói tráng men cao cấp được nhập khẩu nguyên đai, nguyên kiện từ Nhật Bản. Sản phẩm được thiết kế tinh xảo với các gờ chắn nước, móc khóa ngói, lớp men cao cấp không phai màu. Ngói tráng men Marusugi còn gọi là ngói Kent, là loại ngói tráng men cao cấp được nhập khẩu nguyên đai, nguyên kiện từ Nhật Bản. Sản phẩm được thiết kế tinh xảo với các gờ chắn nước, móc khóa ngói, lớp men cao cấp không phai màu.\r\n\r\n', 150000, 70, 100, 'a6.jpg', 1, 0),
(7, 11, 2, 'Ngói Nakamura N09 sóng nhỏ', ' Ngói Nhật Nakamura N09 được sản xuất bởi dây chuyền công nghệ hiện đại (Sơn Nano Silicon - Bê tông cốt sợi) của Nhật Bản nên chất lượng viên ngói đồng nhất và hoàn hảo.', 200000, 20, 100, 'a7.jpg', 0, 0),
(8, 11, 2, 'Ngói sóng lớn', 'là loại ngói truyền thống được làm từ đất sét và được nung ở nhiệt độ cao để tạo nên tính chất chịu nhiệt và chống thấm tốt. Nó có hình dạng là hình sóng, được thiết kế để phủ lên mái nhà hoặc các bề mặt có hình dạng khác.\r\n\r\nNgói sóng lớn thường có kích thước lớn hơn các loại ngói sóng khác, tạo ra hiệu ứng thẩm mỹ độc đáo trên mái nhà. Nó cũng có tính năng bảo vệ tốt cho ngôi nhà khỏi tác động của thời tiết bên ngoài như mưa, gió, nắng, băng tuyết, đá,...\r\n\r\nNgoài ra, ngói sóng lớn còn được sử dụng để trang trí các bề mặt tường và vách ngăn trong các công trình xây dựng.', 250000, 0, 98, '6.jpg', 2, 2),
(9, 6, 3, 'Bông thủy tinh', ' là một loại vật liệu cách nhiệt và cách âm được làm từ các sợi thủy tinh rất mỏng và linh hoạt. Bông thủy tinh thường được sử dụng để giảm tiếng ồn và điều hòa nhiệt độ trong các công trình xây dựng như nhà ở, tòa nhà, nhà máy, khu công nghiệp và các phương tiện giao thông. Nó có khả năng chịu nhiệt và chống cháy tốt, đồng thời không bị rỉ sét, mục nát hoặc bị ăn mòn trong quá trình sử dụng. Bông thủy tinh cũng được sử dụng rộng rãi trong các sản phẩm gia dụng như tấm lót sàn, tấm lót tường, nệm, gối, rèm cửa để tăng tính cách âm và giảm tiếng ồn.', 100000, 10, 0, '39.jpg', 3, 100),
(10, 7, 1, 'Thạch cao cách âm cách nhiệt', 'là một loại vật liệu xây dựng được sử dụng để cách âm, cách nhiệt hoặc trang trí trong các công trình xây dựng. Thạch cao được làm từ hỗn hợp các chất khoáng như thạch anh, vôi, đá vôi và nước. Thạch cao có khả năng chịu lực tốt, chịu được nhiệt độ cao và khả năng cách âm, cách nhiệt tốt, giúp giảm thiểu tiếng ồn và giữ nhiệt trong không gian. Thạch cao cũng được sử dụng để trang trí nội thất, tạo ra các bức tường giả và trần nhà với nhiều hình dáng và kiểu dáng khác nhau.', 100000, 10, 100, '40.jpg', 0, 0),
(11, 8, 6, 'Sàn nhựa vinyl', 'là loại sàn được làm từ nhựa PVC, có độ dày từ 2mm đến 8mm. Với các tính năng chống thấm, chống trầy xước, chống trơn trượt, dễ vệ sinh và bảo trì, sàn nhựa vinyl thường được sử dụng trong nhiều không gian khác nhau như nhà ở, văn phòng, khách sạn, nhà hàng, bệnh viện, trường học và các khu thương mại. Ngoài ra, sàn nhựa vinyl còn có đa dạng về mẫu mã, kiểu dáng và màu sắc, giúp người dùng lựa chọn phù hợp với nhu cầu và phong cách thiết kế của mình.', 1000000, 1, 100, '41.jpg', 0, 0),
(12, 8, 2, 'Sàn đá tự nhiên', 'là một loại sàn được làm từ đá thiên nhiên như đá granite, đá marble, đá basalt, đá travertine, vv. Các viên đá được đánh bóng và cắt thành các kích thước và hình dạng khác nhau để lắp đặt thành một sàn nhà hoàn chỉnh. Sàn đá tự nhiên có độ bền cao, độ cứng và chịu lực tốt, không bị trầy xước và chịu được va đập. Ngoài ra, đá tự nhiên còn có đặc tính cách nhiệt và cách âm, giúp giữ cho nhiệt độ và âm thanh không truyền qua được từ sàn này sang sàn khác. Sàn đá tự nhiên cũng có độ bền màu tốt và dễ vệ sinh, đặc biệt phù hợp với các không gian sang trọng và hiện đại như phòng khách, phòng ăn, phòng tắm, vv.', 100000, 0, 100, '43.jpg', 0, 0),
(13, 8, 2, 'Sàn đá tự nhiên', 'là một loại sàn được làm từ đá thiên nhiên như đá granite, đá marble, đá basalt, đá travertine, vv. Các viên đá được đánh bóng và cắt thành các kích thước và hình dạng khác nhau để lắp đặt thành một sàn nhà hoàn chỉnh. Sàn đá tự nhiên có độ bền cao, độ cứng và chịu lực tốt, không bị trầy xước và chịu được va đập. Ngoài ra, đá tự nhiên còn có đặc tính cách nhiệt và cách âm, giúp giữ cho nhiệt độ và âm thanh không truyền qua được từ sàn này sang sàn khác. Sàn đá tự nhiên cũng có độ bền màu tốt và dễ vệ sinh, đặc biệt phù hợp với các không gian sang trọng và hiện đại như phòng khách, phòng ăn, phòng tắm, vv.', 100000, 0, 100, '43.jpg', 0, 0),
(14, 8, 7, 'Sàn gỗ công nghiệp', ' là loại sàn được sản xuất từ bột gỗ và keo ép lại với nhau bằng áp lực và nhiệt độ cao để tạo ra một tấm ván có độ dày và độ bền tương đương với sàn gỗ tự nhiên. Sàn gỗ công nghiệp có nhiều màu sắc và kiểu dáng khác nhau, có thể giả gỗ tự nhiên hoặc có hoa văn đa dạng. Sàn gỗ công nghiệp thường được sử dụng trong nội thất nhà ở, văn phòng, cửa hàng, khách sạn và các khu vực công cộng khác. Nó được đánh giá là đáng tin cậy, dễ lắp đặt, dễ dàng bảo trì và có tuổi thọ lâu dài.', 1000000, 2, 100, '42.jpg', 0, 0),
(15, 8, 7, 'Sàn gỗ tự nhiên', ' là loại sàn được sản xuất từ bột gỗ và keo ép lại với nhau bằng áp lực và nhiệt độ cao để tạo ra một tấm ván có độ dày và độ bền tương đương với sàn gỗ tự nhiên. Sàn gỗ công nghiệp có nhiều màu sắc và kiểu dáng khác nhau, có thể giả gỗ tự nhiên hoặc có hoa văn đa dạng. Sàn gỗ công nghiệp thường được sử dụng trong nội thất nhà ở, văn phòng, cửa hàng, khách sạn và các khu vực công cộng khác. Nó được đánh giá là đáng tin cậy, dễ lắp đặt, dễ dàng bảo trì và có tuổi thọ lâu dài.', 1000000, 2, 100, '42.jpg', 0, 0),
(16, 13, 1, 'Đá 4x6', 'là loại đá được cắt thành các tấm có kích thước đều 4x6 inch (khoảng 10x15cm). Đá 4x6 thường được sử dụng để lát sân, lối đi hoặc tạo mảng trang trí ngoài trời. Nó có nhiều loại đá khác nhau như đá granit, đá vôi, đá cẩm thạch, đá bazan,...và có đặc tính chịu mài mòn và chịu được sức nặng lớn, thích hợp cho các khu vực có tần suất đi lại cao hoặc cần sử dụng trong môi trường khắc nghiệt.', 1000, 0, 100, '44.jpg', 0, 0),
(17, 13, 1, 'Đá 4x6', 'là loại đá được cắt thành các tấm có kích thước đều 4x6 inch (khoảng 10x15cm). Đá 4x6 thường được sử dụng để lát sân, lối đi hoặc tạo mảng trang trí ngoài trời. Nó có nhiều loại đá khác nhau như đá granit, đá vôi, đá cẩm thạch, đá bazan,...và có đặc tính chịu mài mòn và chịu được sức nặng lớn, thích hợp cho các khu vực có tần suất đi lại cao hoặc cần sử dụng trong môi trường khắc nghiệt.', 1000, 0, 100, '44.jpg', 1, 0),
(18, 13, 2, 'Đá 1x2', 'à loại đá tự nhiên có kích thước bề mặt bằng 1 inch x 2 inch (khoảng 2.54 cm x 5.08 cm). Đá 1x2 thường được sử dụng để lát nền, làm mặt tiền hoặc trang trí tường, tạo điểm nhấn cho không gian kiến trúc. Chúng có nhiều màu sắc và kiểu dáng khác nhau, từ những chiếc đá nhỏ li ti đến những viên đá lớn hơn. Thông thường, đá 1x2 được sản xuất từ đá granit, đá vôi, đá bazan, đá cuội và có độ bền cao, độ cứng tốt, độ bóng sáng và độ chịu nhiệt tốt.', 1000000, 1, 100, '45.jpg', 0, 0),
(19, 13, 2, 'Đá 1x2', 'à loại đá tự nhiên có kích thước bề mặt bằng 1 inch x 2 inch (khoảng 2.54 cm x 5.08 cm). Đá 1x2 thường được sử dụng để lát nền, làm mặt tiền hoặc trang trí tường, tạo điểm nhấn cho không gian kiến trúc. Chúng có nhiều màu sắc và kiểu dáng khác nhau, từ những chiếc đá nhỏ li ti đến những viên đá lớn hơn. Thông thường, đá 1x2 được sản xuất từ đá granit, đá vôi, đá bazan, đá cuội và có độ bền cao, độ cứng tốt, độ bóng sáng và độ chịu nhiệt tốt.', 1000000, 1, 100, '45.jpg', 0, 0),
(20, 13, 3, 'Đá Hộc', 'là một loại đá tự nhiên có xuất xứ từ Hà Nam, Việt Nam. Đá Hộc có màu sắc đa dạng, từ xám đến trắng, có độ bóng cao và độ cứng rất tốt. Do đó, nó thường được sử dụng trong các công trình xây dựng để làm mặt tiền, lan can, sàn, tường và các vật liệu trang trí khác. Ngoài ra, đá Hộc còn được sử dụng để sản xuất đồ trang trí nội thất như bàn, ghế, đồ dùng trong nhà bếp, phòng tắm vì tính thẩm mỹ và độ bền của nó.', 1000, 0, 100, '46.jpg', 0, 0),
(21, 13, 3, 'Đá Hộc', 'là một loại đá tự nhiên có xuất xứ từ Hà Nam, Việt Nam. Đá Hộc có màu sắc đa dạng, từ xám đến trắng, có độ bóng cao và độ cứng rất tốt. Do đó, nó thường được sử dụng trong các công trình xây dựng để làm mặt tiền, lan can, sàn, tường và các vật liệu trang trí khác. Ngoài ra, đá Hộc còn được sử dụng để sản xuất đồ trang trí nội thất như bàn, ghế, đồ dùng trong nhà bếp, phòng tắm vì tính thẩm mỹ và độ bền của nó.', 1000, 0, 100, '46.jpg', 0, 0),
(22, 13, 8, 'Đá chẻ tự nhiên', 'là loại đá được sản xuất bằng cách chia đá lớn thành những miếng nhỏ hình vuông, hình chữ nhật hoặc hình tam giác thông qua quá trình đánh bóng, đánh bóng hoặc cắt. Các mảnh đá được chia tách theo hướng tương ứng với độ dày và chiều dài yêu cầu, tạo ra những mặt phẳng đẹp và chân thực. Đá chẻ tự nhiên được sử dụng rộng rãi trong kiến trúc để trang trí các tòa nhà, đường phố, công viên và các khu vực khác, tạo nên vẻ đẹp tự nhiên và sang trọng cho các công trình xây dựng.', 100000, 0, 100, '47.jpg', 0, 0),
(23, 13, 8, 'Đá chẻ tự nhiên', 'là loại đá được sản xuất bằng cách chia đá lớn thành những miếng nhỏ hình vuông, hình chữ nhật hoặc hình tam giác thông qua quá trình đánh bóng, đánh bóng hoặc cắt. Các mảnh đá được chia tách theo hướng tương ứng với độ dày và chiều dài yêu cầu, tạo ra những mặt phẳng đẹp và chân thực. Đá chẻ tự nhiên được sử dụng rộng rãi trong kiến trúc để trang trí các tòa nhà, đường phố, công viên và các khu vực khác, tạo nên vẻ đẹp tự nhiên và sang trọng cho các công trình xây dựng.', 100000, 0, 100, '47.jpg', 0, 0),
(24, 13, 3, 'Đá Granite Trắng Vân Gỗ', 'là một loại đá tự nhiên với màu trắng pha chút xám, mang lại vẻ đẹp sang trọng và độc đáo cho bất kỳ không gian nào. Nó có các vân gỗ đặc trưng và có độ cứng cao, chịu mài mòn tốt, chịu nhiệt độ cao và kháng axit, thích hợp cho việc sử dụng trong các không gian ngoại thất, bếp và phòng tắm. Ngoài ra, đá Granite Trắng Vân Gỗ còn được sử dụng để làm mặt bàn, bậc cầu thang, trang trí và ốp lát tường.', 1000000, 0, 100, '48.jpg', 1, 0),
(25, 13, 3, 'Đá Granite Trắng Vân Gỗ', 'là một loại đá tự nhiên với màu trắng pha chút xám, mang lại vẻ đẹp sang trọng và độc đáo cho bất kỳ không gian nào. Nó có các vân gỗ đặc trưng và có độ cứng cao, chịu mài mòn tốt, chịu nhiệt độ cao và kháng axit, thích hợp cho việc sử dụng trong các không gian ngoại thất, bếp và phòng tắm. Ngoài ra, đá Granite Trắng Vân Gỗ còn được sử dụng để làm mặt bàn, bậc cầu thang, trang trí và ốp lát tường.', 1000000, 0, 100, '48.jpg', 1, 0),
(29, 1, 2, 'Gạch không nung 4 lỗ', 'là loại gạch được sản xuất bằng cách trộn cát, xi măng và nước, sau đó ép thành dạng viên gạch. Khác với gạch nung, gạch không nung không cần phải được đốt trong lò nung, do đó nó tiết kiệm năng lượng sản xuất hơn. Gạch không nung thường được sử dụng trong xây dựng công trình dân dụng như nhà ở, tường rào và các công trình xây dựng khác. Gạch không nung 4 lỗ có kích thước phổ biến là 10x20cm và 20x20cm, với các lỗ vuông ở giữa để giảm trọng lượng và tăng tính cách âm, cách nhiệt của gạch. Ngoài ra, gạch không nung 4 lỗ cũng có độ bền cao và dễ dàng lắp đặt.', 1000000, 0, 100, '7.jpg', 0, 0),
(31, 1, 2, 'Gạch ống 6 lỗ ', 'là loại gạch có hình dạng dài hình ống, có 6 lỗ tròn chạy dọc theo chiều dài của gạch. Loại gạch này thường được sử dụng trong các công trình xây dựng như làm hàng rào, tường hoặc các công trình cấp nước, thoát nước. Gạch ống 6 lỗ thường được sản xuất từ đất sét hoặc bột đá, sau đó được đốt trong lò nung ở nhiệt độ cao để có độ cứng và độ bền tốt. Gạch ống 6 lỗ có nhiều màu sắc và kiểu dáng khác nhau để phù hợp với nhiều kiểu thiết kế khác nhau.', 100000, 0, 100, '8.jpg', 0, 0),
(32, 15, 2, 'Gạch block xây tường R150', 'là một loại gạch được làm từ xi măng và cát, có kích thước lớn hơn các loại gạch thông thường để xây dựng các tường chắc chắn và bền vững. Kích thước thông thường của gạch block R150 là 390mm x 190mm x 140mm, tương đương với 15.35 inch x 7.48 inch x 5.51 inch. Gạch block R150 có khả năng chịu lực và chống chịu nước tốt, được sử dụng rộng rãi trong xây dựng các công trình dân dụng và công nghiệp.', 300000, 0, 100, '9.jpg', 0, 0),
(33, 15, 2, 'Gạch Block xây tường R190', 'là loại gạch block được sử dụng trong xây dựng tường, trụ, cột, móng nhà, vách ngăn, tường chắn gió...với khả năng chịu lực cao. Kích thước của gạch block R190 là 19x20x39 (cm), có khối lượng trung bình từ 18-20 kg/ viên.\r\n\r\nGạch block R190 được sản xuất từ xi măng, cát, đá granit, nước và chất phụ gia giúp tăng độ bền, chịu lực và chống thấm. Với bề mặt phẳng và chính xác, gạch block R190 được thiết kế với lỗ trống giúp dễ dàng trong việc xây dựng, giảm thiểu thời gian thi công và tiết kiệm chi phí.\r\n\r\nGạch Block xây tường R190 được sử dụng phổ biến trong các công trình xây dựng dân dụng, công nghiệp, cầu đường, cảng biển, sân bay... do có độ bền cao và khả năng chịu lực tốt.', 1500, 0, 100, '10.jpg', 0, 0),
(34, 15, 2, 'Gạch Block xây tường R90', 'Đá xây dựng chất lượng cao của Dong Tam', 800000, 0, 100, '11.jpg', 0, 0),
(35, 2, 2, 'Xi măng trắng SCG Thái Lan PCW50', 'là loại xi măng trắng được sản xuất bởi tập đoàn SCG đến từ Thái Lan. Đây là loại xi măng chuyên dùng cho công trình xây dựng, được sản xuất từ các nguyên liệu đạt tiêu chuẩn cao và được kiểm tra chất lượng nghiêm ngặt để đảm bảo tính ổn định và độ bền cao trong quá trình sử dụng. Xi măng trắng SCG Thái Lan PCW50 thường được sử dụng để trộn với cát, nước và các vật liệu xây dựng khác để tạo ra bê tông trắng hoặc các sản phẩm xây dựng khác có màu trắng.', 100000, 0, 100, '12.jpg', 0, 0),
(37, 2, 2, 'Xi măng Kim Đỉnh PCB40', 'là loại xi măng Portland composite đặc biệt được sản xuất bởi Tập đoàn Xi măng Việt Nam. Xi măng PCB40 có tỉ trọng nhẹ, độ bền cao và khả năng chống thấm tốt. Nó được sử dụng chủ yếu trong các công trình xây dựng như cột, sàn, móng, tường, trần và đường bê tông. Xi măng PCB40 cũng có khả năng tạo ra bề mặt hoàn thiện sáng bóng và đẹp mắt.', 1500000, 0, 100, '13.jpg', 0, 0),
(38, 2, 2, 'Xi măng Sông Gianh PC30', ' ra một sản phẩm xi măng có độ bền cao và đáp ứng các tiêu chuẩn kỹ thuật cần thiết. Xi măng Sông Gianh PC30 có ứng dụng chính trong xây dựng các công trình dân dụng và công nghiệp. Sản phẩm này thường được sử dụng để xây dựng các công trình như nhà ở, tường rào, đường ống, cầu đường và các công trình khác. Xi măng Sông Gianh PC30 được sản xuất bởi công ty Xi măng Sông Gianh thuộc Tập đoàn xi măng Vicem.', 120000, 0, 100, '14.jpg', 0, 0),
(39, 2, 2, 'Xi măng Sông Gianh PCB40', 'là sản phẩm xi măng Portland composite loại 40 được sản xuất bởi Công ty Cổ phần Xi măng Sông Gianh. Đây là loại xi măng chứa phụ gia hỗn hợp giữa xỉ lò, tro bay và đá vôi, giúp tăng độ bền và khả năng chịu lực cho sản phẩm xi măng. Xi măng Sông Gianh PCB40 thường được sử dụng trong xây dựng các công trình dân dụng, công nghiệp và giao thông.', 200000, 0, 100, '15.jpg', 0, 0),
(40, 6, 2, 'Xi măng Kim Đỉnh PC40', 'là loại xi măng cao cấp được sản xuất bởi Tổng công ty Xi măng và Vật liệu Xây dựng (VICEM). Xi măng Kim Đỉnh PC40 có độ bền cao, độ dính kết cứng tốt và độ bền nén cao, được sử dụng rộng rãi trong các công trình xây dựng dân dụng và công nghiệp. Ngoài ra, xi măng Kim Đỉnh PC40 cũng được sử dụng để sản xuất các sản phẩm xi măng như gạch, bê tông, cốt thép, tường chắn nước, tấm ốp lát, đường bê tông và cầu đường.', 500000, 0, 100, '16.jpg', 0, 0),
(42, 8, 2, 'Tấm bê tông nhẹ đúc sẵn Duraflex', 'là sản phẩm được làm từ bê tông nhẹ, có độ bền cao và khả năng chống chịu tốt với thời tiết khắc nghiệt. Tấm bê tông Duraflex thường được sử dụng để lát nền cho các công trình xây dựng như sân vườn, hành lang, lối đi, bãi đỗ xe, hồ bơi, sân tennis, sân bóng đá mini,... Tấm bê tông Duraflex có nhiều kích thước và màu sắc khác nhau để phù hợp với các yêu cầu khác nhau của khách hàng. Sản phẩm này còn được đánh giá là có độ bền cao, chống thấm nước, kháng mài mòn và không bị ảnh hưởng bởi môi trường xung quanh.', 180000, 0, 100, '17.jpg', 0, 0),
(45, 3, 2, 'Tấm bê tông nhẹ đúc sẵn Cemboard', 'là một loại vật liệu xây dựng được làm từ xi măng, sợi cellulose, các chất phụ gia và nước. Các thành phần này được pha trộn với nhau và đúc thành tấm bê tông nhẹ có độ dày và kích thước khác nhau. Tấm bê tông nhẹ đúc sẵn Cemboard thường được sử dụng làm vật liệu xây dựng cho các tường chắn, sàn nhà, trần nhà, hoặc làm vật liệu cách âm, cách nhiệt, chống cháy. Các ưu điểm của tấm bê tông nhẹ đúc sẵn Cemboard bao gồm khả năng chịu lực tốt, không bị cháy và độ bền cao, đồng thời cũng rất dễ dàng để thi công và lắp đặt.', 250000, 0, 100, '18.jpg', 0, 0),
(46, 3, 2, 'Tấm sàn panel bê tông nhẹ đúc sẵn V-Lite', 'là sản phẩm được làm từ bê tông nhẹ, được đúc sẵn thành tấm có kích thước và hình dạng đồng đều, giúp việc lắp đặt trở nên nhanh chóng và dễ dàng hơn. V-Lite thường được sử dụng để làm sàn cho các công trình dân dụng và công nghiệp, có khả năng chịu tải trọng tốt và cách âm, cách nhiệt tốt, giúp giảm thiểu tiếng ồn và tạo cảm giác thoải mái cho người sử dụng. Với độ bền cao, sản phẩm này còn giúp tăng độ an toàn cho công trình và tiết kiệm chi phí bảo trì, sửa chữa trong tương lai.', 200000, 0, 100, '19.jpg', 0, 0),
(48, 3, 2, 'Trụ Bê Tông Li Tâm 190mm', 'là một sản phẩm bê tông được thiết kế để sử dụng trong công trình xây dựng, thường được dùng để làm cột trong nhà, công trình dân dụng hoặc công nghiệp. \r\n\r\n\r\nTrụ này có đường kính bề mặt ngoài là 190mm, được gia cố bằng thép cốt để tăng tính chắc chắn và độ bền. Ngoài ra, trụ bê tông li tâm còn có tính năng chịu tải tốt, giúp truyền tải tải trọng từ phần trên xuống phần đế một cách hiệu quả.\r\n\r\n\r\nTrụ bê tông li tâm 190mm thường được sản xuất với nhiều chiều cao khác nhau để phù hợp với các yêu cầu của từng công trình.', 800000, 0, 100, '20.jpg', 3, 0),
(49, 1, 2, 'Cống bê tông li tâm', 'là một loại cống được sản xuất từ bê tông có cấu trúc li tâm, có đường kính lớn hơn 1 mét, được sử dụng để thoát nước, dẫn nước mưa và nước thải trong các hạng mục công trình xây dựng như đường, cầu, khu đô thị, nhà máy và xí nghiệp. Cống bê tông li tâm có khả năng chịu lực tốt, độ bền cao và không bị ảnh hưởng bởi môi trường khắc nghiệt. Ngoài ra, cống bê tông li tâm còn có khả năng chống ăn mòn, chịu được lực va đập, không bị thấm nước và đảm bảo an toàn cho môi trường xung quanh.', 50000, 0, 100, '21.jpg', 0, 0),
(50, 4, 2, 'Ván coppha gỗ phủ phim 15 mm', 'là một loại ván ép được sản xuất từ gỗ tự nhiên sau đó được phủ lớp phim chống thấm và chống trượt. Ván coppha thường được sử dụng trong các công trình xây dựng như làm sàn, làm vách ngăn, tường chắn, ốp trần, làm kệ để đồ vật, cọc cờ, bộ đếm, khuôn mẫu, v.v. Ván coppha gỗ phủ phim có độ dày từ 4mm đến 25mm, tuy nhiên 15mm là kích thước phổ biến nhất.', 250000, 0, 100, '22.jpg', 0, 0),
(51, 4, 2, 'Sàn gỗ', 'là loại vật liệu được làm từ các thanh gỗ tự nhiên, thường được sử dụng để lát nền trong các công trình xây dựng, nhà ở, căn hộ, văn phòng và các không gian thương mại. Sàn gỗ có nhiều ưu điểm như độ bền cao, dễ dàng lắp đặt, có thể sửa chữa khi bị hư hỏng và mang lại cảm giác ấm áp, sang trọng cho không gian sử dụng. Ngoài ra, sàn gỗ còn có thể được sản xuất từ các loại gỗ tự nhiên khác nhau để phù hợp với yêu cầu của từng loại công trình và phong cách thiết kế.', 350000, 0, 100, '23.jpg', 0, 0),
(52, 4, 2, 'Gỗ sấy', 'là gỗ được đưa vào quá trình sấy khô bằng các phương pháp công nghệ hiện đại như sấy bằng khí nóng, sấy bằng điện, sấy bằng tia cực tím... để giảm độ ẩm và tăng tính ổn định của gỗ. Quá trình sấy khô giúp giảm độ co giãn, cong vênh, nứt nẻ của gỗ, từ đó nâng cao chất lượng và giá trị sử dụng của sản phẩm từ gỗ. Gỗ sấy còn được sử dụng phổ biến trong các ngành công nghiệp sản xuất đồ nội thất, cửa sổ, cửa ra vào, sàn nhà, ván ép...', 550000, 0, 100, '24.jpg', 0, 0),
(53, 4, 2, 'Gỗ ghép', 'là kỹ thuật kết hợp nhiều tấm gỗ nhỏ thành một tấm gỗ lớn hơn thông qua quá trình ép nhiệt và ép tạo áp lực cao. Quá trình này giúp giảm thiểu việc sử dụng gỗ tự nhiên, tối ưu hóa tài nguyên và đảm bảo tính ổn định của tấm gỗ. Gỗ ghép có thể được sử dụng để sản xuất đồ nội thất, ván sàn, tấm ván ép và các sản phẩm gỗ khác.', 120000, 0, 100, '25.jpg', 0, 0),
(54, 4, 2, 'Gỗ Coppa', ' là loại gỗ được sản xuất bằng cách ghép nhiều mảnh gỗ nhỏ lại với nhau để tạo thành tấm gỗ lớn hơn. Gỗ Coppa thường được sử dụng để làm ván ép, ván ghép, ván dán và các sản phẩm nội thất khác. Nó có độ bền cao, độ đàn hồi tốt và khó bị cong vênh, đặc biệt là khi tiếp xúc với nước hoặc độ ẩm cao. Gỗ Coppa còn được ưa chuộng vì giá thành rẻ hơn so với các loại gỗ tự nhiên khác như sồi, dái ngựa, teak, walnut, maple...', 12000000, 0, 100, '26.jpg', 1, 0),
(55, 4, 2, 'Ván cốp pha gỗ phủ phim 12mm', ' là một loại vật liệu xây dựng được làm từ bột gỗ và keo ép lại với nhau bằng cách nén và phủ một lớp phim trên mặt để tăng tính chịu nước, chống mối mọt và bảo vệ bề mặt. Ván cốp pha có độ bền cao, độ chịu lực và độ bền vượt trội so với các vật liệu khác, thường được sử dụng trong các công trình xây dựng như làm sàn nhà, tường, trần, cửa, cột và kết cấu nội thất.', 11000000, 0, 100, '27.jpg', 0, 0),
(56, 11, 2, 'Ngói NS-02', 'là loại ngói lợp được sản xuất từ bột đá granit, bột đá vôi, xi măng, nước và các phụ gia khác. Ngói NS-02 có kích thước tiêu chuẩn là 420 x 330 mm, dày 14mm và có trọng lượng khoảng 3kg/viên. Loại ngói này thường được sử dụng để lợp mái nhà, đặc biệt là ở các vùng có khí hậu nóng ẩm hoặc thời tiết khắc nghiệt, bởi vì nó có tính năng chống chịu thời tiết và chống thấm tốt. Ngoài ra, ngói NS-02 còn có tính năng cách âm, cách nhiệt và giữ được màu sắc bền đẹp theo thời gian sử dụng.', 50000, 0, 100, '28.jpg', 0, 0),
(57, 11, 2, 'Ngói màu SJVC DT-07', 'là một loại ngói lợp mái được sản xuất từ đất sét và sử dụng công nghệ nung ở nhiệt độ cao, đảm bảo độ bền và độ chịu nước tốt. Ngói có hình dáng truyền thống, màu sắc đa dạng và đẹp mắt, thích hợp cho các công trình xây dựng nhà ở, biệt thự, khách sạn, trường học, nhà máy, xí nghiệp, v.v. Ngói màu Sao Việt Nhật SJVC DT-07 còn được đánh giá cao về khả năng chống chịu các tác động từ môi trường như gió, mưa, nắng, ẩm ướt, sương muối, v.v.', 250000, 0, 100, '29.jpg', 0, 0),
(58, 11, 2, 'NGÓI LẤY SÁNG', 'là loại ngói được thiết kế với mục đích cho phép ánh sáng tự nhiên xuyên qua vào bên trong không gian, giúp giảm thiểu sự lãng phí năng lượng trong việc chiếu sáng bằng đèn điện và tạo ra không gian sống thoáng đãng, rộng rãi hơn. Ngói lấy sáng có thể được làm từ nhiều loại vật liệu khác nhau như làm từ nhựa, composite hay kính. Việc sử dụng ngói lấy sáng trong kiến trúc hiện đại là một xu hướng phổ biến, giúp tối ưu hóa không gian, tiết kiệm năng lượng và tạo ra môi trường sống thoải mái, tiện nghi cho con người.', 320000, 0, 100, '30.jpg', 0, 0),
(59, 11, 2, 'Ngói lợp NLL S706', 'là loại ngói lợp được sản xuất từ chất liệu đất sét và được nung ở nhiệt độ cao, có đặc tính chống thấm và chống chịu mài mòn tốt. Ngói lợp này có hình dáng chữ S, có khả năng gắn kết tốt giữa các tấm ngói với nhau, giúp đảm bảo tính chắc chắn và độ bền của mái nhà. Ngói lợp Đồng Tâm NLL S706 được sử dụng phổ biến trong các công trình xây dựng nhà ở, biệt thự, khách sạn, khu nghỉ dưỡng...', 800000, 0, 100, '31.jpg', 0, 0),
(60, 5, 2, 'Đồng thau', 'là một loại kim loại có màu vàng nâu đỏ và được sử dụng trong nhiều ứng dụng khác nhau, bao gồm trong sản xuất đồ gia dụng, điện tử, đồng hồ, đồ trang sức, bánh xe và bộ phận máy móc. Ngoài ra, đồng thau còn được sử dụng rộng rãi trong ngành xây dựng để làm vật liệu cách nhiệt, ống dẫn nước, mái che và trang trí nội thất. Đặc biệt, đồng thau còn được sử dụng để sản xuất tiền xu và hạt nhân của một số loại đạn.', 60000, 0, 100, '32.jpg', 0, 0),
(61, 5, 2, 'Nhôm', 'là một kim loại nhẹ, có độ dẫn điện và nhiệt tốt, khá bền và ít bị ăn mòn. Nhôm được sử dụng rộng rãi trong sản xuất các vật dụng gia đình, đồ nội thất, đồ gia dụng, đồ dùng trong ngành xây dựng, ô tô, máy bay, điện tử và nhiều ngành công nghiệp khác. Ngoài ra, nhôm còn được sử dụng để sản xuất hợp kim nhôm, làm vật liệu cách nhiệt và ngăn chặn bức xạ, và cũng được sử dụng trong một số ứng dụng y tế.', 15000, 0, 100, '33.jpg', 0, 0),
(62, 5, 2, 'Sắt', 'là một kim loại dùng rất phổ biến trong ngành xây dựng, cơ khí, chế tạo và nhiều lĩnh vực khác. Sắt có tính chất cứng, dẻo, chịu lực tốt và dễ dàng gia công thành các hình dạng khác nhau. Sắt thường được sử dụng để làm khung xương, tấm lợp, cột, dầm trong công trình xây dựng. Sắt có nhiều loại, phổ biến nhất là sắt thép, sắt cuộn, sắt hình, sắt la, sắt U, sắt V.v...', 300000, 0, 100, '34.jpg', 0, 0),
(63, 5, 2, 'Inox', 'Inox là tên gọi chung của các loại thép không rỉ (hay còn gọi là thép không gỉ) có khả năng chống ăn mòn và không bị rỉ sét trong môi trường ẩm ướt. Inox được sản xuất bằng cách pha trộn các hợp kim sắt với các nguyên tố khác như Crom, Niken, Molypdenum, Titan, v.v.\r\n\r\n\r\nCác loại Inox thường được sử dụng trong công nghiệp chế tạo máy móc, sản xuất đồ gia dụng và trang trí nội thất, y tế và thực phẩm, cơ khí và đóng tàu, xây dựng và kiến ​​trúc, v.v. Inox có độ bóng sáng và sáng bóng cao, khả năng chịu nhiệt và mài mòn tốt, đồng thời có thể được đúc, hàn, gia công và tạo hình theo nhiều kiểu dáng khác nhau.\r\n\r\n\r\nCác loại Inox thường được đánh giá theo mức độ chống ăn mòn, độ cứng và độ dẻo. Các loại Inox phổ biến nhất là Inox 304, Inox 316 và Inox 430.', 20000, 0, 100, '35.jpg', 0, 0),
(64, 4, 2, 'Xốp cách âm', ' là loại vật liệu được sử dụng để giảm thiểu độ ồn và cách nhiệt cho các công trình xây dựng. Nó được sản xuất từ các loại vật liệu như polystyrene, polyurethane, rockwool, glasswool, hay cellulose. Xốp cách âm được ứng dụng rộng rãi trong các công trình xây dựng như nhà ở, văn phòng, hội trường, phòng thu âm, nhà máy sản xuất, phòng thí nghiệm và các công trình công nghiệp khác.', 10000, 0, 100, '36.jpg', 0, 0),
(65, 6, 2, 'Bông thủy tinh Glasswool', ' là một vật liệu cách nhiệt được sản xuất bằng cách ép nhiều sợi thủy tinh siêu mỏng và rải chúng thành một lớp dày. Với cấu trúc vô cùng nhỏ gọn, Glasswool có khả năng giảm thiểu đáng kể lượng nhiệt, âm thanh và tiếng ồn di chuyển qua lại giữa các khu vực, giúp giảm chi phí năng lượng và tạo ra một môi trường sống thoải mái, yên tĩnh và an toàn hơn. Glasswool thường được sử dụng trong việc cách nhiệt cho các công trình như nhà ở, tòa nhà, nhà xưởng, kho lạnh, và các phòng lab hay những nơi đòi hỏi tính bảo vệ cao.', 50000, 0, 100, '37.jpg', 0, 0),
(66, 5, 2, 'Túi khí cách âm', 'là một loại vật liệu cách âm được làm từ nhựa PVC, bên trong chứa khí và bột khoáng cách nhiệt. Túi khí cách âm được sử dụng để giảm tiếng ồn, cách nhiệt cho các công trình xây dựng như tường, trần, sàn, mái nhà. Túi khí cách âm có khả năng cách âm và cách nhiệt tốt, đặc biệt là trong việc giảm tiếng ồn từ các nguồn âm thanh bên trong và bên ngoài công trình. Nó có thể dễ dàng thi công và lắp đặt, tiết kiệm chi phí và thời gian thi công so với các vật liệu cách âm khác.\r\n\r\n\r\n\r\nlà một loại vật liệu cách âm được làm từ nhựa PVC, bên trong chứa khí và bột khoáng cách nhiệt. Túi khí cách âm được sử dụng để giảm tiếng ồn, cách nhiệt cho các công trình xây dựng như tường, trần, sàn, mái nhà. Túi khí cách âm có khả năng cách âm và cách nhiệt tốt, đặc biệt là trong việc giảm tiếng ồn từ các nguồn âm thanh bên trong và bên ngoài công trình. Nó có thể dễ dàng thi công và lắp đặt, tiết kiệm chi phí và thời gian thi công so với các vật liệu cách âm khác.\r\n\r\n\r\n\r\nlà một loại vật liệu cách âm được làm từ nhựa PVC, bên trong chứa khí và bột khoáng cách nhiệt. Túi khí cách âm được sử dụng để giảm tiếng ồn, cách nhiệt cho các công trình xây dựng như tường, trần, sàn, mái nhà. Túi khí cách âm có khả năng cách âm và cách nhiệt tốt, đặc biệt là trong việc giảm tiếng ồn từ các nguồn âm thanh bên trong và bên ngoài công trình. Nó có thể dễ dàng thi công và lắp đặt, tiết kiệm chi phí và thời gian thi công so với các vật liệu cách âm khác.\r\n\r\n\r\n\r\n\r\n\r\n', 300000, 0, 100, '38.jpg', 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongtindonhang`
--

CREATE TABLE `thongtindonhang` (
  `id` int(11) NOT NULL,
  `ten_khach_hang` varchar(100) NOT NULL,
  `dia_chi_nguoi_nhan` varchar(200) NOT NULL,
  `so_dien_thoai_nguoi_nhan` varchar(20) NOT NULL,
  `ngay_giao_hang` datetime NOT NULL,
  `phi_van_chuyen` float NOT NULL,
  `ghi_chu` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `thongtindonhang`
--

INSERT INTO `thongtindonhang` (`id`, `ten_khach_hang`, `dia_chi_nguoi_nhan`, `so_dien_thoai_nguoi_nhan`, `ngay_giao_hang`, `phi_van_chuyen`, `ghi_chu`) VALUES
(56, 'Vũ Hồng Ngọc', '456 Lê Văn Việt, Hà Nội', '0123456789', '0001-01-01 00:00:00', 25000, 'kHÔNG CÓ GHI CHÚ'),
(57, 'Vũ Hồng Ngọc', '456 Lê Văn Việt, Hà Nội', '0123456789', '0001-01-01 00:00:00', 25000, 'kHÔNG CÓ GHI CHÚ'),
(58, 'Lê Thanh Thảo', '456 Nguyễn Thị Minh Khai, Quận 3, TP Hồ Chí Minh', '0932123456', '0001-01-01 00:00:00', 25000, 'kHÔNG CÓ GHI CHÚ'),
(59, 'Lê Thanh Thảo', '456 Nguyễn Thị Minh Khai, Quận 3, TP Hồ Chí Minh', '0932123456', '0001-01-01 00:00:00', 25000, 'kHÔNG CÓ GHI CHÚ'),
(60, 'Lê Quỳnh Anh', '123 Lê Lợi, Thành phố Huế', '0865432109', '0001-01-01 00:00:00', 25000, 'kHÔNG CÓ GHI CHÚ00000'),
(63, 'Nguyen Van Aaa', 'Ha Noi', '0987654321', '0001-01-01 00:00:00', 25000, 'kHÔNG CÓ GHI CHÚ'),
(64, 'Le Thi C', 'Da Nang', '0912345678', '0001-01-01 00:00:00', 25000, 'kHÔNG CÓ GHI CHÚ'),
(65, 'Le Thi C', 'Da Nang', '0912345678', '0001-01-01 00:00:00', 25000, 'kHÔNG CÓ GHI CHÚ'),
(66, 'Le Thi C', 'Da Nang', '0912345678', '0001-01-01 00:00:00', 25000, 'kHÔNG CÓ GHI CHÚ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thuonghieu`
--

CREATE TABLE `thuonghieu` (
  `id` int(11) NOT NULL,
  `TenThuongHieu` varchar(50) DEFAULT NULL,
  `MoTa` varchar(255) DEFAULT NULL,
  `TrangWeb` varchar(100) DEFAULT NULL,
  `Logo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `thuonghieu`
--

INSERT INTO `thuonghieu` (`id`, `TenThuongHieu`, `MoTa`, `TrangWeb`, `Logo`) VALUES
(1, 'Vicostone', 'Công ty chuyên sản xuất và cung cấp đá nhân tạo, vật liệu xây dựng cao cấp', 'https://vicostone.com/', 'logo2.jpg'),
(2, 'LafargeHolcim', 'Công ty sản xuất xi măng và vật liệu xây dựng hàng đầu thế giới', 'https://LafargeHolcim.com/', 'logo3.jpg'),
(3, 'Aica', 'Công ty sản xuất và cung cấp các sản phẩm vật liệu xây dựng, nội thất và trang trí chất lượng cao', 'https://Aica.com/', 'logo4.jpg'),
(4, 'Knauf', 'Công ty sản xuất và cung cấp vật liệu xây dựng chất lượng cao', 'https://Knauf.com/', 'logo5.jpg'),
(5, 'Coteccons', 'Tập đoàn xây dựng hàng đầu Việt Nam', 'https://Coteccons.com/', 'logo6.jpg'),
(6, 'Vinaconex', 'Tổng công ty xây dựng Hà Nội - đơn vị hàng đầu trong lĩnh vực xây dựng tại Việt Nam', 'https://vinaconex.com/', 'logo7.jpg'),
(7, 'Tân Á Đại Thành', 'Công ty sản xuất vật liệu xây dựng đa dạng với nhiều sản phẩm chất lượng', 'https://tanadathanh.com/', 'logo9.jpg'),
(8, 'Dong Tam', 'Công ty sản xuất và cung cấp các sản phẩm gạch, đá xây dựng chất lượng cao', 'https://dongtamgroup.com/', 'logo8.jpg'),
(9, 'Nhựa Hòa Phát', 'Công ty sản xuất và cung cấp sản phẩm nhựa hàng đầu Việt Nam', 'https://nhuahoaphat.com.vn/', 'logo1.jpg');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_chitietdonhang_donhang` (`id_don_hang`),
  ADD KEY `FK_chitietdonhang_sanpham` (`id_san_pham`);

--
-- Chỉ mục cho bảng `danhgia`
--
ALTER TABLE `danhgia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_danhgia_nguoidung` (`id_nguoi_dung`),
  ADD KEY `FK_danhgia_sanpham` (`id_san_pham`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_donhang_nguoidung` (`id_nguoi_dung`);

--
-- Chỉ mục cho bảng `giohang`
--
ALTER TABLE `giohang`
  ADD PRIMARY KEY (`id_nguoi_dung`,`id_san_pham`),
  ADD KEY `FK_giohang_sanpham` (`id_san_pham`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_hoadon_donhang` (`id_don_hang`);

--
-- Chỉ mục cho bảng `lienhe`
--
ALTER TABLE `lienhe`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `phanhoi`
--
ALTER TABLE `phanhoi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_phanhoi_nguoidung` (`id_nguoi_dung`),
  ADD KEY `FK_phanhoi_danhgia` (`id_danh_gia`);

--
-- Chỉ mục cho bảng `quangcao`
--
ALTER TABLE `quangcao`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_sanpham_loaisanpham` (`id_loai_san_pham`),
  ADD KEY `id_thuong_hieu` (`id_thuong_hieu`);

--
-- Chỉ mục cho bảng `thongtindonhang`
--
ALTER TABLE `thongtindonhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `thuonghieu`
--
ALTER TABLE `thuonghieu`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT cho bảng `danhgia`
--
ALTER TABLE `danhgia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `lienhe`
--
ALTER TABLE `lienhe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT cho bảng `phanhoi`
--
ALTER TABLE `phanhoi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `quangcao`
--
ALTER TABLE `quangcao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT cho bảng `thongtindonhang`
--
ALTER TABLE `thongtindonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT cho bảng `thuonghieu`
--
ALTER TABLE `thuonghieu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD CONSTRAINT `FK_chitietdonhang_donhang` FOREIGN KEY (`id_don_hang`) REFERENCES `donhang` (`id`),
  ADD CONSTRAINT `FK_chitietdonhang_sanpham` FOREIGN KEY (`id_san_pham`) REFERENCES `sanpham` (`id`);

--
-- Các ràng buộc cho bảng `danhgia`
--
ALTER TABLE `danhgia`
  ADD CONSTRAINT `FK_danhgia_nguoidung` FOREIGN KEY (`id_nguoi_dung`) REFERENCES `nguoidung` (`id`),
  ADD CONSTRAINT `FK_danhgia_sanpham` FOREIGN KEY (`id_san_pham`) REFERENCES `sanpham` (`id`);

--
-- Các ràng buộc cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `FK_donhang_nguoidung` FOREIGN KEY (`id_nguoi_dung`) REFERENCES `nguoidung` (`id`);

--
-- Các ràng buộc cho bảng `giohang`
--
ALTER TABLE `giohang`
  ADD CONSTRAINT `FK_giohang_nguoidung` FOREIGN KEY (`id_nguoi_dung`) REFERENCES `nguoidung` (`id`),
  ADD CONSTRAINT `FK_giohang_sanpham` FOREIGN KEY (`id_san_pham`) REFERENCES `sanpham` (`id`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `FK_hoadon_donhang` FOREIGN KEY (`id_don_hang`) REFERENCES `donhang` (`id`);

--
-- Các ràng buộc cho bảng `phanhoi`
--
ALTER TABLE `phanhoi`
  ADD CONSTRAINT `FK_phanhoi_danhgia` FOREIGN KEY (`id_danh_gia`) REFERENCES `danhgia` (`id`),
  ADD CONSTRAINT `FK_phanhoi_nguoidung` FOREIGN KEY (`id_nguoi_dung`) REFERENCES `nguoidung` (`id`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_sanpham_loaisanpham` FOREIGN KEY (`id_loai_san_pham`) REFERENCES `loaisanpham` (`id`),
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`id_thuong_hieu`) REFERENCES `thuonghieu` (`id`);

--
-- Các ràng buộc cho bảng `thongtindonhang`
--
ALTER TABLE `thongtindonhang`
  ADD CONSTRAINT `FK_thongtindonhang_donhang` FOREIGN KEY (`id`) REFERENCES `donhang` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
