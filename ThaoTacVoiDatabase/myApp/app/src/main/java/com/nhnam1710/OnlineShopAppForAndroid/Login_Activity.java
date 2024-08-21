package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.Map;

public class Login_Activity extends AppCompatActivity {

    private EditText editTextTenDangNhap;
    private EditText editTextPassword;
    private Button buttonDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();

        buttonDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //phương thức này sẽ lấy dữ liệu người dùng nhập vào và mã hóa xong gửi lên sever
                guiTaiKhoanMatKhauLenSever();

//                //mã hóa tk mk người dùng
//                String tenDangNhap = GlobalClass.getMD5(editTextTenDangNhap.getText().toString());
//                String password = GlobalClass.getMD5(editTextPassword.getText().toString());
//
//                // Thực hiện kiểm tra đăng nhập ở đây (giả sử kiểm tra thành công)
//                if (tenDangNhap.equals("123") && password.equals("123")) {
//                    //nếu đăng nhập thành công thì lưu tk mk vào global class và thông báo và chuyển màn hình đến main activity
//                    Toast.makeText(Login_Activity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                    GlobalClass.setUserName(editTextTenDangNhap.getText().toString());
//                    GlobalClass.setPassword(editTextPassword.getText().toString());
//                    Intent intent = new Intent(Login_Activity.this, MainActivity.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(Login_Activity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
//                }
//                //-----------------------------------------------------------------
            }
        });
    }

    private void anhXa() {
        editTextTenDangNhap = findViewById(R.id.editTextTenDangNhap_activity_login);
        editTextPassword = findViewById(R.id.editTextPassword_activity_login);
        buttonDangNhap = findViewById(R.id.buttonDangNhap_activity_login);
    }

    public void guiTaiKhoanMatKhauLenSever(){
        String urlNhanTaiKhoanVaMatKhau = getResources().getString(R.string.url_nhan_tai_khoan_va_mat_khau_va_xu_ly);
        MyVolleyStringRequest.GuiStringRequestDenSever(urlNhanTaiKhoanVaMatKhau, Login_Activity.this, new MyVolleyStringRequest.thaoTacVoiStringRequestNay() {
            @Override
            public Map<String, String> guiMapLenSever(Map<String, String> param) {
                String tenDangNhapMD5 = GlobalClass.getMD5(editTextTenDangNhap.getText().toString().trim()),
                        matKhauMD5 = GlobalClass.getMD5(editTextPassword.getText().toString());

                param.put("tenDangNhap", tenDangNhapMD5);
                param.put("matKhau", matKhauMD5);
                return param;
            }

            @Override
            public void xuLyChuoiDocDuocTuSever(String response) {
                //biến response chính là dòng chữ mà sever in ra sau khi xử lý
            }

            @Override
            public void baoLoiCuaOnErrorResponse(VolleyError error) {
                //báo lỗi của cua OnErrorResponse trong myVolleyStringRequest (class này gọi lại tức là lỗi xuất phát từ đây, nhưng code thì ở bên kia) code bên đây nếu lỗi thì lỗi dữ liệu đầu vào (biến kiểu Map tên param)

            }
        });
    }
}
