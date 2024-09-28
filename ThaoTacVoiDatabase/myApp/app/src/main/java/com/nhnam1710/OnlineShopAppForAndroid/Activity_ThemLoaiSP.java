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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class Activity_ThemLoaiSP extends AppCompatActivity {

    // Khai báo các biến ánh xạ
    private EditText editTextTenLoaiSanPham, editTextMoTa;
    private Button buttonChonHinhAnh, buttonThemLoaiSP;
    private ImageView imageViewHinhAnh;
    private int REQUEST_CODE_CHON_HINH = 113;

    private Uri uriSelectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai_sp);

        // Ánh xạ các thành phần trong layout
        anhXa();

        //bước 1 bắt sự kiện chọn hình
        buttonChonHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent.ACTION_GET_CONTENT hành động cho người dùng chọn 1 tệp từ thiết bị
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //chỉ định loại tệp người dùng chọn là một hình ảnh
                intent.setType("image/*");
                //Khi bạn gọi Intent.createChooser(intent, "Chọn hình ảnh"), phương thức này sẽ tạo ra một hộp thoại mà trong đó người dùng có thể chọn ứng dụng mà họ muốn sử dụng để chọn hình ảnh từ thiết bị. Hộp thoại này giúp cải thiện trải nghiệm người dùng bằng cách cho phép họ chọn từ nhiều ứng dụng thay vì chỉ một ứng dụng cụ thể.
                startActivityForResult(Intent.createChooser(intent, "Chọn hình ảnh"), REQUEST_CODE_CHON_HINH);
                //startActivityForResult sẽ nhận kết quả từ hành động chọn ảnh ở phương thức onActivityResult
            }
        });

        //bước cuối bắt sự kiện cho nút thêm (gọi lại phương thức gửi dữ liệu loại sản phẩm người dùng nhập lên sever để thêm)
        buttonThemLoaiSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_ThemLoaiSP.this, "Đã click", Toast.LENGTH_SHORT).show();
                themLoaiSP();
            }
        });
    }

    //bước 2 xử lý dữ liệu sau khi chọn ảnh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //        requestCode == REQUEST_CODE_CHON_HINH:
        //
        //Kiểm tra xem mã yêu cầu (request code) có phải là REQUEST_CODE_CHON_HINH hay không. Mã này được xác định ở phần trước trong mã của bạn và được sử dụng để phân biệt các yêu cầu khác nhau khi có nhiều hành động có thể được thực hiện trong ứng dụng.
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


    //bước 4 sau khi có được hình ảnh dưới dạng chuổi base64 tiến hành gửi nó lên sever như các biến bình thường khác
    public void themLoaiSP(){
        String url = getString(R.string.url_them_loai_sp);
        MyVolleyStringRequest.GuiStringRequestDenSever(url, Activity_ThemLoaiSP.this, new MyVolleyStringRequest.thaoTacVoiStringRequestNay() {
            @Override
            public Map<String, String> guiMapLenSever(Map<String, String> param) {
                param.put("tenLoaiSP", editTextTenLoaiSanPham.getText().toString());
                param.put("moTa", editTextMoTa.getText().toString());
                param.put("hinhAnh", chuyenAnhDuocChonThanhChuoiBase64(uriSelectedImage));
                return param;
            }

            @Override
            public void xuLyChuoiDocDuocTuSever(String response) {
                Log.e("xoa ngay sau khi test", response);
                if (response.equals("success")) {
                    // Phản hồi thành công
                    Intent intent = new Intent(Activity_ThemLoaiSP.this, Activity_QuanLyLoaiSanPham.class);
                    startActivity(intent);
                    Toast.makeText(Activity_ThemLoaiSP.this, "Thêm loại sản phẩm thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void baoLoiCuaOnErrorResponse(VolleyError error) {

            }
        });
    }

    // Phương thức để ánh xạ các thành phần
    private void anhXa() {
        editTextTenLoaiSanPham = findViewById(R.id.editTextTenLoaiSanPham_activity_themloaisp);
        editTextMoTa = findViewById(R.id.editTextMoTa_activity_themloaisp);
        buttonChonHinhAnh = findViewById(R.id.buttonChonHinhAnh_activity_themloaisp);
        buttonThemLoaiSP = findViewById(R.id.buttonThemLoaiSP_activity_themloaisp);
        imageViewHinhAnh = findViewById(R.id.imageViewHinhAnh_activity_themloaisp);
    }
}
