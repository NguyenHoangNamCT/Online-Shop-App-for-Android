package com.nhnam1710.OnlineShopAppForAndroid;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyVolleyRequest {

    private static final String TAG = MyVolleyRequest.class.getSimpleName();

    public interface XuLyDuLieuListener {
        void duLieuNhanDuoc(JSONArray danhSachThuongHieu, JSONArray danhSachLoaiSanPham);
        void onError(String errorMessage);
    }

    public static void layThuongHieuVaLoaiSanPham(Context context, XuLyDuLieuListener listener) {
        // Tạo một RequestQueue để quản lý các yêu cầu Volley
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // URL của script PHP trên máy chủ
        String url = "http://your_server_url/your_script.php";

        // Tạo một yêu cầu JSON Object để gửi yêu cầu GET đến máy chủ
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi khi gửi yêu cầu đến máy chủ
                        error.printStackTrace();
                        // Gửi thông báo lỗi đến người nghe
                        listener.onError(error.getMessage());
                    }
                }
        );

        // Thêm yêu cầu vào hàng đợi
        requestQueue.add(jsonObjectRequest);
    }
}
