package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityQuanLyDonHang extends AppCompatActivity {

    private ListView listViewDonHang;
    private Button buttonThemDonHang;
    private TextView textViewQuanLyDonHang; // Thêm biến cho TextView tiêu đề
    private Toolbar toolbar; // Thêm biến cho Toolbar



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_don_hang);
        // Thiết lập Toolbar
        setSupportActionBar(toolbar);
        // Gọi phương thức ánh xạ
        anhXa();


        // Lắng nghe sự kiện khi nhấn nút thêm đơn hàng
        buttonThemDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


    }

    private void anhXa() {
        // Ánh xạ các thành phần giao diện
        toolbar = findViewById(R.id.toolbarQuanLyDonHang_activity_quan_ly_don_hang);
        textViewQuanLyDonHang = findViewById(R.id.textViewQuanLyDonHang_activity_quan_ly_don_hang);
        listViewDonHang = findViewById(R.id.listViewDonHang_activity_quan_ly_don_hang);
        buttonThemDonHang = findViewById(R.id.buttonThemDonHang_activity_quan_ly_don_hang);
    }


}
