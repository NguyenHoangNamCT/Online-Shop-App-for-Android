package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolbar;
    GridView gridViewDanhSachSP;
    ArrayList<SanPham> arrayListSanPham;
    AdapterSanPham adapterSanPham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();

        themDuLieu();
        adapterSanPham = new AdapterSanPham(this, R.layout.dong_san_pham, arrayListSanPham);
        gridViewDanhSachSP.setAdapter(adapterSanPham);
//
        setSupportActionBar(myToolbar); //thay thế action bar mặc định thành toolbar
    }

    public void anhXa(){
        myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        gridViewDanhSachSP = findViewById(R.id.gridViewDanhSachSP);
    }

    public void themDuLieu(){
        arrayListSanPham = new ArrayList<>();
        arrayListSanPham.add(new SanPham(R.drawable.ao, "Áo sơ mi trắng", 10, 10000, 20));
        arrayListSanPham.add(new SanPham(R.drawable.ao, "Áo sơ mi trắng", 10, 10000, 20));
        arrayListSanPham.add(new SanPham(R.drawable.ao, "Áo sơ mi trắng", 10, 10000, 20));
        arrayListSanPham.add(new SanPham(R.drawable.ao, "Áo sơ mi trắng", 10, 10000, 20));
        arrayListSanPham.add(new SanPham(R.drawable.ao, "Áo sơ mi trắng", 10, 10000, 20));
        arrayListSanPham.add(new SanPham(R.drawable.ao, "Áo sơ mi trắng", 10, 10000, 20));
        arrayListSanPham.add(new SanPham(R.drawable.ao, "Áo sơ mi trắng", 10, 10000, 20));
        arrayListSanPham.add(new SanPham(R.drawable.ao, "Áo sơ mi trắng", 10, 10000, 20));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



}