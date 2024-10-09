package com.nhnam1710.OnlineShopAppForAndroid;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyVolleyRequest {

    private static final String TAG = MyVolleyRequest.class.getSimpleName();

    public interface XuLyDuLieuListener {
        void duLieuNhanDuoc(JSONArray danhSachThuongHieu, JSONArray danhSachLoaiSanPham);
        void onError(String errorMessage);
    }

    public interface XuLyVolleyListennerVaErroLayFullLoaiSanPham{
        public void danhSachLoaiSanPhamDocDuoc(JSONArray response);
        public void chuoiBaoLoiCuaVolley(String VolleyErrorMessage);
    }

    public interface XuLyDuLieuNhanDuocTuSever{
        public void JsonArrayNhanDuocTuSever(JSONArray response);
        public void chuoiBaoLoiCuaVolley(String VolleyErrorMessage);
    }

    public static void layJsonArrayTuSever(String url, Context context, XuLyDuLieuNhanDuocTuSever xuLyDuLieuNhanDuocTuSever){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        xuLyDuLieuNhanDuocTuSever.JsonArrayNhanDuocTuSever(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Báo lỗi lên
                        Toast.makeText(context, "log: loi cua toi, Lỗi volley: cụ thể lỗi là: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        //Log lên
                        Log.e("loi cua toi", "Lỗi ở phương thức layJsonArrayTuSever, onErrorResponse trong class MyVolleyRequest: cụ thể lỗi là: " + error.getMessage());
                        xuLyDuLieuNhanDuocTuSever.chuoiBaoLoiCuaVolley(error.getMessage());
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }


//    public interface XuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu{
//        public Map<String, String> guiMapLenSever(Map<String, String> param);
//        public void jsonArrayNhanDuocTuServer(JSONArray response) throws JSONException;
//        public void chuoiBaoLoiCuaVolley(String VolleyErrorMessage);
//    }

//    //nghe chatgpt nói json array request không hỗ trợ getParams để gửi dữ liệu
//    public static void layJsonArrayTuSeverCoGuiKemDuLieu(String url, Context context, XuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu xuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu){
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST,
//                url,
//                null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            xuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu.jsonArrayNhanDuocTuServer(response);
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        xuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu.chuoiBaoLoiCuaVolley(error.getMessage());
//                    }
//                }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> param = new HashMap<>();
//                return xuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu.guiMapLenSever(param);
//            }
//        };
//        requestQueue.add(jsonArrayRequest);
//    }

    public interface XuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu{
        public Map<String, String> guiMapLenSever(Map<String, String> param);
        public void jsonArrayDangString_NhanDuocTuServer(String response) throws JSONException;
        public void chuoiBaoLoiCuaVolley(String VolleyErrorMessage);
    }

    public static void layJsonArrayDangString_TuSeverCoGuiKemDuLieu(String url, Context context, XuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu xuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonStringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            xuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu.jsonArrayDangString_NhanDuocTuServer(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        xuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu.chuoiBaoLoiCuaVolley(error.getMessage());
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                return xuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu.guiMapLenSever(param);
            }
        };
        requestQueue.add(jsonStringRequest);
    }


    public static void layThuongHieuVaLoaiSanPham(Context context, XuLyDuLieuListener listener) {
        // Tạo một RequestQueue để quản lý các yêu cầu Volley
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // URL của script PHP trên máy chủ
        String url = context.getString(R.string.url_thuong_hieu_va_loai_san_pham);

        // Tạo một yêu cầu JSON Object để gửi yêu cầu GET đến máy chủ
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null){
                            // Xử lý phản hồi JSON từ máy chủ
                            try {
                                JSONArray danhSachThuongHieu = response.getJSONArray("danhSachThuongHieu");
                                JSONArray danhSachLoaiSanPham = response.getJSONArray("danhSachLoaiSanPham");

                                // Gửi dữ liệu đến người nghe
                                listener.duLieuNhanDuoc(danhSachThuongHieu, danhSachLoaiSanPham);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                // Gửi thông báo lỗi đến người nghe

                                listener.onError(e.getMessage());
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi khi gửi yêu cầu đến máy chủ
                        error.printStackTrace();
                        Toast.makeText(context, "Lỗi: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("loi cua toi", "Lỗi: " + error.getMessage());
                        // Gửi thông báo lỗi đến người nghe
                        listener.onError(error.getMessage());
                    }
                }
        );

        // Thêm yêu cầu vào hàng đợi
        requestQueue.add(jsonObjectRequest);
    }

    public static void layFullLoaiSanPham(Context context, XuLyVolleyListennerVaErroLayFullLoaiSanPham listennerVaErroInterface){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //lấy url đã lưu trong thư mục values
        String urlShowFullLoaiSanPham = context.getString(R.string.url_show_full_loai_san_pham);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                urlShowFullLoaiSanPham,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //gửi biến response của class JSONArray qua để khi gọi tự xử lý
                        listennerVaErroInterface.danhSachLoaiSanPhamDocDuoc(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Báo lỗi lên
                        Toast.makeText(context, "Lỗi khi đọc danh sách sản phẩm từ sever: Báo ở hàm static layFullLoaiSanPham trong class MyVolleyRequest: cụ thể lỗi là: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        //Log lên
                        Log.e("loi cua toi", "Lỗi khi đọc danh sách sản phẩm từ sever: Báo ở hàm static layFullLoaiSanPham trong class MyVolleyRequest: cụ thể lỗi là: " + error.getMessage());
                        //Tạo điều kiện để bên kia muốn đọc lỗi
                        listennerVaErroInterface.chuoiBaoLoiCuaVolley(error.getMessage());
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }
}
