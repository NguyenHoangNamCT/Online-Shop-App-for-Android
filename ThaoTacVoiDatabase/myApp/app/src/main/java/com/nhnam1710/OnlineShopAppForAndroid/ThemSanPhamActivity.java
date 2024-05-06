package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
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
                // Viết mã xử lý khi người dùng nhấn vào nút hủy
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