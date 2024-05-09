package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class SuaSanPham_Activity extends AppCompatActivity {
    private EditText editTextTenSanPham;
    private EditText editTextMoTa;
    private EditText editTextGiaTien;
    private EditText editTextGiamGia;
    private EditText editTextSoLuong;
    private EditText editTextDonViTinh;
    private Spinner spinnerLoaiSanPham;
    private Spinner spinnerThuongHieu;
    private ImageView ivHinhAnhDaChon;
    private Button btnChonHinhAnh;
    private Button buttonSua;
    private Button buttonHuy;

    private boolean isNewImageSelected = false;
    private Uri selectedImageUri;
    int requestCodeChonHinh = 532;
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
            editTextDonViTinh.setText(sp.getDonViTinh());
            String imageName = sp.getHinhAnh();
            String urlImageOnSever = getResources().getString(R.string.url_img_on_sever) + imageName;
            Picasso.get().load(urlImageOnSever).into(ivHinhAnhDaChon);
            ivHinhAnhDaChon.setVisibility(View.VISIBLE);
            //set 2 spinner nằm trong ánh xạ

        }

        //bắt sự kiện click nút chọn hình
        btnChonHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/*");
                startActivityForResult(intent1, requestCodeChonHinh);
            }
        });

        buttonSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanSua(sp);
            }
        });
    }

    private void anhXa() {
        editTextTenSanPham = findViewById(R.id.editTextTenSanPham_activity_sua_san_pham);
        editTextMoTa = findViewById(R.id.editTextMoTa_activity_sua_san_pham);
        editTextGiaTien = findViewById(R.id.editTextGiaTien_activity_sua_san_pham);
        editTextGiamGia = findViewById(R.id.editTextGiamGia_activity_sua_san_pham);
        editTextSoLuong = findViewById(R.id.editTextSoLuong_activity_sua_san_pham);
        editTextDonViTinh = findViewById(R.id.editTextDonViTinh_activity_sua_san_pham);
        spinnerLoaiSanPham = findViewById(R.id.spinnerLoaiSanPham_activity_sua_san_pham);
        spinnerThuongHieu = findViewById(R.id.spinnerThuongHieu_activity_sua_san_pham);
        ivHinhAnhDaChon = findViewById(R.id.ivHinhAnhDaChon_activity_sua_san_pham);
        btnChonHinhAnh = findViewById(R.id.btnChonHinhAnh_activity_sua_san_pham);
        buttonSua = findViewById(R.id.buttonSua_activity_sua_san_pham);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == requestCodeChonHinh && resultCode == RESULT_OK && data!= null){
            selectedImageUri = data.getData();
            ivHinhAnhDaChon.setImageURI(selectedImageUri);
            ivHinhAnhDaChon.setVisibility(View.VISIBLE);
            isNewImageSelected = true;
        } else{
            isNewImageSelected = false;
        }
    }

    public void xacNhanSua(SanPham sp){
        AlertDialog.Builder builder = new AlertDialog.Builder(SuaSanPham_Activity.this);
        builder.setMessage("Bạn có muốn sửa sản phẩm: " + sp.getTenSanPham() + "không? ");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(nhapDuThongTin()){
                    guiDuLieuUpdateSPLenSever(sp);
                }
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void guiDuLieuUpdateSPLenSever(SanPham sp){
        String suaSanPhamSeverUrl = getResources().getString(R.string.url_sua_san_pham);
        MyVolleyStringRequest.GuiStringRequestDenSever(suaSanPhamSeverUrl, SuaSanPham_Activity.this, new MyVolleyStringRequest.thaoTacVoiStringRequestNay() {
            @Override
            public Map<String, String> guiMapLenSever(Map<String, String> param) {
                String tenSanPham = editTextTenSanPham.getText().toString().trim();
                String moTa = editTextMoTa.getText().toString().trim();
                double giaTien = Double.parseDouble(editTextGiaTien.getText().toString().trim());
                double giamGia = Double.parseDouble(editTextGiamGia.getText().toString().trim());
                int soLuong = Integer.parseInt(editTextSoLuong.getText().toString().trim());
                String donViTinh = editTextDonViTinh.getText().toString().trim();
                int idLoaiSanPham = ((DataItem) spinnerLoaiSanPham.getSelectedItem()).getId();
                int idThuongHieu = ((DataItem) spinnerThuongHieu.getSelectedItem()).getId();
                String hinhAnhBase64;
                if(isNewImageSelected)
                    hinhAnhBase64 = getImageStringBase64(selectedImageUri);
                else
                    hinhAnhBase64 = ""; //làm kiểu này cho nữa đọc code dễ hiểu ý nghĩa hơn

                param.put("sanPhamID", String.valueOf(sp.getId()));
                param.put("tenSanPham", tenSanPham);
                param.put("moTa", moTa);
                param.put("giaTien", String.valueOf(giaTien));
                param.put("giamGia", String.valueOf(giamGia));
                param.put("soLuong", String.valueOf(soLuong));
                param.put("donViTinh", donViTinh);
                param.put("idLoaiSanPham", String.valueOf(idLoaiSanPham));
                param.put("idThuongHieu", String.valueOf(idThuongHieu));
                param.put("hinhAnh", hinhAnhBase64);
                param.put("luotXem", String.valueOf(sp.getLuotXem()));
                param.put("luotMua", String.valueOf(sp.getLuotMua()));

                return param;
            }

            @Override
            public void xuLyChuoiDocDuocTuSever(String response) {
                // Toast các trường hợp nhận được từ server
                if (response.equals("success")) {
                    Intent intent = new Intent(SuaSanPham_Activity.this, QuanLySanPham_Activity.class);
                    startActivity(intent);
                    Toast.makeText(SuaSanPham_Activity.this, "Sửa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                } else if (response.equals("erro")) {
                    Log.e("loi cua toi", "Lỗi ở file php suaSanPham.php chạy phuong thức suaSanPham từ model SanPham thất bại,...");
                    Toast.makeText(SuaSanPham_Activity.this, "Lỗi khi sửa sản phẩm", Toast.LENGTH_SHORT).show();
                } else if (response.equals("erro them hinh")) {
                    Log.e("loi cua toi", "Lỗi ở file php suaSanPham.php thêm hình ảnh được gửi lên dưới dạng base64 thất bại, kiểm tra thêm ở java SuaSanPham_Activity chỗ phương thức getImageStringBase64(),...");
                    Toast.makeText(SuaSanPham_Activity.this, "Lỗi khi thêm hình ảnh", Toast.LENGTH_SHORT).show();
                } else if (response.contains("gui thieu thong tin")) {
                    Log.e("loi cua toi", "Lỗi ở app gửi thiếu 1 hoặc tất cả thông tin, nằm trong phương thức của interface truyền vào phương thức guiDuLieuUpdateSPLenSever cụ thể là: guiMapLenSever() trong class SuaSanPham_Activity");
                    Toast.makeText(SuaSanPham_Activity.this, "Thiếu thông tin cần thiết khi gửi đến server", Toast.LENGTH_SHORT).show();
                } else if (response.equals("khong phai post")) {
                    Log.e("loi cua toi", "Kiểu gửi lên sever không phải là Post, lỗi này xảy ra ở SuaSanPham_Activity nhưng phải kiểm tra class MyVolleyStringRequest cụ thể là phương thức GuiStringRequestDenSever để kiểm tra");
                    Toast.makeText(SuaSanPham_Activity.this, "Yêu cầu không phải là POST", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SuaSanPham_Activity.this, "Phản hồi không xác định từ server: " + response, Toast.LENGTH_SHORT).show();
                }
                Log.d("xem du lieu", "Dữ liệu sever in ra là: " + response);
            }

            @Override
            public void baoLoiCuaOnErrorResponse(VolleyError error) {
                Log.e("loi cua toi", "Báo lỗi của volley, lỗi này xảy ra ở phương thức guiDuLieuUpdateSPLenSever trong class SuaSanPham_Activity: " + error.getMessage());
                Toast.makeText(SuaSanPham_Activity.this, "Lỗi thêm hình, lỗi ở sever", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getImageStringBase64(Uri uri) {
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

    public boolean nhapDuThongTin() {
        boolean isValid = true;

        // Kiểm tra và hiển thị lỗi cho EditText
        if (editTextTenSanPham.getText().toString().trim().isEmpty()) {
            editTextTenSanPham.setError("Vui lòng nhập tên sản phẩm");
            isValid = false;
        }

        if (editTextMoTa.getText().toString().trim().isEmpty()) {
            editTextMoTa.setError("Vui lòng nhập mô tả");
            isValid = false;
        }

        if (editTextGiaTien.getText().toString().trim().isEmpty()) {
            editTextGiaTien.setError("Vui lòng nhập giá tiền");
            isValid = false;
        }

        if (editTextGiamGia.getText().toString().trim().isEmpty()) {
            editTextGiamGia.setError("Vui lòng nhập giảm giá");
            isValid = false;
        }

        if (editTextSoLuong.getText().toString().trim().isEmpty()) {
            editTextSoLuong.setError("Vui lòng nhập số lượng");
            isValid = false;
        }

        if (editTextDonViTinh.getText().toString().trim().isEmpty()) {
            editTextDonViTinh.setError("Vui lòng nhập đơn vị tính");
            isValid = false;
        }

        // Kiểm tra và hiển thị thông báo lỗi cho Spinner
        if (spinnerLoaiSanPham.getSelectedItem() == null) {
            Toast.makeText(this, "Vui lòng chọn loại sản phẩm", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (spinnerThuongHieu.getSelectedItem() == null) {
            Toast.makeText(this, "Vui lòng chọn thương hiệu", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        // Kiểm tra xem hình ảnh đã được chọn hay chưa
        if (ivHinhAnhDaChon.getDrawable() == null) {
            Toast.makeText(this, "Vui lòng chọn hình ảnh", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

}