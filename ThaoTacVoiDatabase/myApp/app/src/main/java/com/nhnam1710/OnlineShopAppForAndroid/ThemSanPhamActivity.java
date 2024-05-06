package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ThemSanPhamActivity extends AppCompatActivity {

    private EditText editTextTenSanPham, editTextMoTa, editTextGiaTien, editTextGiamGia, editTextSoLuong, editTextHinhAnh, editTextDonViTinh;
    private Spinner spinnerLoaiSanPham, spinnerThuongHieu;
    private ImageView imageViewHinhAnhDaChon;
    private Button buttonChonHinhAnh, buttonThem, buttonHuy;

    private  int REQUEST_CODE_CHON_HINH  = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        anhXa();


        MyVolleyRequest.layThuongHieuVaLoaiSanPham(ThemSanPhamActivity.this, new MyVolleyRequest.XuLyDuLieuListener() {
            @Override
            public void duLieuNhanDuoc(JSONArray danhSachThuongHieu, JSONArray danhSachLoaiSanPham) {
                try{
                    // Chuẩn bị Map để lưu id và tên thương hiệu và loại sản phẩm
                    Map<Integer, String> mapThuongHieu = new HashMap<>();
                    Map<Integer, String> mapLoaiSanPham = new HashMap<>();

                    // Lặp qua danh sách thương hiệu và loại sản phẩm để lấy id và tên
                    for (int i = 0; i < danhSachThuongHieu.length(); i++) {
                        JSONObject jsonObject = danhSachThuongHieu.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String ten = jsonObject.getString("TenThuongHieu");
                        mapThuongHieu.put(id, ten);
                    }
                    for (int i = 0; i < danhSachLoaiSanPham.length(); i++) {
                        JSONObject jsonObject = danhSachLoaiSanPham.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String ten = jsonObject.getString("ten_loai_san_pham");
                        mapLoaiSanPham.put(id, ten);
                    }

                    // Cập nhật Spinner sử dụng Map
                    ArrayAdapter<String> adapterThuongHieu = new ArrayAdapter<>(ThemSanPhamActivity.this, android.R.layout.simple_spinner_item, new ArrayList<>(mapThuongHieu.values()));
                    ArrayAdapter<String> adapterLoaiSanPham = new ArrayAdapter<>(ThemSanPhamActivity.this, android.R.layout.simple_spinner_item, new ArrayList<>(mapLoaiSanPham.values()));

                    spinnerThuongHieu.setAdapter(adapterThuongHieu);
                    spinnerLoaiSanPham.setAdapter(adapterLoaiSanPham);

                    // Lưu Map vào tag của Spinner
                    spinnerThuongHieu.setTag(mapThuongHieu);
                    spinnerLoaiSanPham.setTag(mapLoaiSanPham);
                }
                catch (JSONException e){
                    Log.e("loi cua toi", "Lỗi khi gọi phương thức duLieuNhanDuoc() của class MyVolleyRequest từ ThemSanPhamActivity: " + e.getMessage());
                }

            }

            @Override
            public void onError(String errorMessage) {
                Log.e("loi cua toi", "Lỗi đọc json ở class ThemSanPhamActivity cụ thể là trong phương thức onError: " + errorMessage);
            }
        });

        // Xử lý sự kiện khi nhấn nút chọn hình ảnh
        buttonChonHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Chọn hình ảnh"), REQUEST_CODE_CHON_HINH);
            }
        });

        // Xử lý sự kiện khi nhấn nút thêm
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Viết mã xử lý khi người dùng nhấn vào nút thêm
            }
        });

        // Xử lý sự kiện khi nhấn nút hủy
        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHON_HINH && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            imageViewHinhAnhDaChon.setImageURI(imageUri);
            imageViewHinhAnhDaChon.setVisibility(View.VISIBLE);
        }
    }

    private void anhXa() {
        // Ánh xạ EditText
        editTextTenSanPham = findViewById(R.id.editTextTenSanPham_activity_them_san_pham);
        editTextMoTa = findViewById(R.id.editTextMoTa_activity_them_san_pham);
        editTextGiaTien = findViewById(R.id.editTextGiaTien_activity_them_san_pham);
        editTextGiamGia = findViewById(R.id.editTextGiamGia_activity_them_san_pham);
        editTextSoLuong = findViewById(R.id.editTextSoLuong_activity_them_san_pham);
        editTextHinhAnh = findViewById(R.id.editTextHinhAnh_activity_them_san_pham);
        editTextDonViTinh = findViewById(R.id.editTextDonViTinh_activity_them_san_pham);

        // Ánh xạ Spinner
        spinnerLoaiSanPham = findViewById(R.id.spinnerLoaiSanPham_activity_them_san_pham);
        spinnerThuongHieu = findViewById(R.id.spinnerThuongHieu_activity_them_san_pham);

        // Ánh xạ ImageView
        imageViewHinhAnhDaChon = findViewById(R.id.ivHinhAnhDaChon_activity_them_san_pham);

        // Ánh xạ Button
        buttonChonHinhAnh = findViewById(R.id.btnChonHinhAnh_activity_them_san_pham);
        buttonThem = findViewById(R.id.buttonThem_activity_them_san_pham);
        buttonHuy = findViewById(R.id.buttonHuy_activity_them_san_pham);
    }
}