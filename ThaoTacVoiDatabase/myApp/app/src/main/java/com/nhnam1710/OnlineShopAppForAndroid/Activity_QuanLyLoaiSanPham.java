package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar; // Import Toolbar

import android.util.Log;
import android.widget.Button; // Import Button
import android.widget.ListView; // Import ListView
import android.widget.TextView; // Import TextView
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_QuanLyLoaiSanPham extends AppCompatActivity {


    // Khai báo các biến
    Toolbar toolbar;
    TextView textViewQuanLyLoaiSanPham;
    ListView listViewLoaiSanPham;
    Button buttonThemLoaiSanPham;

    AdapterLoaiSanPham adapterLoaiSanPham;
    ArrayList<LoaiSanPham> loaiSanPhamArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_loai_san_pham);
        // Gọi phương thức ánh xạ
        anhXa();
        // Thiết lập Toolbar
        setSupportActionBar(toolbar);

        //hiển thị full loại sản phẩm lên listview listViewLoaiSanPham
        loaiSanPhamArrayList = new ArrayList<>();
        adapterLoaiSanPham = new AdapterLoaiSanPham(loaiSanPhamArrayList, R.layout.dong_loai_san_pham, Activity_QuanLyLoaiSanPham.this);
        listViewLoaiSanPham.setAdapter(adapterLoaiSanPham);

        layLoaiSanPhamDocDuocTuSeverHienThiLenListView();

    }

    private void anhXa() {
        toolbar = findViewById(R.id.toolbarQuanLyLoaiSanPham_activity_quan_ly_loai_san_pham);
        textViewQuanLyLoaiSanPham = findViewById(R.id.textViewQuanLyLoaiSanPham_activity_quan_ly_loai_san_pham);
        listViewLoaiSanPham = findViewById(R.id.listViewLoaiSanPham_activity_quan_ly_loai_san_pham);
        buttonThemLoaiSanPham = findViewById(R.id.buttonThemLoaiSanPham_activity_quan_ly_loai_san_pham);
    }
    
    private void layLoaiSanPhamDocDuocTuSeverHienThiLenListView(){
        MyVolleyRequest.layFullLoaiSanPham(Activity_QuanLyLoaiSanPham.this, new MyVolleyRequest.XuLyVolleyListennerVaErroLayFullLoaiSanPham() {
            @Override
            public void danhSachLoaiSanPhamDocDuoc(JSONArray response) {
                //duyệt JsonArray đổ từng jsonobject vào arraylist
                try {
                    for(int i = 0; i < response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        //có phương thức khởi tạo sẵn
                        LoaiSanPham loaiSanPham = new LoaiSanPham(jsonObject);
                        loaiSanPhamArrayList.add(loaiSanPham);
                    }
                    //cập nhật thay đổi để hiển thị lên
                    adapterLoaiSanPham.notifyDataSetChanged();
                    Log.e("hihi", loaiSanPhamArrayList.toString());
//                    Toast.makeText(Activity_QuanLyLoaiSanPham.this, loaiSanPhamArrayList.toString(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void chuoiBaoLoiCuaVolley(String VolleyErrorMessage) {
                //chưa có nhu cầu dùng đến
            }
        });
    }
}