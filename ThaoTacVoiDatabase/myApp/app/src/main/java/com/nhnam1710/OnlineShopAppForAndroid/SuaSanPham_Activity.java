package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        Intent intent = getIntent();
        SanPham sp = (SanPham) intent.getSerializableExtra("sanPhamData");

        if (sp != null) {
            editTextTenSanPham.setText(sp.getTenSanPham());
            editTextMoTa.setText(sp.getMoTa());
            editTextGiaTien.setText(String.valueOf(sp.getGiaTien()));
            editTextGiamGia.setText(String.valueOf(sp.getGiamGia()));
            editTextSoLuong.setText(String.valueOf(sp.getSoLuong()));
            editTextHinhAnh.setText(sp.getHinhAnh());
            editTextDonViTinh.setText(sp.getDonViTinh());
        }
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
        doDuLieuVao2Spinner();
    }

    public void doDuLieuVao2Spinner(){
        MyVolleyRequest.layThuongHieuVaLoaiSanPham(SuaSanPham_Activity.this, new MyVolleyRequest.XuLyDuLieuListener() {
            @Override
            public void duLieuNhanDuoc(JSONArray danhSachThuongHieu, JSONArray danhSachLoaiSanPham) {
                DataItem[] luuIDVaTenThuongHieu = new DataItem[danhSachThuongHieu.length()],
                    luuIDVaTenLoaiSanPham = new DataItem[danhSachLoaiSanPham.length()];

                try{
                    for(int i = 0; i < danhSachThuongHieu.length(); i++){
                        JSONObject jsonObject = danhSachThuongHieu.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String tenThuongHieu = jsonObject.getString("TenThuongHieu");
                        luuIDVaTenThuongHieu[i] = new DataItem(id, tenThuongHieu);
                    }

                    for(int i = 0; i < danhSachLoaiSanPham.length(); i++){
                        JSONObject jsonObject = danhSachLoaiSanPham.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String tenLoaiSP = jsonObject.getString("ten_loai_san_pham");
                        luuIDVaTenLoaiSanPham[i] = new DataItem(id, tenLoaiSP);
                    }

                    ArrayAdapter<DataItem> adapterThuongHieu = new ArrayAdapter<>(SuaSanPham_Activity.this, android.R.layout.simple_spinner_item, luuIDVaTenThuongHieu);
                    ArrayAdapter<DataItem> adapterLoaiSanPham = new ArrayAdapter<>(SuaSanPham_Activity.this, android.R.layout.simple_spinner_item, luuIDVaTenLoaiSanPham);

                    spinnerThuongHieu.setAdapter(adapterThuongHieu);
                    spinnerLoaiSanPham.setAdapter(adapterLoaiSanPham);

                    //lưu mảng id và tên vào spinner
                    spinnerThuongHieu.setTag(luuIDVaTenThuongHieu);
                    spinnerLoaiSanPham.setTag(luuIDVaTenThuongHieu);

                }
                catch (JSONException e){
                    Log.e("loi cua toi", "Lỗi khi gọi phương thức duLieuNhanDuoc() của class MyVolleyRequest từ ThemSanPhamActivity: " + e.getMessage());
                    Toast.makeText(SuaSanPham_Activity.this, "Lỗi đổ dữ liẹu vào spinner", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}