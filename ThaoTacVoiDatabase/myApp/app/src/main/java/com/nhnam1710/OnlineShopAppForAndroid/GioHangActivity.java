package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class GioHangActivity extends AppCompatActivity {

    private ListView listViewGioHang;
    private Button btnThanhToan;
    private TextView tvGioHangRong;
    private ArrayList<SanPhamTrongGio> sanPhamTrongGioArrayList;
    AdapterGioHang adapterGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhXa();

        sanPhamTrongGioArrayList = new ArrayList<>();
        adapterGioHang = new AdapterGioHang(this, R.layout.dong_gio_hang, sanPhamTrongGioArrayList);
        listViewGioHang.setAdapter(adapterGioHang);

        loadGiaHang();
//        themDuLieuAo();
        adapterGioHang.notifyDataSetChanged();

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(GioHangActivity.this, CheckOutActivity.class);
                intent1.putExtra("danhSachSP", sanPhamTrongGioArrayList);
                startActivity(intent1);
            }
        });
    }

    public void anhXa(){
        listViewGioHang = findViewById(R.id.listViewGioHang_activity_gio_hang);
        btnThanhToan = findViewById(R.id.btnThanhToan_activity_gio_hang);
        tvGioHangRong = findViewById(R.id.tvGioHangRong_activity_gio_hang);
    }

    public void loadGiaHang(){
        String url = getString(R.string.url_show_full_gio_hang);
        MyVolleyRequest.layJsonArrayTuSeverCoGuiKemDuLieu(url, GioHangActivity.this, new MyVolleyRequest.XuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu() {
            @Override
            public Map<String, String> guiMapLenSever(Map<String, String> param) {
                //mục đích: gửi tên đăng nhập và mật khẩu lên cho server thay thế id người dùng để load giỏ hàng về (mục đích đảm bảo người dùng chính là chủ nick)
                param.put("userName", GlobalClass.getUserName());
                param.put("password", GlobalClass.getPassword());
                return param;
            }

            @Override
            public void jsonArrayNhanDuocTuServer(JSONArray response){
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        SanPhamTrongGio sanPhamTrongGio = new SanPhamTrongGio(jsonObject);
                        Log.d("loi cua toi", "\nSản phẩm trong giỏ thứ: " + i + sanPhamTrongGio.toString()+ "\n") ;
                        sanPhamTrongGioArrayList.add(sanPhamTrongGio);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                adapterGioHang.notifyDataSetChanged();
            }

            @Override
            public void chuoiBaoLoiCuaVolley(String VolleyErrorMessage) {
                Toast.makeText(GioHangActivity.this, "Load giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                Log.e("loi cua toi", "Load giỏ hàng thất bại: " + VolleyErrorMessage);
            }
        });
    }

//    public static SanPhamTrongGio RandomSanPhamTrongGio(){
//        Random random = new Random();
//        String[] TEN_SAN_PHAM = {"Áo thun", "Quần jean", "Áo khoác", "Giày sneaker", "Túi xách"};
//        int[] GIA = {100000, 200000, 150000, 300000, 250000};
//        int[] SO_LUONG = {1, 2, 3, 4, 5};
//
//        int randomId = random.nextInt(1000); // Id ngẫu nhiên
//        String randomTenSanPham = TEN_SAN_PHAM[random.nextInt(TEN_SAN_PHAM.length)];
//        int randomGia = GIA[random.nextInt(GIA.length)];
//        int randomSoLuong = SO_LUONG[random.nextInt(SO_LUONG.length)];
//        boolean randomChonMua = false;  // Chọn mua ngẫu nhiên
//
//        return (new SanPhamTrongGio(randomId, randomTenSanPham, randomGia, randomSoLuong, "randomHinhAnh", randomChonMua));
//    }
//
//    public void themDuLieuAo(){
//        for(int i = 0; i < 20; i++){
//            sanPhamTrongGioArrayList.add(GioHangActivity.RandomSanPhamTrongGio());
//        }
//        adapterGioHang.notifyDataSetChanged();
//        Log.d("GioHangActivity", "Số sản phẩm trong giỏ: " + sanPhamTrongGioArrayList.size()); // Log số lượng sản phẩm
//    }


}