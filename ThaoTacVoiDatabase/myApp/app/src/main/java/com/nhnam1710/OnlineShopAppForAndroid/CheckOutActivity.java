package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public class CheckOutActivity extends AppCompatActivity {

    ListView listViewDSSP;

    ArrayList<SanPhamTrongGio> arrayListSP;
    AdapterCheckOut adapterCheckOut;
    private TextView textViewTieuDe, textViewTongTien;
    private Button buttonDatHang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        anhXa();

        loadCheckOut();

        buttonDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanDatHang();
            }
        });

    }

    public void guiThongTinDatHangLenServerDeTaoDonHang(){
        String url = getString(R.string.url_tao_don_hang);
//        Toast.makeText(CheckOutActivity.this, "So luong la: " + arrayListSP.size(), Toast.LENGTH_SHORT).show();
        MyVolleyStringRequest.GuiStringRequestDenSever(url, CheckOutActivity.this, new MyVolleyStringRequest.thaoTacVoiStringRequestNay() {
            @Override
            public Map<String, String> guiMapLenSever(Map<String, String> param) {
                try {
                    // Chuyển ArrayList thành JSON
                    JSONArray jsonArray = new JSONArray();
                    for (SanPhamTrongGio sanPham : arrayListSP) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("idSanPham", sanPham.getId());
                        jsonObject.put("soLuong", sanPham.getSoLuong());
                        jsonArray.put(jsonObject);
                    }
                    //đưa thông tin đăng nhập để xác thực
                    param.put("tenDangNhap", GlobalClass.getUserName());
                    param.put("matKhau", GlobalClass.getPassword());
                    // Đưa JSON vào param
                    param.put("gioHang", jsonArray.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return param;
            }

            @Override
            public void xuLyChuoiDocDuocTuSever(String response) {
                if (response.contains("Nguoi_dung_khong_ton_tai_ID_tim_duoc_la_null")) {
                    // Xử lý khi không tìm thấy người dùng
                    Toast.makeText(CheckOutActivity.this, "Người dùng không tồn tại!", Toast.LENGTH_SHORT).show();
                } else if (response.contains("server_that_bai_trong_viec_doc_arraylist_cac_san_pham_chon_mua_tu_app")) {
                    // Xử lý khi server không đọc được giỏ hàng
                    Toast.makeText(CheckOutActivity.this, "Server không đọc được giỏ hàng!", Toast.LENGTH_SHORT).show();
                } else if (response.contains("serser_khong_nhan_duoc_user_name!_Khi_dat_hang")) {
                    // Xử lý khi không nhận được tên đăng nhập
                    Toast.makeText(CheckOutActivity.this, "Tên đăng nhập không được gửi tới server!", Toast.LENGTH_SHORT).show();
                } else if (response.contains("serser_khong_nhan_duoc_mat_khau!_Khi_dat_hang")) {
                    // Xử lý khi không nhận được mật khẩu
                    Toast.makeText(CheckOutActivity.this, "Mật khẩu không được gửi tới server!", Toast.LENGTH_SHORT).show();
                } else if (response.contains("serser_khong_nhan_duoc_gio_hang!_Khi_dat_hang")) {
                    // Xử lý khi không nhận được giỏ hàng
                    Toast.makeText(CheckOutActivity.this, "Giỏ hàng không được gửi tới server!", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý cho các trường hợp khác hoặc thành công
                    Toast.makeText(CheckOutActivity.this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
                }
                Log.d("loi cua toi", "----Trong class: " + getClass().getName() + " nhận được chuổi thông báo từ server là: " + response);
            }

            @Override
            public void baoLoiCuaOnErrorResponse(VolleyError error) {
            }
        });
    }



    public void xacNhanDatHang() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CheckOutActivity.this);

        alertDialog.setMessage("Xác nhận đặt hàng");
        alertDialog.setPositiveButton("Đặt hàng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                guiThongTinDatHangLenServerDeTaoDonHang();
            }
        });

        alertDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    public void xacNhanXoaThuongHieu() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CheckOutActivity.this);

        alertDialog.setMessage("Xác nhận đặt hàng");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

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

    public void nhanArrayListVaKhoiTaoListviewVaAdapter(){
        arrayListSP = (ArrayList<SanPhamTrongGio>) getIntent().getSerializableExtra("danhSachSP");
        adapterCheckOut = new AdapterCheckOut(CheckOutActivity.this, R.layout.dong_check_out, arrayListSP);
        listViewDSSP.setAdapter(adapterCheckOut);
        Log.e("test loi", "1"+arrayListSP.toString());
    }
    public void loadCheckOut(){
        //nhận dữ liệu từ trang giỏ hàng
        nhanArrayListVaKhoiTaoListviewVaAdapter();
        Log.e("test loi", "2"+arrayListSP.toString());

        for (int i = 0; i < arrayListSP.size(); )
            if (arrayListSP.get(i).getChonMua() == false)
                arrayListSP.remove(i);
            else
                i++;
        if (arrayListSP == null)
            Toast.makeText(CheckOutActivity.this, "khongNhanDuoc", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(CheckOutActivity.this, arrayListSP.toString(), Toast.LENGTH_SHORT).show();
        adapterCheckOut.notifyDataSetChanged();
    }

    public void anhXa(){
        // Ánh xạ các thành phần giao diện
        textViewTieuDe = findViewById(R.id.textViewTieuDe_check_out_activity);
        listViewDSSP = findViewById(R.id.listViewDanhSachSanPham_check_out_activity);
        textViewTongTien = findViewById(R.id.textViewTongTien_check_out_activity);
        buttonDatHang = findViewById(R.id.buttonDatHang_check_out_activity);
    }
}