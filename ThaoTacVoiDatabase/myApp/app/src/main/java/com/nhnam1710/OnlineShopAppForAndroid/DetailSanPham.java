package com.nhnam1710.OnlineShopAppForAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Map;

public class DetailSanPham extends AppCompatActivity {

    private ImageView anhSanPham;
    private TextView tenSanPham, giaSanPham, luotBan, moTaSanPham, tieuDeDanhGia, danhGiaText, soLuongSanPham, giamGiaTextView, giaBanTextView;
    private RatingBar danhGiaSao;
    private ImageView anhNguoiDung, anhSanPham1, anhSanPham2, anhSanPham3, anhSanPham4, anhSanPham5;
    private TextView tenNguoiDung;
    private RatingBar soSao;
    Button btnTrangChu, btnThemVaoGio, btnMuaHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_san_pham);
        anhXa();

        SanPham sp = nhanSanPhamTuMainActivity();
        ganGiaTriTuSanPhamVaoGiaoDienDetail(sp);

        //-------------------- danh sách các sự kiện --------------------
        suKienThemSanPhamVaoGioHang(sp);
    }

    private void anhXa() {
        // Ánh xạ các thành phần từ tệp XML
        anhSanPham = findViewById(R.id.anhSanPham_activity_detail_san_pham);
        tenSanPham = findViewById(R.id.tenSanPham_activity_detail_san_pham);
        giaSanPham = findViewById(R.id.giaSanPham_activity_detail_san_pham);
        luotBan = findViewById(R.id.luotBan_activity_detail_san_pham);
        moTaSanPham = findViewById(R.id.moTaSanPham_activity_detail_san_pham);
        tieuDeDanhGia = findViewById(R.id.tieuDeDanhGia_activity_detail_san_pham);
        danhGiaSao = findViewById(R.id.danhGiaSao_activity_detail_san_pham);
        danhGiaText = findViewById(R.id.danhGiaText_activity_detail_san_pham);
        anhNguoiDung = findViewById(R.id.anhNguoiDung_activity_detail_san_pham);
        anhSanPham1 = findViewById(R.id.anhSanPham1_activity_detail_san_pham);
        anhSanPham2 = findViewById(R.id.anhSanPham2_activity_detail_san_pham);
        anhSanPham3 = findViewById(R.id.anhSanPham3_activity_detail_san_pham);
        anhSanPham4 = findViewById(R.id.anhSanPham4_activity_detail_san_pham);
        anhSanPham5 = findViewById(R.id.anhSanPham5_activity_detail_san_pham);
        tenNguoiDung = findViewById(R.id.tenNguoiDung_activity_detail_san_pham);
        soSao = findViewById(R.id.soSao_activity_detail_san_pham);
        soLuongSanPham = findViewById(R.id.soLuongSanPham_activity_detail_san_pham);
        giamGiaTextView = findViewById(R.id.giamGia_activity_detail_san_pham);
        giaBanTextView = findViewById(R.id.giaBan_activity_detail_san_pham);
        btnTrangChu = findViewById(R.id.btn_home_activity_detail_san_pham);
        btnThemVaoGio = findViewById(R.id.btn_cart_activity_detail_san_pham);
        btnMuaHang = findViewById(R.id.btn_buy_activity_detail_san_pham);

    }

    public SanPham nhanSanPhamTuMainActivity(){
        Intent intent = getIntent();
        return (SanPham) intent.getSerializableExtra("sanPham");
    }

    public void ganGiaTriTuSanPhamVaoGiaoDienDetail(SanPham sp){
        // Gán các giá trị của đối tượng SanPham vào các view tương ứng
        tenSanPham.setText(sp.getTenSanPham());

        double giaGoc = sp.getGiaTien();
        int giaBan = (int) (giaGoc * (1 - sp.getGiamGia()/100));
        giaSanPham.setText(formatVND(giaGoc));
        //gạch ngang giá gốc
        giaSanPham.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        giamGiaTextView.setText("Giảm: " + sp.getGiamGia() + "% ");
        giaBanTextView.setText(formatVND(giaBan));
        moTaSanPham.setText("Mô tả: " + sp.getMoTa());
        luotBan.setText("Lượt bán: " + sp.getLuotMua());
        soLuongSanPham.setText("Kho còn: " + sp.getSoLuong() + " " + sp.getDonViTinh());

        String urlImageOnSever = getResources().getString(R.string.url_img_on_sever) + sp.getHinhAnh();
        // Picasso  để tải hình ảnh từ URL hoặc đường dẫn vào ImageView
        Picasso.get().load(urlImageOnSever).into(anhSanPham);
    }

    public String formatVND(double num){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');

        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        return formatter.format(num) + "đ"; // Đơn vị tiền tệ VND
    }

    public void themSanPhamVaoGio(SanPham sanPhamCanThemVaoGio){
        String url = getString(R.string.url_them_san_pham_vao_gio_hang);
        MyVolleyStringRequest.GuiStringRequestDenSever(url,
                DetailSanPham.this,
                new MyVolleyStringRequest.thaoTacVoiStringRequestNay() {
                    @Override
                    public Map<String, String> guiMapLenSever(Map<String, String> param) {
                        param.put("userName", GlobalClass.getUserName());
                        param.put("password", GlobalClass.getPassword());
                        param.put("idSanPham", sanPhamCanThemVaoGio.getId() + "");
                        return param;
                    }

                    @Override
                    public void xuLyChuoiDocDuocTuSever(String response) {
                        if (response != null && !response.isEmpty()) {
                            // Kiểm tra các thông báo thành công từ server
                            if (response.contains("them_san_pham_vao_gio_hang_thanh_cong!")) {
                                Toast.makeText(DetailSanPham.this, "Thêm sản phẩm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                            } else if (response.contains("serser_khong_nhan_duoc_user_name!")) {
                                String tag = "loi cua toi";
                                Log.e(tag, "Server không nhận được user name!");
                                Toast.makeText(DetailSanPham.this, tag + ": Server không nhận được user name!", Toast.LENGTH_SHORT).show();
                            } else if (response.contains("serser_khong_nhan_duoc_mat_khau!")) {
                                String tag = "loi cua toi";
                                Log.e(tag, "Server không nhận được mật khẩu!");
                                Toast.makeText(DetailSanPham.this, tag + ": Server không nhận được mật khẩu!", Toast.LENGTH_SHORT).show();
                            } else if (response.contains("serser_khong_nhan_duoc_id_san_pham!")) {
                                String tag = "loi cua toi";
                                Log.e(tag, "Server không nhận được ID sản phẩm!");
                                Toast.makeText(DetailSanPham.this, tag + ": Server không nhận được ID sản phẩm!", Toast.LENGTH_SHORT).show();
                            } else if (response.contains("them_san_pham_vao_gio_hang_that_bai!")) {
                                String tag = "loi cua toi";
                                Log.e(tag, "Thêm sản phẩm vào giỏ hàng thất bại!");
                                Toast.makeText(DetailSanPham.this, tag + ": Thêm sản phẩm vào giỏ hàng thất bại!", Toast.LENGTH_SHORT).show();
                            } else {
                                String tag = "thong_bao";
                                Log.i(tag, "Phản hồi từ server: " + response);
                                Toast.makeText(DetailSanPham.this, tag + ": Phản hồi từ server: " + response, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            String tag = "loi cua toi";
                            Log.e(tag, "Không có phản hồi từ server!");
                            Toast.makeText(DetailSanPham.this, tag + ": Không có phản hồi từ server!", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void baoLoiCuaOnErrorResponse(VolleyError error) {

                    }
                });
    }

    //-------------------- Danh sách các sự kiện --------------------
    public void suKienThemSanPhamVaoGioHang(SanPham sanPhamCanThemVaoGio){
        btnThemVaoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themSanPhamVaoGio(sanPhamCanThemVaoGio);
            }
        });
    }
}