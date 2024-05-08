package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Base64;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ThemSanPhamActivity extends AppCompatActivity {

    private EditText editTextTenSanPham, editTextMoTa, editTextGiaTien, editTextGiamGia, editTextSoLuong, editTextDonViTinh;
    private Spinner spinnerLoaiSanPham, spinnerThuongHieu;
    private ImageView imageViewHinhAnhDaChon;
    private Button buttonChonHinhAnh, buttonThem, buttonHuy;
    private Uri selectedImageUri;

    private  int REQUEST_CODE_CHON_HINH  = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        anhXa();


        //đổ 2 bảng thương hiệu và loại sản phẩm vào 2 spinner tương ứng
        MyVolleyRequest.layThuongHieuVaLoaiSanPham(ThemSanPhamActivity.this, new MyVolleyRequest.XuLyDuLieuListener() {
            @Override
            public void duLieuNhanDuoc(JSONArray danhSachThuongHieu, JSONArray danhSachLoaiSanPham) {
                try{
                    // Chuẩn bị mảng để lưu dữ liệu thương hiệu và loại sản phẩm
                    DataItem luuIDDanhSachThuongHieu[] = new DataItem[danhSachThuongHieu.length()];
                    DataItem luuIDDanhSachLoaiSanPham[] = new DataItem[danhSachLoaiSanPham.length()];

                    // Lặp qua danh sách thương hiệu và loại sản phẩm để lấy id và tên
                    for (int i = 0; i < danhSachThuongHieu.length(); i++) {
                        JSONObject jsonObject = danhSachThuongHieu.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String ten = jsonObject.getString("TenThuongHieu");
                        luuIDDanhSachThuongHieu[i] = new DataItem(id, ten);
                    }
                    for (int i = 0; i < danhSachLoaiSanPham.length(); i++) {
                        JSONObject jsonObject = danhSachLoaiSanPham.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String ten = jsonObject.getString("ten_loai_san_pham");
                        luuIDDanhSachLoaiSanPham[i] = new DataItem(id, ten);
                    }


                    ArrayAdapter<DataItem> adapterThuongHieu = new ArrayAdapter<>(ThemSanPhamActivity.this, android.R.layout.simple_spinner_item, luuIDDanhSachThuongHieu);
                    ArrayAdapter<DataItem> adapterLoaiSanPham = new ArrayAdapter<>(ThemSanPhamActivity.this, android.R.layout.simple_spinner_item, luuIDDanhSachLoaiSanPham);

                    spinnerThuongHieu.setAdapter(adapterThuongHieu);
                    spinnerLoaiSanPham.setAdapter(adapterLoaiSanPham);

                    // Lưu Map vào tag của Spinner
                    spinnerThuongHieu.setTag(luuIDDanhSachThuongHieu);
                    spinnerLoaiSanPham.setTag(luuIDDanhSachLoaiSanPham);
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
                if(kiemTraDuLieuNhap()){
                    sendProductDataToServer();
                    Toast.makeText(ThemSanPhamActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ThemSanPhamActivity.this, "Thêm sản phẩm thất bai, vui lòng điền đủ thông tin", Toast.LENGTH_LONG).show();
                }
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

    private void sendProductDataToServer() {
        String url = getResources().getString(R.string.url_them_san_pham); // Đường dẫn đến API trên máy chủ của bạn

        // Khởi tạo RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Tạo một StringRequest để thực hiện yêu cầu POST
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Xử lý phản hồi từ máy chủ nếu cần
                        if (response.equals("success")) {
                            // Phản hồi thành công
                            Intent intent = new Intent(ThemSanPhamActivity.this, QuanLySanPham_Activity.class);
                            startActivity(intent);
                            Toast.makeText(ThemSanPhamActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        } else if (response.equals("erro")) {
                            Log.e("loi cua toi", "Lỗi chỗ thêm sản phẩm trong file themsanpham.php check các tham số truyền vào + cái hàm thêm,...");
                            Toast.makeText(ThemSanPhamActivity.this, "Có lỗi xảy ra khi thêm sản phẩm phía sever", Toast.LENGTH_SHORT).show();
                        } else if (response.equals("erro them hinh")) {
                            // Phản hồi lỗi khi thêm hình ảnh
                            Log.e("loi cua toi", "Lỗi khi thêm hình ảnh đã gửi vào sever, nguyên nhân có thể do dạng base64 gửi lên có vấn đề,... hoặc khác");
                            Toast.makeText(ThemSanPhamActivity.this, "Có lỗi xảy ra khi thêm hình ảnh", Toast.LENGTH_SHORT).show();
                        } else if (response.equals("erro from app")) {
                            // Phản hồi lỗi khi yêu cầu không phải là POST
                            Log.e("loi cua toi", "Lỗi này có thể do hàm sendProductDataToServer (trong ThemSanPhamActivity) tham số khởi tạo của thằng stringRequest không phải là post,...");
                            Toast.makeText(ThemSanPhamActivity.this, "Lỗi yêu cầu từ ứng dụng", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Xử lý lỗi nếu có
                Toast.makeText(ThemSanPhamActivity.this, "Lỗi: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Thêm các tham số (key-value) cần gửi đến máy chủ
                Map<String, String> params = new HashMap<>();
                params.put("ten_san_pham", editTextTenSanPham.getText().toString());
                params.put("mo_ta", editTextMoTa.getText().toString());
                params.put("gia_tien", editTextGiaTien.getText().toString());
                params.put("giam_gia", editTextGiamGia.getText().toString());
                params.put("so_luong", editTextSoLuong.getText().toString());
                params.put("hinh_anh", getImageString(selectedImageUri)); // Chuyển đổi hình ảnh sang dạng Base64 và gửi
                params.put("don_vi_tinh", editTextDonViTinh.getText().toString());
                params.put("loai_san_pham", spinnerLoaiSanPham.getSelectedItem().toString());
                int idLoaiSP = ((DataItem[]) spinnerLoaiSanPham.getTag())[(int) spinnerLoaiSanPham.getSelectedItemPosition()].getId();
                params.put("id_loai_san_pham", ""+idLoaiSP);
                params.put("thuong_hieu", spinnerThuongHieu.getSelectedItem().toString());
                params.put("id_thuong_hieu", ((DataItem[])(spinnerThuongHieu.getTag()))[spinnerThuongHieu.getSelectedItemPosition()].getId() + "");
                return params;
            }
        };

        // Thêm yêu cầu vào hàng đợi
        queue.add(stringRequest);
    }

    // Hàm để chuyển đổi hình ảnh thành dạng Base64
    private String getImageString(Uri uri) {
        Bitmap bitmap;
        try {
            // Tạo một đối tượng Bitmap từ URI
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            // Chuẩn bị để nén hình ảnh thành một mảng byte bằng cách sử dụng ByteArrayOutputStream.
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // Nén hình ảnh thành một mảng byte sử dụng phương thức compress của lớp Bitmap. với định dạng đầu ra là jpg, chất lượng hình ảnh là 100% không bị giảm, tất cả được lưu vào byteArrayOutputStream
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            //chuyển byteArrayOutputStream thành 1 mảng byte
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            // Chuyển đổi mảng byte thành một chuỗi dạng Base64 sử dụng phương thức encodeToString của lớp Base64.
            return Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private boolean kiemTraDuLieuNhap() {
        boolean isValid = true;

        // Kiểm tra và hiển thị thông báo cho từng trường dữ liệu
        if (editTextTenSanPham.getText().toString().isEmpty()) {
            editTextTenSanPham.setError("Vui lòng nhập tên sản phẩm");
            isValid = false;
        }

        if (editTextMoTa.getText().toString().isEmpty()) {
            editTextMoTa.setError("Vui lòng nhập mô tả");
            isValid = false;
        }

        if (editTextGiaTien.getText().toString().isEmpty()) {
            editTextGiaTien.setError("Vui lòng nhập giá tiền");
            isValid = false;
        }

        if (editTextGiamGia.getText().toString().isEmpty()) {
            editTextGiamGia.setError("Vui lòng nhập giảm giá");
            isValid = false;
        }

        if (editTextSoLuong.getText().toString().isEmpty()) {
            editTextSoLuong.setError("Vui lòng nhập số lượng");
            isValid = false;
        }


        if (editTextDonViTinh.getText().toString().isEmpty()) {
            editTextDonViTinh.setError("Vui lòng nhập đơn vị tính");
            isValid = false;
        }

        if (spinnerLoaiSanPham.getSelectedItem() == null) {
            Toast.makeText(this, "Vui lòng chọn loại sản phẩm", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (spinnerThuongHieu.getSelectedItem() == null) {
            Toast.makeText(this, "Vui lòng chọn thương hiệu", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (imageViewHinhAnhDaChon.getDrawable() == null) {
            Toast.makeText(this, "Vui lòng chọn hình ảnh", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid; // Trả về true nếu tất cả các trường đều được nhập hoặc chọn
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHON_HINH && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            imageViewHinhAnhDaChon.setImageURI(selectedImageUri);
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