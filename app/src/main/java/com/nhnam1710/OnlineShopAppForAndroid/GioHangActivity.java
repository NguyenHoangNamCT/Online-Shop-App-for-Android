package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class GioHangActivity extends AppCompatActivity {

    private ListView listViewGioHang;
    private Button btnThanhToan;
    private TextView tvGioHangRong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
    }

    public void anhXa(){
        listViewGioHang = findViewById(R.id.listViewGioHang_activity_gio_hang);
        btnThanhToan = findViewById(R.id.btnThanhToan_activity_gio_hang);
        tvGioHangRong = findViewById(R.id.tvGioHangRong_activity_gio_hang);
    }
}