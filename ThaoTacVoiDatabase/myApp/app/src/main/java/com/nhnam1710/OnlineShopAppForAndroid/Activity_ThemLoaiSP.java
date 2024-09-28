package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Activity_ThemLoaiSP extends AppCompatActivity {

    // Khai báo các biến ánh xạ
    private EditText editTextTenLoaiSanPham, editTextMoTa;
    private Button buttonChonHinhAnh, buttonThemLoaiSP;
    private ImageView imageViewHinhAnh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai_sp);

        // Ánh xạ các thành phần trong layout
        anhXa();
    }

    // Phương thức để ánh xạ các thành phần
    private void anhXa() {
        editTextTenLoaiSanPham = findViewById(R.id.editTextTenLoaiSanPham_activity_themloaisp);
        editTextMoTa = findViewById(R.id.editTextMoTa_activity_themloaisp);
        buttonChonHinhAnh = findViewById(R.id.buttonChonHinhAnh_activity_themloaisp);
        buttonThemLoaiSP = findViewById(R.id.buttonThemLoaiSP_activity_themloaisp);
        imageViewHinhAnh = findViewById(R.id.imageViewHinhAnh_activity_themloaisp);
    }
}
