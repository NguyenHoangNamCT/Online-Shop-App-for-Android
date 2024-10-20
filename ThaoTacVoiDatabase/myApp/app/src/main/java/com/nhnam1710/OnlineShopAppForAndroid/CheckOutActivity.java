package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class CheckOutActivity extends AppCompatActivity {

    ListView listViewDSSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        anhXa();

        Intent intent = getIntent();
        ArrayList<SanPhamTrongGio> arrayListSP = (ArrayList<SanPhamTrongGio>) intent.getSerializableExtra("danhSachSP");
        AdapterCheckOut adapterCheckOut = new AdapterCheckOut(this, R.layout.dong_check_out, arrayListSP);
        listViewDSSP.setAdapter(adapterCheckOut);

        loadCheckOut(arrayListSP, adapterCheckOut);


    }

    public void loadCheckOut(ArrayList<SanPhamTrongGio> arrayListSP, AdapterCheckOut adapterCheckOut){
        for(int i = 0; i < arrayListSP.size();)
            if(arrayListSP.get(i).getChonMua() == false)
                arrayListSP.remove(i);
            else
                i++;
        adapterCheckOut.notifyDataSetChanged();
    }

    public void anhXa(){    
        listViewDSSP = findViewById(R.id.listViewDanhSachSanPham_check_out_activity);
    }
}