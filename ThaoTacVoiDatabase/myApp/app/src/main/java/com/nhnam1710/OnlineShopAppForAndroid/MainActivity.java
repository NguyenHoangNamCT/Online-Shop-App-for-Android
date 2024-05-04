package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolbar;
    GridView gridViewDanhSachSP;
    ArrayList<SanPham> arrayListSanPham;
    AdapterSanPham adapterSanPham;

    TextView tenShop;

    String url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = getResources().getString(R.string.url_sever);

        anhXa();
        readJson(url);

        adapterSanPham = new AdapterSanPham(this, R.layout.dong_san_pham, arrayListSanPham);
        gridViewDanhSachSP.setAdapter(adapterSanPham);
//
        setSupportActionBar(myToolbar); //thay thế action bar mặc định thành toolbar

        tenShop = findViewById(R.id.textViewTenShop);
        tenShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailSanPham.class);
                startActivity(intent);
            }
        });


    }

    public void anhXa(){
        myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        gridViewDanhSachSP = findViewById(R.id.gridViewDanhSachSP);
    }


    public void readJson(String url){
        arrayListSanPham = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                arrayListSanPham.add(SanPham.fromJsonOject(jsonObject));
                            } catch (JSONException e) {
                                Log.d("loi cua toi", "Lỗi trycatch ở phương thức readJson ở Main Activity, cụ thể là trong onRespone");
                                throw new RuntimeException(e);
                            }
                        }
                        adapterSanPham.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi: " + error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("loi cua toi", "Lỗi ở phương thức readJson ở Main Activity cụ thể là trong onErrorResponse: " + error.toString());
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuCartOnMainactivity){
            Intent intent = new Intent(MainActivity.this, GioHangActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.menuQuanLySanPham){
            Intent intent = new Intent(MainActivity.this, QuanLySanPham_Activity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}