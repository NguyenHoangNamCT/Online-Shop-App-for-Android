package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

    private TextView textViewTieuDe, textViewTongTien;
    private Button buttonDatHang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        anhXa();

        Intent intent = getIntent();
        arrayListSP = (ArrayList<SanPhamTrongGio>) intent.getSerializableExtra("danhSachSP");
        AdapterCheckOut adapterCheckOut = new AdapterCheckOut(this, R.layout.dong_check_out, arrayListSP);
        listViewDSSP.setAdapter(adapterCheckOut);

        loadCheckOut(arrayListSP, adapterCheckOut);


    }

    public void guiThongTinDatHangLenServerDeTaoDonHang(ArrayList<SanPhamTrongGio> arrayListSP){
        String url = getString(R.string.url_tao_don_hang);
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

            }

            @Override
            public void baoLoiCuaOnErrorResponse(VolleyError error) {

            }
        };
    }

    public void xacNhanDatHang(ArrayList<SanPhamTrongGio> arrayListSP) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CheckOutActivity.this);

        alertDialog.setMessage("Xác nhận đặt hàng");
        alertDialog.setPositiveButton("Đặt hàng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

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

    public void loadCheckOut(ArrayList<SanPhamTrongGio> arrayListSP, AdapterCheckOut adapterCheckOut){
        arrayListSP.clear();
        for(int i = 0; i < arrayListSP.size();)
            if(arrayListSP.get(i).getChonMua() == false)
                arrayListSP.remove(i);
            else
                i++;
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