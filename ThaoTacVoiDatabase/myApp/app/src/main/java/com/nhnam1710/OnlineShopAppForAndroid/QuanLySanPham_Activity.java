package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuanLySanPham_Activity extends AppCompatActivity {
    TextView textViewQuanLySanPham;
    ListView listViewSanPham;
    Button buttonThemSanPham;
    Toolbar toolbar;

    ArrayList<SanPham> sanPhamArrayList;
    AdapterQuanLySanPham adapterQuanLySanPham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);
        anhXa();
        setSupportActionBar(toolbar);

        String url = getResources().getString(R.string.url_sever);
        adapterQuanLySanPham = new AdapterQuanLySanPham(QuanLySanPham_Activity.this, R.layout.dong_quan_ly_san_pham, sanPhamArrayList);
        listViewSanPham.setAdapter(adapterQuanLySanPham);
        readJsonArraySanPham(QuanLySanPham_Activity.this, url);
    }

    private void anhXa() {
        sanPhamArrayList = new ArrayList<>();
        textViewQuanLySanPham = findViewById(R.id.textViewQuanLySanPham_activity_quan_ly_san_pham);
        listViewSanPham = findViewById(R.id.listViewSanPham_activity_quan_ly_san_pham);
        buttonThemSanPham = findViewById(R.id.buttonThemSanPham_activity_quan_ly_san_pham);
        toolbar = findViewById(R.id.toolbarQuanLySanPham_activity_quan_ly_san_pham);
    }


    //danh sách phương thức tĩnh
    public void readJsonArraySanPham(Context context, String url){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                sanPhamArrayList.add(SanPham.fromJsonOject(jsonObject));
                            }
                            catch (JSONException e) {
                                Log.e("loi cua toi", "Lỗi ở phương thức readJsonArraySanPham() trong class QuanLySanPham_Activity: " + e.getMessage());
                                throw new RuntimeException(e);
                            }
                        }
                        adapterQuanLySanPham.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}