package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityQuanLyThuongHieu extends AppCompatActivity {

    // Khai báo các biến để ánh xạ
    private Toolbar toolbarQuanLyThuongHieu;
    private TextView textViewQuanLyThuongHieu;
    private ListView listViewThuongHieu;
    private Button buttonThemThuongHieu;

    private ArrayList<ThuongHieu> thuongHieuArrayList;
    private AdapterQuanLyThuongHieu thuongHieuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_thuong_hieu);
        anhXa();

        thuongHieuArrayList = new ArrayList<>();
        thuongHieuAdapter = new AdapterQuanLyThuongHieu(ActivityQuanLyThuongHieu.this, thuongHieuArrayList, R.layout.dong_thuong_hieu);
        listViewThuongHieu.setAdapter(thuongHieuAdapter);


        //các sự kiện
        buttonThemThuongHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityQuanLyThuongHieu.this, ActivityThemThuongHieu.class);
                startActivity(intent);
            }
        });
    }

    public void loadData(){
        String url = getString(R.string.url_show_full_thuong_hieu);
        MyVolleyRequest.layJsonArrayTuSever(url, ActivityQuanLyThuongHieu.this, new MyVolleyRequest.XuLyDuLieuNhanDuocTuSever() {
            @Override
            public void JsonArrayNhanDuocTuSever(JSONArray response) {
                thuongHieuArrayList.clear();
                try {
                    for(int i = 0; i < response.length(); i++){
                        JSONObject jsonObjectTemp = response.getJSONObject(i);
                        ThuongHieu thuongHieuTemp = new ThuongHieu(jsonObjectTemp);
                        thuongHieuArrayList.add(thuongHieuTemp);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                thuongHieuAdapter.notifyDataSetChanged();
            }

            @Override
            public void chuoiBaoLoiCuaVolley(String VolleyErrorMessage) {
                //chưa cần cái này
            }
        });
    }


    private void anhXa() {
        // Ánh xạ các thành phần giao diện với các view trong layout
        toolbarQuanLyThuongHieu = findViewById(R.id.toolbarQuanLyThuongHieu_activity_quan_ly_thuong_hieu);
        textViewQuanLyThuongHieu = findViewById(R.id.textViewQuanLyThuongHieu_activity_quan_ly_thuong_hieu);
        listViewThuongHieu = findViewById(R.id.listViewThuongHieu_activity_quan_ly_thuong_hieu);
        buttonThemThuongHieu = findViewById(R.id.buttonThemThuongHieu_activity_quan_ly_thuong_hieu);
    }
}
