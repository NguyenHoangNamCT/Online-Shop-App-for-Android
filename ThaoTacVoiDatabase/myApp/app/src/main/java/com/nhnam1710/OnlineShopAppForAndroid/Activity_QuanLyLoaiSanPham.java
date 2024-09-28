package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar; // Import Toolbar
import android.widget.Button; // Import Button
import android.widget.ListView; // Import ListView
import android.widget.TextView; // Import TextView

public class Activity_QuanLyLoaiSanPham extends AppCompatActivity {


    // Khai báo các biến
    Toolbar toolbar;
    TextView textViewQuanLyLoaiSanPham;
    ListView listViewLoaiSanPham;
    Button buttonThemLoaiSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_loai_san_pham);
        // Gọi phương thức ánh xạ
        anhXa();

        // Thiết lập Toolbar
        setSupportActionBar(toolbar);
    }

    private void anhXa() {
        toolbar = findViewById(R.id.toolbarQuanLyLoaiSanPham_activity_quan_ly_loai_san_pham);
        textViewQuanLyLoaiSanPham = findViewById(R.id.textViewQuanLyLoaiSanPham_activity_quan_ly_loai_san_pham);
        listViewLoaiSanPham = findViewById(R.id.listViewLoaiSanPham_activity_quan_ly_loai_san_pham);
        buttonThemLoaiSanPham = findViewById(R.id.buttonThemLoaiSanPham_activity_quan_ly_loai_san_pham);
    }
}