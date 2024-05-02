<?php require('model/sanpham.php'); ?>
<!-- file này show tất cả hình ảnh của database lên nhưng giờ nó không cần dùng nữa -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hiển thị Hình ảnh</title>
</head>
<body>
    <div style="max-width: 800px; margin: 50px auto; text-align: center;">
        <h1>Hiển thị Hình ảnh</h1>
        <?php
            $sp = new SanPham();
            $mangHinhSP = $sp->layTatCaHinhAnhSanPham();
            $i = 0;
            foreach($mangHinhSP as $spItem){
                $i++;
                echo $i;
        ?> 
                <img src="images\<?php echo $spItem['hinh_anh']; ?>" alt="Hình ảnh" style="max-width: 100%; height: auto;">
        <?php
            }
        ?>
</div>
</body>
</html>
