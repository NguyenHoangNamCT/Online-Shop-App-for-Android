package com.nhnam1710.OnlineShopAppForAndroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

// Lớp UpHinhAnh giúp tải hình ảnh lên máy chủ bằng cách sử dụng thư viện Volley của Android
public class UpHinhAnh {

    // TAG sử dụng để đánh dấu lớp cho việc ghi log
    private static final String TAG = UpHinhAnh.class.getSimpleName();

    // Phương thức để tải hình ảnh lên máy chủ
    public static void uploadImage(Context context, String imageUrl, final ImageUploadListener listener) {
        // Tạo một hàng đợi yêu cầu Volley
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Tạo một ImageRequest để gửi yêu cầu tải hình ảnh
        ImageRequest imageRequest = new ImageRequest(
                imageUrl, // URL của hình ảnh cần tải
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        // Gọi phương thức callback khi tải hình ảnh thành công
                        listener.onImageUploadSuccess(response);
                    }
                },
                0, // Chiều rộng của hình ảnh (0 để tự động điều chỉnh)
                0, // Chiều cao của hình ảnh (0 để tự động điều chỉnh)
                Bitmap.Config.RGB_565, // Định dạng của hình ảnh
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Ghi log lỗi nếu có
                        Log.e("loi cua toi", "Lỗi ở phương thức uploadImage trong class " + TAG + ": " +error.getMessage());
                        // Gọi phương thức callback khi xảy ra lỗi
                        listener.onImageUploadError(error.getMessage());
                    }
                }
        );

        // Thêm yêu cầu vào hàng đợi
        requestQueue.add(imageRequest);
    }

    // Interface để lắng nghe sự kiện tải hình ảnh lên máy chủ
    public interface ImageUploadListener {
        // Phương thức callback khi tải hình ảnh thành công
        void onImageUploadSuccess(Bitmap imageBitmap);
        // Phương thức callback khi xảy ra lỗi trong quá trình tải hình ảnh
        void onImageUploadError(String errorMessage);
    }
}
