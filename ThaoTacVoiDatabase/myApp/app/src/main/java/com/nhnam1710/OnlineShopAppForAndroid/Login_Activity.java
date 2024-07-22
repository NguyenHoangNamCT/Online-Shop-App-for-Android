package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                String tenDangNhap = editTextTenDangNhap.getText().toString();
                String password = editTextPassword.getText().toString();

                // Thực hiện kiểm tra đăng nhập ở đây (giả sử kiểm tra thành công)
                if (tenDangNhap.equals("123") && password.equals("123")) {
                    Toast.makeText(Login_Activity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    // Chuyển sang Activity khác
                    Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login_Activity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void anhXa() {
        editTextTenDangNhap = findViewById(R.id.editTextTenDangNhap_activity_login);
        editTextPassword = findViewById(R.id.editTextPassword_activity_login);
        buttonDangNhap = findViewById(R.id.buttonDangNhap_activity_login);
    }
}
