package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class ActivitySuaThuongHieu extends AppCompatActivity {

    // Khai báo các biến để ánh xạ
    private EditText editTextTenThuongHieu, editTextMoTaThuongHieu, editTextTrangWebThuongHieu;
    private Button buttonChonLogo, buttonSuaThuongHieu;
    private ImageView imageViewLogo;
    private int REQUEST_CODE_CHON_HINH = 123;

    private Uri uriSelectedImage;
    private ThuongHieu thuongHieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thuong_hieu);

        // Gọi phương thức ánh xạ
        anhXa();

        fillData();


        buttonChonLogo.setOnClickListener(new View.OnClickListener() {
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

        buttonSuaThuongHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraThongTin()) {
                    xacNhanSuaThuongHieu();
                }
            }
        });
    }

    public void fillData() {
        // Nhận đối tượng ThuongHieu được truyền qua Intent
        thuongHieu = (ThuongHieu) getIntent().getSerializableExtra("thuongHieu");

        // Gán trực tiếp các giá trị từ đối tượng ThuongHieu vào các trường giao diện
        editTextTenThuongHieu.setText(thuongHieu.getTenThuongHieu());
        editTextMoTaThuongHieu.setText(thuongHieu.getMoTa());
        editTextTrangWebThuongHieu.setText(thuongHieu.getTrangWeb());
        // Lấy URL của logo từ máy chủ
        String fullImageUrl = getString(R.string.url_img_on_sever) + thuongHieu.getLogo();
        // Sử dụng Picasso để tải và hiển thị hình ảnh vào ImageView
        Picasso.get().load(fullImageUrl).into(imageViewLogo);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CHON_HINH && resultCode == RESULT_OK && data != null && data.getData() != null){
            uriSelectedImage = data.getData();
            imageViewLogo.setImageURI(uriSelectedImage);
            imageViewLogo.setVisibility(View.VISIBLE);
        }
    }

    //bước 3: viết hàm chuyển đổi hình ảnh sang dạng chuổi base64 đầu vào là uri của hình ảnh đã chọn được
    // Hàm để chuyển đổi hình ảnh thành dạng Base64
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

    //bước 4 gửi dữ liệu cần sửa lên sever
    public void suaThuongHieu(){
        String url = getString(R.string.url_sua_thuong_hieu);
        MyVolleyStringRequest.GuiStringRequestDenSever(url, ActivitySuaThuongHieu.this, new MyVolleyStringRequest.thaoTacVoiStringRequestNay() {
            @Override
            public Map<String, String> guiMapLenSever(Map<String, String> param) {
                // Lấy dữ liệu từ các EditText mà người dùng đã nhập
                String tenThuongHieu = editTextTenThuongHieu.getText().toString().trim();
                String moTaThuongHieu = editTextMoTaThuongHieu.getText().toString().trim();
                String trangWebThuongHieu = editTextTrangWebThuongHieu.getText().toString().trim();

                String hinhAnhBase64 = "";
                // Kiểm tra nếu người dùng đã chọn hình ảnh
                if (uriSelectedImage != null)
                    hinhAnhBase64 = chuyenAnhDuocChonThanhChuoiBase64(uriSelectedImage);

                // Đưa các giá trị vào param để gửi lên server
                param.put("id", thuongHieu.getId() + "");
                param.put("ten_thuong_hieu", tenThuongHieu);
                param.put("mo_ta", moTaThuongHieu);
                param.put("trang_web", trangWebThuongHieu);
                param.put("logo", hinhAnhBase64);
                return param;
            }

            @Override
            public void xuLyChuoiDocDuocTuSever(String response) {
                // Kiểm tra xem response có phải là null hoặc rỗng không
                if (response == null || response.isEmpty()) {
                    Toast.makeText(ActivitySuaThuongHieu.this, "Không nhận được phản hồi từ server.", Toast.LENGTH_SHORT).show();
                    Log.e("loi_cua_toi", "Response từ server là null hoặc rỗng.");
                    return;
                }

                // Xử lý các phản hồi khác nhau từ server
                switch (response) {
                    case "success":
                        // Thao tác khi cập nhật thành công
                        Toast.makeText(ActivitySuaThuongHieu.this, "Cập nhật thương hiệu thành công.", Toast.LENGTH_SHORT).show();
                        // Báo thành công cho startActivityForResult xử lý
                        setResult(RESULT_OK);
                        // Có thể điều hướng đến một Activity khác hoặc cập nhật UI
                        finish(); // Ví dụ: Đóng Activity
                        break;

                    case "false":
                        Toast.makeText(ActivitySuaThuongHieu.this, "Cập nhật thương hiệu thất bại..Giá trị biến  Response là: " + response, Toast.LENGTH_SHORT).show();
                        Log.e("loi_cua_toi", "Cập nhật thương hiệu thất bại từ server.Giá trị biến  Response là: " + response);
                        break;

                    case "erro them hinh":
                        Toast.makeText(ActivitySuaThuongHieu.this, "Không thể thêm hình ảnh vào sever. Giá trị biến Response là: " + response, Toast.LENGTH_SHORT).show();
                        Log.e("loi_cua_toi", "Không thể thêm hình ảnh vào sever. Giá trị biến Response là: " + response);
                        break;

                    default:
                        Toast.makeText(ActivitySuaThuongHieu.this, "Đã xảy ra lỗi: " + response, Toast.LENGTH_SHORT).show();
                        Log.e("loi_cua_toi", "Phản hồi không xác định từ server.Giá trị biến Response là: " + response);
                        break;
                }
            }

            @Override
            public void baoLoiCuaOnErrorResponse(VolleyError error) {

            }
        });
    }

    public void xacNhanSuaThuongHieu() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivitySuaThuongHieu.this);

        alertDialog.setMessage("Bạn có muốn cập nhật thương hiệu " + thuongHieu.getTenThuongHieu() + " không?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                suaThuongHieu();
            }
        });

        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private boolean kiemTraThongTin() {
        String tenThuongHieu = editTextTenThuongHieu.getText().toString().trim();
        String moTaThuongHieu = editTextMoTaThuongHieu.getText().toString().trim();
        String trangWebThuongHieu = editTextTrangWebThuongHieu.getText().toString().trim();

        Boolean result = true;
        // Kiểm tra xem các trường có rỗng hay không
        if (tenThuongHieu.isEmpty()) {
            editTextTenThuongHieu.setError("Vui lòng nhập tên thương hiệu");
            result = false;
        }

        if (moTaThuongHieu.isEmpty()) {
            editTextMoTaThuongHieu.setError("Vui lòng nhập mô tả thương hiệu");
            result = false;
        }

        if (trangWebThuongHieu.isEmpty()) {
            editTextTrangWebThuongHieu.setError("Vui lòng nhập trang web thương hiệu");
            result = false;
        }

        // Nếu tất cả các trường đều hợp lệ
        return result;
    }




    // Phương thức ánh xạ các thành phần từ giao diện
    private void anhXa() {
        editTextTenThuongHieu = findViewById(R.id.editTextTenThuongHieu_activity_suathuonghieu);
        editTextMoTaThuongHieu = findViewById(R.id.editTextMoTaThuongHieu_activity_suathuonghieu);
        editTextTrangWebThuongHieu = findViewById(R.id.editTextTrangWebThuongHieu_activity_suathuonghieu);
        buttonChonLogo = findViewById(R.id.buttonChonLogo_activity_suathuonghieu);
        buttonSuaThuongHieu = findViewById(R.id.buttonCapNhatThuongHieu_activity_suathuonghieu);
        imageViewLogo = findViewById(R.id.imageViewLogo_activity_suathuonghieu);
    }
}
