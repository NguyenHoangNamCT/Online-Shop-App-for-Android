package com.nhnam1710.OnlineShopAppForAndroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.text.NumberFormat;

public class AdapterGioHang extends BaseAdapter {
    GioHangActivity context;
    int layout;
    List<SanPhamTrongGio> sanPhamTrongGioList;

    public AdapterGioHang(GioHangActivity context, int layout, List<SanPhamTrongGio> sanPhamTrongGioList) {
        this.context = context;
        this.layout = layout;
        this.sanPhamTrongGioList = sanPhamTrongGioList;
    }

    @Override
    public int getCount() {
        return sanPhamTrongGioList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        CheckBox checkbox;
        ImageView imageViewSanPham;
        TextView textViewTenSanPham;
        TextView textViewGia;
        EditText editTextSoLuong;
        Button buttonGiamSoLuong;
        Button buttonTangSoLuong;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.checkbox = convertView.findViewById(R.id.checkbox_san_pham_dong_gio_hang);
            holder.imageViewSanPham = convertView.findViewById(R.id.anh_san_pham_dong_gio_hang);
            holder.textViewTenSanPham = convertView.findViewById(R.id.textView_san_pham_dong_gio_hang);
            holder.textViewGia = convertView.findViewById(R.id.gia_san_pham_dong_gio_hang);
            holder.editTextSoLuong = convertView.findViewById(R.id.so_luong_edittext_dong_gio_hang);
            holder.buttonGiamSoLuong = convertView.findViewById(R.id.giam_so_luong_button_dong_gio_hang);
            holder.buttonTangSoLuong = convertView.findViewById(R.id.tang_so_luong_button_dong_gio_hang);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        SanPhamTrongGio sanPham = sanPhamTrongGioList.get(position);

        // Gán các giá trị của SanPhamTrongGio vào holder
        holder.textViewTenSanPham.setText(sanPham.getTenSanPham());
        //tính giá sau khi giảm và format thành dạng vnd để dễ đọc
        int giaSauKhiGiam = (int) (sanPham.getGia() * (1 - sanPham.getGiamGia()/100));
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        holder.textViewGia.setText(String.valueOf(vndFormat.format(giaSauKhiGiam)));
        holder.editTextSoLuong.setText(String.valueOf(sanPham.getSoLuong()));

        // Gán giá trị cho các thành phần khác
        holder.checkbox.setChecked(sanPham.getChonMua());
        String url = context.getString(R.string.url_img_on_sever) + sanPham.getHinhAnh();
        Picasso.get()
                .load(url)
                .error(R.drawable.ic_launcher_foreground) // Hình ảnh sẽ hiển thị nếu có lỗi
                .into(holder.imageViewSanPham);

        //-------------------------------- Danh sách các sự kiện --------------------------------

//        holder.editTextSoLuong.addTextChangedListener(new TextWatcher() {
////            giải thích tham số
////            CharSequence s: là chuổi đang được hiển thị trên edittext
////            int start: vị trí bắt đầu của băn bản thay đổi (nếu thêm thì tính là vị trí đầu tiên của ký tự được thêm đầu tiên, nếu xóa thì là vị trí của ký tự cuối cùng được xóa)
////            int count: là số ký tự được thêm vào hoặc xóa đi, dương nếu thêm, âm nếu xóa
////            int after: là số ký tự được thêm vào (nếu xóa thì nó sẽ là 0)
//
//            private String chuoiBanDau;
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                chuoiBanDau = s.toString();
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // Văn bản đang thay đổi
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(s.toString().equals("") || s.toString().equals(chuoiBanDau))
//                    return;
//                thayDoiSoLuongSanPhamTrongGio(Integer.parseInt(s.toString()), sanPham.getId());
//                context.recreate(); //mục đích: load lại màn hình giỏ hàng đồng bộ các biến và giao diện với database (điều xảy ra: màn hình giỏ hàng hiện tại sẽ bị hủy, và tạo lại màn hình giỏ hàng mới)
//            }
//        });

        holder.editTextSoLuong.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String soLuongMoi = holder.editTextSoLuong.getText().toString();
                if(!soLuongMoi.isEmpty()){
                    int soLuongCu = sanPham.getSoLuong();
                    int soLuongMoi_kieuInt = Integer.parseInt(soLuongMoi);
                    if (!hasFocus && soLuongMoi_kieuInt != soLuongCu && soLuongMoi_kieuInt != 0 && soLuongMoi_kieuInt <= sanPham.getSoLuongSanPhamCuaShop()) {
                        thayDoiSoLuongSanPhamTrongGio(Integer.parseInt(soLuongMoi), sanPham.getId());
                        context.loadGiaHang(); // Load lại màn hình giỏ hàng
                    } else if(!hasFocus && soLuongMoi_kieuInt != soLuongCu && soLuongMoi_kieuInt == 0 && soLuongMoi_kieuInt <= sanPham.getSoLuongSanPhamCuaShop()){
                        showDiaLogXacNhanXoa(sanPham);
                    } else if(!hasFocus && soLuongMoi_kieuInt != soLuongCu && soLuongMoi_kieuInt != 0 && soLuongMoi_kieuInt > sanPham.getSoLuongSanPhamCuaShop()){
                        Toast.makeText(context, "Shop chỉ còn " + sanPham.getSoLuongSanPhamCuaShop() + " sản phẩm, bạn không thể mua số lượng lớn hơn con số này", Toast.LENGTH_SHORT).show();
                        context.loadGiaHang();
                    }
                }
                else
                    context.loadGiaHang();
            }
        });

        holder.buttonGiamSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuongCu = sanPham.getSoLuong(),
                    soLuongMoi = soLuongCu - 1;
                if(soLuongMoi <= 0)
                    showDiaLogXacNhanXoa(sanPham);
                else{
                    thayDoiSoLuongSanPhamTrongGio(soLuongMoi, sanPham.getId());
                    context.loadGiaHang();
                }
            }
        });

        holder.buttonTangSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuongCu = sanPham.getSoLuong(),
                    soLuongMoi = soLuongCu + 1;
                if(soLuongMoi > sanPham.getSoLuongSanPhamCuaShop()){
                    Toast.makeText(context, "Shop chỉ còn " + sanPham.getSoLuongSanPhamCuaShop() + " sản phẩm, bạn không thể mua số lượng lớn hơn con số này", Toast.LENGTH_SHORT).show();
                    context.loadGiaHang();
                }
                else{
                    thayDoiSoLuongSanPhamTrongGio(soLuongMoi, sanPham.getId());
                    context.loadGiaHang();
                }
            }
        });

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.checkbox.isChecked()){
                    sanPham.setChonMua(true);
                }
                else{
                    sanPham.setChonMua(false);
                }
            }
        });

        return convertView;
    }

    // Phương thức để hiển thị dialog xác nhận
    private void showDiaLogXacNhanXoa(final SanPhamTrongGio sanPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này khỏi giỏ hàng?");

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                xoaSanPhamTrongGio(sanPham);
                context.loadGiaHang();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn "Không"
                context.loadGiaHang();
                dialog.dismiss(); // Đóng dialog
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void thayDoiSoLuongSanPhamTrongGio(int soLuongMoi, int idSanPham){
        String url = context.getString(R.string.url_thay_doi_so_luong_san_pham_trong_gio);
        MyVolleyStringRequest.GuiStringRequestDenSever(url, context, new MyVolleyStringRequest.thaoTacVoiStringRequestNay() {
            @Override
            public Map<String, String> guiMapLenSever(Map<String, String> param) {
                param.put("tenDangNhap", GlobalClass.getUserName());
                param.put("matKhau", GlobalClass.getPassword());
                param.put("idSanPham", idSanPham+"");
                param.put("soLuongMoi", soLuongMoi+"");
                return param;
            }

            @Override
            public void xuLyChuoiDocDuocTuSever(String response) {
                if(response.contains("Cap_nhat_thanh_cong")){
                    //xử lý các kiểu
                    String message = "Thành công yeah.";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
                else if(response.contains("Cap_nhat_that_bai")){
                    String message = "Cập nhật thất bại khi thay đổi số lượng sản phẩm trong giỏ hàng.";
                    Log.e("loi cua toi", message);
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
                else if (response.contains("serser_khong_nhan_duoc_user_name")) {
                    String message = "Server không nhận được username khi thay đổi số lượng sản phẩm trong giỏ hàng.";
                    Log.e("loi cua toi", message);
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else if (response.contains("serser_khong_nhan_duoc_mat_khau")) {
                    String message = "Server không nhận được mật khẩu khi thay đổi số lượng sản phẩm trong giỏ hàng.";
                    Log.e("loi cua toi", message);
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else if (response.contains("serser_khong_nhan_duoc_id_san_pham")) {
                    String message = "Server không nhận được ID sản phẩm khi thay đổi số lượng sản phẩm trong giỏ hàng.";
                    Log.e("loi cua toi", message);
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else if (response.contains("serser_khong_nhan_duoc_so_luong_moi")) {
                    String message = "Server không nhận được số lượng mới khi thay đổi số lượng sản phẩm trong giỏ hàng.";
                    Log.e("loi cua toi", message);
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else if (response.contains("Nguoi_dung_khong_ton_tai_ID_tim_duoc_la_null")) {
                    String message = "Người dùng không tồn tại, ID tìm được là null.";
                    Log.e("loi cua toi", message);
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else if (response.contains("Cap_nhat_that_bai")) {
                    String message = "Cập nhật thất bại khi thay đổi số lượng sản phẩm trong giỏ hàng.";
                    Log.e("loi cua toi", message);
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else {
                    // Có thể thêm xử lý cho trường hợp không có lỗi
                    Log.i("thong tin", "Không có lỗi từ server: " + response);
                }

                Log.e("loi cua toi", "Server báo khi cập nhật sản phẩm trong giỏ hàng là:" + response);
            }

            @Override
            public void baoLoiCuaOnErrorResponse(VolleyError error) {

            }
        });
    }

    public void xoaSanPhamTrongGio(SanPhamTrongGio sp){
        String url = context.getString(R.string.url_xoa_san_pham_trong_gio);
        MyVolleyStringRequest.GuiStringRequestDenSever(url, context, new MyVolleyStringRequest.thaoTacVoiStringRequestNay() {
            @Override
            public Map<String, String> guiMapLenSever(Map<String, String> param) {
                param.put("tenDangNhap", GlobalClass.getUserName());
                param.put("matKhau", GlobalClass.getPassword());
                param.put("idSanPham", sp.getId()+"");
                return param;
            }
            @Override
            public void xuLyChuoiDocDuocTuSever(String response) {
                Log.e("loi cua toi", "Server báo khi xóa sản phẩm trong giỏ hàng là: " + response);

                if (response.contains("Xoa_thanh_cong")) {
                    Toast.makeText(context, "Xóa sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                } else if (response.contains("xoa_that_bai")) {
                    Toast.makeText(context, "Xóa sản phẩm thất bại!", Toast.LENGTH_SHORT).show();
                } else if (response.contains("serser_khong_nhan_duoc_user_name!")) {
                    Toast.makeText(context, "Không nhận được tên đăng nhập!", Toast.LENGTH_SHORT).show();
                } else if (response.contains("serser_khong_nhan_duoc_mat_khau!")) {
                    Toast.makeText(context, "Không nhận được mật khẩu!", Toast.LENGTH_SHORT).show();
                } else if (response.contains("serser_khong_nhan_duoc_id_san_pham!")) {
                    Toast.makeText(context, "Không nhận được ID sản phẩm!", Toast.LENGTH_SHORT).show();
                } else if (response.contains("Nguoi_dung_khong_ton_tai_ID_tim_duoc_la_null")) {
                    Toast.makeText(context, "Người dùng không tồn tại!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Có lỗi xảy ra, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void baoLoiCuaOnErrorResponse(VolleyError error) {
                Log.e("loi cua toi", "Volley báo lỗi khi xóa sản phẩm trong giỏ hàng là:" + error.getMessage());
            }
        });
    }
}
