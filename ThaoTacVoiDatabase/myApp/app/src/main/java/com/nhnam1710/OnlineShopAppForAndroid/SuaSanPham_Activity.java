package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class SuaSanPham_Activity extends AppCompatActivity {
    private EditText editTextTenSanPham;
    private EditText editTextMoTa;
    private EditText editTextGiaTien;
    private EditText editTextGiamGia;
    private EditText editTextSoLuong;
    private EditText editTextHinhAnh;
    private EditText editTextDonViTinh;
    private Spinner spinnerLoaiSanPham;
    private Spinner spinnerThuongHieu;
    private ImageView ivHinhAnhDaChon;
    private Button btnChonHinhAnh;
    private Button buttonThem;
    private Button buttonHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_san_pham);
        anhXa();
    }

    private void anhXa() {
        editTextTenSanPham = findViewById(R.id.editTextTenSanPham_activity_sua_san_pham);
        editTextMoTa = findViewById(R.id.editTextMoTa_activity_sua_san_pham);
        editTextGiaTien = findViewById(R.id.editTextGiaTien_activity_sua_san_pham);
        editTextGiamGia = findViewById(R.id.editTextGiamGia_activity_sua_san_pham);
        editTextSoLuong = findViewById(R.id.editTextSoLuong_activity_sua_san_pham);
        editTextHinhAnh = findViewById(R.id.editTextHinhAnh_activity_sua_san_pham);
        editTextDonViTinh = findViewById(R.id.editTextDonViTinh_activity_sua_san_pham);
        spinnerLoaiSanPham = findViewById(R.id.spinnerLoaiSanPham_activity_sua_san_pham);
        spinnerThuongHieu = findViewById(R.id.spinnerThuongHieu_activity_sua_san_pham);
        ivHinhAnhDaChon = findViewById(R.id.ivHinhAnhDaChon_activity_sua_san_pham);
        btnChonHinhAnh = findViewById(R.id.btnChonHinhAnh_activity_sua_san_pham);
        buttonThem = findViewById(R.id.buttonSua_activity_sua_san_pham);
        buttonHuy = findViewById(R.id.buttonHuy_activity_sua_san_pham);
    }
}