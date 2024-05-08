package com.nhnam1710.OnlineShopAppForAndroid;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyVolleyStringRequest {
    public interface thaoTacVoiStringRequestNay{
        public Map<String, String> guiMapLenSever(Map<String, String> param);
        public void xuLyChuoiDocDuocTuSever(String response);
        public void baoLoiCuaOnErrorResponse(VolleyError error);
    }
    public static void GuiStringRequestDenSever(String urlSever, Context context, thaoTacVoiStringRequestNay thaoTac){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlSever,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        thaoTac.xuLyChuoiDocDuocTuSever(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        thaoTac.baoLoiCuaOnErrorResponse(error);
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                return thaoTac.guiMapLenSever(param);
            }
        };

        requestQueue.add(stringRequest);
    }
}


