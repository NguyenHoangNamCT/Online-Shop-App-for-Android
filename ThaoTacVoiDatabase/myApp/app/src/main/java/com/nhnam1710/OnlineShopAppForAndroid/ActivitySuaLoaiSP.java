package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.activity.result.ActivityResult;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class ActivitySuaLoaiSP extends AppCompatActivity {

    // Khai báo các thành phần giao diện
    private EditText editTextTenLoaiSanPham;
    private EditText editTextMoTa;
    private Button buttonChonHinhAnh;
    private ImageView imageViewHinhAnh;
    private Button buttonSuaLoaiSP;
    private int REQUEST_CODE_CHON_HINH = 123;
    Uri uriSelectedImage;

    int idLoaiSPCanSua;
    LoaiSanPham loaiSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiy_sua_loai_sp);
        anhXa();

        fillGiaTriHienTai();

        //bước 1 cho người dùng chọn ảnh
        buttonChonHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent.ACTION_GET_CONTENT hành động cho người dùng chọn 1 tệp từ thiết bị
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //chỉ định loại tệp người dùng chọn là một hình ảnh
                intent.setType("image/*");
                //Khi bạn gọi Intent.createChooser(intent, "Chọn hình ảnh"), phương thức này sẽ tạo ra một hộp thoại mà trong đó người dùng có thể chọn ứng dụng mà họ muốn sử dụng để chọn hình ảnh từ thiết bị.
                // Hộp thoại này giúp cải thiện trải nghiệm người dùng bằng cách cho phép họ chọn từ nhiều ứng dụng thay vì chỉ một ứng dụng cụ thể.
                startActivityForResult(Intent.createChooser(intent, "Chọn hình ảnh"), REQUEST_CODE_CHON_HINH);
                //startActivityForResult sẽ nhận kết quả từ hành động chọn ảnh ở phương thức onActivityResult
            }
        });

        buttonSuaLoaiSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                idLoaiSPCanSua = intent.getIntExtra("idLoaiSP", -1);
                guiThongTinCanSuaLenSever();
            }
        });
    }

    private void anhXa() {
        // Ánh xạ các thành phần với các view trong layout
        editTextTenLoaiSanPham = findViewById(R.id.editTextTenLoaiSanPham_activity_sua_loai_sp);
        editTextMoTa = findViewById(R.id.editTextMoTa_activity_sua_loai_sp);
        buttonChonHinhAnh = findViewById(R.id.buttonChonHinhAnh_activity_sua_loai_sp);
        imageViewHinhAnh = findViewById(R.id.imageViewHinhAnh_activity_sua_loai_sp);
        buttonSuaLoaiSP = findViewById(R.id.buttonThemLoaiSP_activity_sua_loai_sp);

    }

    private void fillGiaTriHienTai(){
        Intent intent = getIntent();
        loaiSanPham = (LoaiSanPham) intent.getSerializableExtra("loaiSP");
        editTextTenLoaiSanPham.setText(loaiSanPham.getTenLoaiSanPham());
        editTextMoTa.setText(loaiSanPham.getMoTa());
        String urlHinhAnh = ActivitySuaLoaiSP.this.getString(R.string.url_img_on_sever) + loaiSanPham.getHinhAnh();
        Picasso.get().load(urlHinhAnh).into(imageViewHinhAnh);
        imageViewHinhAnh.setVisibility(View.VISIBLE);
    }

    //bước 2 lưu uri của hình ảnh và hiển thị hình ảnh được chọn lên imageview
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //        requestCode == REQUEST_CODE_CHON_HINH:
        //
        //Kiểm tra xem mã yêu cầu (request code) có phải là REQUEST_CODE_CHON_HINH hay không.
        // Mã này được xác định ở phần trước trong mã của bạn và được sử dụng để phân biệt các yêu cầu khác nhau khi có nhiều hành động có thể được thực hiện trong ứng dụng.
        //Điều này giúp đảm bảo rằng phản hồi mà bạn nhận được là từ hành động chọn hình ảnh cụ thể mà bạn đã thực hiện.

        //        resultCode == RESULT_OK:
        //
        //Kiểm tra xem mã kết quả (result code) có phải là RESULT_OK hay không. Mã này cho biết rằng hành động đã hoàn tất thành công.
        //Nếu mã kết quả không phải là RESULT_OK, điều đó có nghĩa là hành động chọn hình ảnh đã bị hủy hoặc không thành công, và bạn không nên tiếp tục xử lý.

        //        data != null:
        //
        //Kiểm tra xem đối tượng data (chứa thông tin trả về từ hành động chọn hình ảnh) có khác null hay không. Nếu data là null, điều đó có nghĩa là không có thông tin nào được trả về từ hành động.
        //Điều này giúp đảm bảo rằng bạn có dữ liệu hợp lệ để làm việc.

        //        data.getData() != null:
        //
        //Kiểm tra xem URI (Uniform Resource Identifier) của hình ảnh được chọn có khác null hay không. data.getData() sẽ trả về URI của hình ảnh mà người dùng đã chọn.
        //Nếu URI là null, điều đó có nghĩa là không có hình ảnh nào được chọn, và bạn không nên tiếp tục xử lý.
        if (requestCode == REQUEST_CODE_CHON_HINH && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //lưu uri từ ảnh chọn được
            uriSelectedImage = data.getData();
            //hiển thị hình ảnh chọn được
            imageViewHinhAnh.setImageURI(uriSelectedImage);
            //tắt ẩn imageView ban đầu
            imageViewHinhAnh.setVisibility(View.VISIBLE);
        }
    }

    //bước 3 chuyển hình ảnh thành dạng chuổi base64 khi có được uri của hình ảnh
    private String chuyenAnhDuocChonThanhChuoiBase64(Uri uri) {
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


    //bước 4 gửi tất cả dữ liệu người dùng đã nhập lên sever
    public void guiThongTinCanSuaLenSever(){
        String url = getString(R.string.url_sua_loai_sp);
        MyVolleyStringRequest.GuiStringRequestDenSever(url, ActivitySuaLoaiSP.this, new MyVolleyStringRequest.thaoTacVoiStringRequestNay() {
            @Override
            public Map<String, String> guiMapLenSever(Map<String, String> param) {
                param.put("idLoaiSP", idLoaiSPCanSua + "");
                param.put("tenLoaiSP", editTextTenLoaiSanPham.getText().toString());
                param.put("moTa", editTextMoTa.getText().toString());
                String chuoiAnhDangBase64 = "";
                if(uriSelectedImage != null)
                    chuoiAnhDangBase64 = chuyenAnhDuocChonThanhChuoiBase64(uriSelectedImage);
                param.put("hinhAnh", chuoiAnhDangBase64);
                return param;
            }

            @Override
            public void xuLyChuoiDocDuocTuSever(String response) {
                if(response.equals("success")){
                    Toast.makeText(ActivitySuaLoaiSP.this ,"Sửa thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActivitySuaLoaiSP.this, Activity_QuanLyLoaiSanPham.class);
                    startActivity(intent);

                }
                else
                    Toast.makeText(ActivitySuaLoaiSP.this ,"Sửa thất bại, lỗi code sever", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void baoLoiCuaOnErrorResponse(VolleyError error) {

            }
        });
    }
}