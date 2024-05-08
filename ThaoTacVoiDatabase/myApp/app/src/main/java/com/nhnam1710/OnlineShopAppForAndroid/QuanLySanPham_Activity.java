package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


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
import java.util.Map;

public class QuanLySanPham_Activity extends AppCompatActivity {
    TextView textViewQuanLySanPham;
    ListView listViewSanPham;
    Button buttonThemSanPham;
    Toolbar toolbar;

    ArrayList<SanPham> sanPhamArrayList;
    AdapterQuanLySanPham adapterQuanLySanPham;
    int REQUEST_CODE_THEM_SAN_PHAM = 2837;



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

        buttonThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLySanPham_Activity.this, ThemSanPhamActivity.class);
                startActivityForResult(intent, REQUEST_CODE_THEM_SAN_PHAM);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_THEM_SAN_PHAM && resultCode == RESULT_OK && data != null){

        }
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

    public void xoaSanPham(String urlSV, int idSP){
        MyVolleyStringRequest.GuiStringRequestDenSever(urlSV, QuanLySanPham_Activity.this, new MyVolleyStringRequest.thaoTacVoiStringRequestNay() {
            @Override
            public Map<String, String> guiMapLenSever(Map<String, String> param) {
                param.put("id_san_pham_tu_app", String.valueOf(idSP));
                return param;
            }

            @Override
            public void xuLyChuoiDocDuocTuSever(String response) {
                if(response.equals("success")){
                    Toast.makeText(QuanLySanPham_Activity.this, "Xoá sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    String urlGetDataSanPham = getResources().getString(R.string.url_sever);
                    updateData(urlGetDataSanPham);
                } else if(response.equals("erro")){
                    Log.e("loi cua toi", "Lỗi xoá sản phẩm từ sever, cụ thể là file xoaSanPham.php");
                    Toast.makeText(QuanLySanPham_Activity.this, "Xoá thất bại", Toast.LENGTH_SHORT);
                } else if(response.equals("erro from app")){
                    Log.e("loi cua toi", "Lỗi từ app gửi dữ liệu lên không phải kiểu post");
                    Toast.makeText(QuanLySanPham_Activity.this, "Xoá thất bại", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void baoLoiCuaOnErrorResponse(VolleyError error) {
                Log.e("loi cua toi", "Báo lỗi của volley về việc xoá sản phẩm cụ thể trong phương thức xoaSanPham ở class QuanLySanPham_Activity =>" +error.getMessage());
            }
        });
    }

    public void updateData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(QuanLySanPham_Activity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        sanPhamArrayList.clear();
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