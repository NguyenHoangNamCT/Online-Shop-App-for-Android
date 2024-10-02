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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class ActivityThemThuongHieu extends AppCompatActivity {

    // Khai báo các biến cho từng thành phần giao diện
    private EditText editTextTenThuongHieu;
    private EditText editTextMoTaThuongHieu;
    private EditText editTextTrangWebThuongHieu;
    private Uri uriSelectedImage;
    private Button buttonChonLogo;

    int REQUEST_CODE_CHON_HINH = 123;

    private ImageView imageViewLogo;
    private Button buttonThemThuongHieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thuong_hieu);
        anhXa();



        // Thiết lập sự kiện cho các nút (ví dụ)
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

        buttonThemThuongHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 Code để lưu thông tin thương hiệu
                if(kiemTraDuLieuDauVao()){
                    themThuongHieu();
                }
                else
                    return;


            }
        });
    }

    public void anhXa(){
        // Ánh xạ các thành phần giao diện với biến
        editTextTenThuongHieu = findViewById(R.id.editTextTenThuongHieu_activity_themthuonghieu);
        editTextMoTaThuongHieu = findViewById(R.id.editTextMoTaThuongHieu_activity_themthuonghieu);
        editTextTrangWebThuongHieu = findViewById(R.id.editTextTrangWebThuongHieu_activity_themthuonghieu);
        buttonChonLogo = findViewById(R.id.buttonChonLogo_activity_themthuonghieu);
        imageViewLogo = findViewById(R.id.imageViewLogo_activity_themthuonghieu);
        buttonThemThuongHieu = findViewById(R.id.buttonThemThuongHieu_activity_themthuonghieu);
    }

    public boolean kiemTraDuLieuDauVao() {
        // Lấy giá trị từ các EditText mà người dùng đã nhập
        String tenThuongHieu = editTextTenThuongHieu.getText().toString().trim();
        String moTaThuongHieu = editTextMoTaThuongHieu.getText().toString().trim();
        String trangWebThuongHieu = editTextTrangWebThuongHieu.getText().toString().trim();

        // Kiểm tra các trường có rỗng hay không
        if (tenThuongHieu.isEmpty()) {
            editTextTenThuongHieu.setError("Vui lòng nhập tên thương hiệu");
            return false;
        }

        if (moTaThuongHieu.isEmpty()) {
            editTextMoTaThuongHieu.setError("Vui lòng nhập mô tả thương hiệu");
            return false;
        }

        if (trangWebThuongHieu.isEmpty()) {
            editTextTrangWebThuongHieu.setError("Vui lòng nhập trang web của thương hiệu");
            return false;
        }

        // Kiểm tra xem người dùng đã chọn hình ảnh hay chưa
        if (uriSelectedImage == null) {
            // Nếu chưa chọn hình ảnh, có thể thông báo cho người dùng
            // Ví dụ: Toast hoặc thông báo lỗi
            Toast.makeText(this, "Vui lòng chọn logo cho thương hiệu", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Nếu tất cả dữ liệu đều đầy đủ
        return true;
    }

    //bước 2 xử lý dữ liệu sau khi chọn ảnh
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
            imageViewLogo.setImageURI(uriSelectedImage);
            //tắt ẩn imageView ban đầu
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

    public void themThuongHieu(){
        String url = getString(R.string.url_them_thuong_hieu);
        MyVolleyStringRequest.GuiStringRequestDenSever(url, ActivityThemThuongHieu.this, new MyVolleyStringRequest.thaoTacVoiStringRequestNay() {
            @Override
            public Map<String, String> guiMapLenSever(Map<String, String> param) {
                // Lấy giá trị từ các EditText mà người dùng đã nhập
                String tenThuongHieu = editTextTenThuongHieu.getText().toString().trim();
                String moTaThuongHieu = editTextMoTaThuongHieu.getText().toString().trim();
                String trangWebThuongHieu = editTextTrangWebThuongHieu.getText().toString().trim();
                String logoBase64 = chuyenAnhDuocChonThanhChuoiBase64(uriSelectedImage);

                // Đưa các giá trị vào param để gửi lên server
                param.put("tenThuongHieu", tenThuongHieu);
                param.put("moTaThuongHieu", moTaThuongHieu);
                param.put("trangWebThuongHieu", trangWebThuongHieu);
                param.put("logoBase64", logoBase64);
                return param;
            }

            @Override
            public void xuLyChuoiDocDuocTuSever(String response) {
                // Kiểm tra phản hồi từ server
                switch (response) {
                    case "success":
                        // Thông báo thêm thương hiệu thành công
                        Toast.makeText(ActivityThemThuongHieu.this, "Thêm thương hiệu thành công!", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish(); // Đóng Activity hiện tại và quay về Activity trước đó
                        break;

                    case "error":
                        // Thông báo thêm thương hiệu thất bại
                        Toast.makeText(ActivityThemThuongHieu.this, "Lỗi: Không thể thêm thương hiệu. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                        // Ghi lại lỗi vào log
                        Log.e("loi cua toi", "Lỗi khi thêm thương hiệu, sever trả về chuổi: ERRO");
                        break;

                    case "erro them hinh":
                        // Thông báo lỗi khi lưu hình ảnh thất bại
                        Toast.makeText(ActivityThemThuongHieu.this, "Lỗi: Không thể lưu hình ảnh. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                        // Ghi lại lỗi vào log
                        Log.e("loi cua toi", "Lỗi khi lưu hình ảnh trên server, sever trả về chuổi: erro them hinh");
                        break;

                    default:
                        // Thông báo lỗi không xác định hoặc các trường hợp phản hồi khác
                        Toast.makeText(ActivityThemThuongHieu.this, "Lỗi không xác định: " + response, Toast.LENGTH_SHORT).show();
                        // Ghi lại phản hồi không xác định vào log
                        Log.e("loi cua toi", "Phản hồi không xác định từ server: sever trả về 1 báo lỗi, lên sever đọc" + response);
                        break;
                }
            }

            @Override
            public void baoLoiCuaOnErrorResponse(VolleyError error) {
                Toast.makeText(ActivityThemThuongHieu.this, "Lỗi volley: cụ thể: " + error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("loi cua toi", "Lỗi volley, cụ thể: " + error.getMessage());
            }
        });
    }
}
