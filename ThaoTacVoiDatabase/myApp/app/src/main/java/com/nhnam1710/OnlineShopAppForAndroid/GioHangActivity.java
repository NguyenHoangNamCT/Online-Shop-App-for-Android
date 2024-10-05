package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GioHangActivity extends AppCompatActivity {

    private ListView listViewGioHang;
    private Button btnThanhToan;
    private TextView tvGioHangRong;
    private ArrayList<SanPhamTrongGio> sanPhamTrongGioArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhXa();

        themDuLieuAo();
        AdapterGioHang adapterGioHang = new AdapterGioHang(this, R.layout.dong_gio_hang, sanPhamTrongGioArrayList);
        listViewGioHang.setAdapter(adapterGioHang);

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

    public static SanPhamTrongGio RandomSanPhamTrongGio(){
        Random random = new Random();
        String[] TEN_SAN_PHAM = {"Áo thun", "Quần jean", "Áo khoác", "Giày sneaker", "Túi xách"};
        int[] GIA = {100000, 200000, 150000, 300000, 250000};
        int[] SO_LUONG = {1, 2, 3, 4, 5};

        int randomId = random.nextInt(1000); // Id ngẫu nhiên
        String randomTenSanPham = TEN_SAN_PHAM[random.nextInt(TEN_SAN_PHAM.length)];
        int randomGia = GIA[random.nextInt(GIA.length)];
        int randomSoLuong = SO_LUONG[random.nextInt(SO_LUONG.length)];
        int randomHinhAnh = R.drawable.ao;
        boolean randomChonMua = false;  // Chọn mua ngẫu nhiên

        return (new SanPhamTrongGio(randomId, randomTenSanPham, randomGia, randomSoLuong, randomHinhAnh, randomChonMua));
    }

    public void themDuLieuAo(){
        sanPhamTrongGioArrayList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            sanPhamTrongGioArrayList.add(GioHangActivity.RandomSanPhamTrongGio());
        }
    }
}