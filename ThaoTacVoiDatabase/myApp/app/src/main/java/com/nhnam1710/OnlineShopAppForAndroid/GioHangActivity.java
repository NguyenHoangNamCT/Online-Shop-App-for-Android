package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class GioHangActivity extends AppCompatActivity {

    private ListView listViewGioHang;
    private Button btnThanhToan;
    private TextView tvGioHangRong;
    private ArrayList<SanPhamTrongGio> sanPhamTrongGioArrayList;
    AdapterGioHang adapterGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhXa();

        sanPhamTrongGioArrayList = new ArrayList<>();
        adapterGioHang = new AdapterGioHang(this, R.layout.dong_gio_hang, sanPhamTrongGioArrayList);
        listViewGioHang.setAdapter(adapterGioHang);

        loadGiaHang();
//        themDuLieuAo();
        adapterGioHang.notifyDataSetChanged();

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(GioHangActivity.this, CheckOutActivity.class);
                intent1.putExtra("danhSachSP", sanPhamTrongGioArrayList);
                startActivity(intent1);
            }
        });
    }

    public void anhXa(){
        listViewGioHang = findViewById(R.id.listViewGioHang_activity_gio_hang);
        btnThanhToan = findViewById(R.id.btnThanhToan_activity_gio_hang);
        tvGioHangRong = findViewById(R.id.tvGioHangRong_activity_gio_hang);
    }

//    phương thức này để thông báo và log lỗi từ các trường hợp báo lỗi từ sever
    public void thongBaoLoi(String chuoiTuSever) {
        // Lấy giá trị username và password
        String username = GlobalClass.getUserName();
        String password = GlobalClass.getPassword();

        // Kiểm tra nội dung chuỗi lỗi từ server và xử lý tương ứng
        if (chuoiTuSever.contains("Du_lieu_gui_len_sever_co_van_de")) {
            // Thông báo lỗi khi dữ liệu gửi lên server có vấn đề
            Toast.makeText(GioHangActivity.this, "Dữ liệu gửi lên server có vấn đề. Server báo là: " + chuoiTuSever, Toast.LENGTH_SHORT).show();
            Log.d("loi cua toi", "Dữ liệu gửi lên server có vấn đề. Server báo là: " + chuoiTuSever + " | Username: " + username + " | Password: " + password);
        } else if (chuoiTuSever.contains("Nguoi_dung_khong_hop_le")) {
            // Thông báo lỗi khi người dùng không hợp lệ
            Toast.makeText(GioHangActivity.this, "Người dùng không hợp lệ. Server báo là: " + chuoiTuSever, Toast.LENGTH_SHORT).show();
            Log.d("loi cua toi", "Người dùng không hợp lệ. Server báo là: " + chuoiTuSever + " | Username: " + username + " | Password: " + password);
        } else if (chuoiTuSever.contains("Some_other_error")) {
            // Xử lý lỗi khác (nếu có)
            Toast.makeText(GioHangActivity.this, "Lỗi không xác định. Server báo là: " + chuoiTuSever, Toast.LENGTH_SHORT).show();
            Log.d("loi cua toi", "Lỗi không xác định từ server. Server báo là: " + chuoiTuSever + " | Username: " + username + " | Password: " + password);
        } else {
            // Xử lý lỗi mặc định nếu không khớp với các trường hợp trên
            Toast.makeText(GioHangActivity.this, "Có lỗi xảy ra: " + chuoiTuSever, Toast.LENGTH_SHORT).show();
            Log.d("loi cua toi", "Lỗi từ server: " + chuoiTuSever + " | Username: " + username + " | Password: " + password);
        }
    }

    // phương thức này dùng để kiểm tra xem server có báo lỗi hay không (lỗi do server cố tình in ra) true false là có hoặc không tương ứng. Có cả thông báo và log lỗi nữa
    public boolean kiemTraSeverCoBaoLoiKhong(JSONArray response){
        // Kiểm tra xem phản hồi có phải là một thông điệp lỗi không
            try {
                JSONObject jsonObject = response.getJSONObject(0); // Lấy đối tượng JSON đầu tiên
                if (jsonObject.has("error")) { // Kiểm tra nếu có trường "error"
                    String errorMessage = jsonObject.getString("error"); // Lấy thông điệp lỗi
                    thongBaoLoi(errorMessage);// báo và log lỗi ra
                    return true; // Dừng lại nếu có lỗi
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        return false;
    }

    public void xuLyNeuGioHangRong(JSONArray response){
        if(response.length() == 0){
            tvGioHangRong.setText("Hiện giỏ hàng của bạn không có sản phẩm nào!");
            tvGioHangRong.setVisibility(View.VISIBLE);
        }
    }

//    public void loadGiaHang(){
//        String url = getString(R.string.url_show_full_gio_hang);
//        MyVolleyRequest.layJsonArrayTuSeverCoGuiKemDuLieu(url, GioHangActivity.this, new MyVolleyRequest.XuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu() {
//            @Override
//            public Map<String, String> guiMapLenSever(Map<String, String> param) {
//                Log.d("Params", "Sending params: userName=" + GlobalClass.getUserName() + ", password=" + GlobalClass.getPassword());
//                //mục đích: gửi tên đăng nhập và mật khẩu lên cho server thay thế id người dùng để load giỏ hàng về (mục đích đảm bảo người dùng chính là chủ nick)
//                param.put("userName", GlobalClass.getUserName());
//                param.put("password", GlobalClass.getPassword());
//                return param;
//            }
//
//            @Override
//            public void jsonArrayNhanDuocTuServer(JSONArray response){
//                // Log giá trị của response để kiểm tra
//                Log.d("loi cua toi", "Giá trị response: " + response.toString());
//
//                // Thông báo cho người dùng về giá trị của response
//                Toast.makeText(GioHangActivity.this, "Giá trị response: " + response.toString(), Toast.LENGTH_LONG).show();
//
////                xuLyNeuGioHangRong(response);
//
//                if(!kiemTraSeverCoBaoLoiKhong(response)){
//                    for (int i = 0; i < response.length(); i++){
//                        try {
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            SanPhamTrongGio sanPhamTrongGio = new SanPhamTrongGio(jsonObject);
//                            Log.d("loi cua toi", "\nSản phẩm trong giỏ thứ: " + i + sanPhamTrongGio.toString()+ "\n") ;
//                            sanPhamTrongGioArrayList.add(sanPhamTrongGio);
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                    adapterGioHang.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void chuoiBaoLoiCuaVolley(String VolleyErrorMessage) {
//                Toast.makeText(GioHangActivity.this, "Load giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
//                Log.e("loi cua toi", "Load giỏ hàng thất bại: Lỗi volley: " + VolleyErrorMessage);
//            }
//        });
//    }



    //viết lại vì jsonarray request không được tương thích với getParams eên không gửi dữ liệu được
    public void loadGiaHang(){
        String url = getString(R.string.url_show_full_gio_hang);
        MyVolleyRequest.layJsonArrayDangString_TuSeverCoGuiKemDuLieu(url, GioHangActivity.this, new MyVolleyRequest.XuLyDuLieuNhanDuocTuServerCoGuiKemDuLieu() {
            @Override
            public Map<String, String> guiMapLenSever(Map<String, String> param) {
                Log.d("Params", "Sending params: userName=" + GlobalClass.getUserName() + ", password=" + GlobalClass.getPassword());
                //mục đích: gửi tên đăng nhập và mật khẩu lên cho server thay thế id người dùng để load giỏ hàng về (mục đích đảm bảo người dùng chính là chủ nick)
                param.put("userName", GlobalClass.getUserName());
                param.put("password", GlobalClass.getPassword());
                return param;
            }


            @Override
            public void jsonArrayDangString_NhanDuocTuServer(String JsonArrayDangString){
                try {
                    JSONArray response = new JSONArray(JsonArrayDangString);
                    if(!kiemTraSeverCoBaoLoiKhong(response)){
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                SanPhamTrongGio sanPhamTrongGio = new SanPhamTrongGio(jsonObject);
                                Log.d("loi cua toi", "\nSản phẩm trong giỏ thứ: " + i + sanPhamTrongGio.toString()+ "\n") ;
                                sanPhamTrongGioArrayList.add(sanPhamTrongGio);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        adapterGioHang.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
//                xuLyNeuGioHangRong(response);

            }

            @Override
            public void chuoiBaoLoiCuaVolley(String VolleyErrorMessage) {
                Toast.makeText(GioHangActivity.this, "Load giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                Log.e("loi cua toi", "Load giỏ hàng thất bại: Lỗi volley: " + VolleyErrorMessage);
            }
        });
    }

//    public static SanPhamTrongGio RandomSanPhamTrongGio(){
//        Random random = new Random();
//        String[] TEN_SAN_PHAM = {"Áo thun", "Quần jean", "Áo khoác", "Giày sneaker", "Túi xách"};
//        int[] GIA = {100000, 200000, 150000, 300000, 250000};
//        int[] SO_LUONG = {1, 2, 3, 4, 5};
//
//        int randomId = random.nextInt(1000); // Id ngẫu nhiên
//        String randomTenSanPham = TEN_SAN_PHAM[random.nextInt(TEN_SAN_PHAM.length)];
//        int randomGia = GIA[random.nextInt(GIA.length)];
//        int randomSoLuong = SO_LUONG[random.nextInt(SO_LUONG.length)];
//        boolean randomChonMua = false;  // Chọn mua ngẫu nhiên
//
//        return (new SanPhamTrongGio(randomId, randomTenSanPham, randomGia, randomSoLuong, "randomHinhAnh", randomChonMua));
//    }
//
//    public void themDuLieuAo(){
//        for(int i = 0; i < 20; i++){
//            sanPhamTrongGioArrayList.add(GioHangActivity.RandomSanPhamTrongGio());
//        }
//        adapterGioHang.notifyDataSetChanged();
//        Log.d("GioHangActivity", "Số sản phẩm trong giỏ: " + sanPhamTrongGioArrayList.size()); // Log số lượng sản phẩm
//    }


}