package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class ActivityQuanLyThuongHieu extends AppCompatActivity {

    // Khai báo các biến để ánh xạ
    private Toolbar toolbarQuanLyThuongHieu;
    private TextView textViewQuanLyThuongHieu;
    private ListView listViewThuongHieu;
    private Button buttonThemThuongHieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_thuong_hieu);
        anhXa();

        buttonThemThuongHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityQuanLyThuongHieu.this, ActivityThemThuongHieu.class);
                startActivity(intent);
            }
        });
    }


    private void anhXa() {
        // Ánh xạ các thành phần giao diện với các view trong layout
        toolbarQuanLyThuongHieu = findViewById(R.id.toolbarQuanLyThuongHieu_activity_quan_ly_thuong_hieu);
        textViewQuanLyThuongHieu = findViewById(R.id.textViewQuanLyThuongHieu_activity_quan_ly_thuong_hieu);
        listViewThuongHieu = findViewById(R.id.listViewThuongHieu_activity_quan_ly_thuong_hieu);
        buttonThemThuongHieu = findViewById(R.id.buttonThemThuongHieu_activity_quan_ly_thuong_hieu);
    }
}
