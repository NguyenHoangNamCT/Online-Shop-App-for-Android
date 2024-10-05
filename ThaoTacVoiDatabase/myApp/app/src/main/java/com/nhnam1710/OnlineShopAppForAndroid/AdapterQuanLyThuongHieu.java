package com.nhnam1710.OnlineShopAppForAndroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class AdapterQuanLyThuongHieu extends BaseAdapter {
    ActivityQuanLyThuongHieu contex;
    List<ThuongHieu> thuongHieuList;
    int layout;

    public AdapterQuanLyThuongHieu(ActivityQuanLyThuongHieu contex, List<ThuongHieu> thuongHieuList, int layout) {
        this.contex = contex;
        this.thuongHieuList = thuongHieuList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return thuongHieuList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ThuongHieuViewHolder {
        ImageView imageViewLogoThuongHieu;
        TextView textViewTenThuongHieu;
        TextView textViewMoTaThuongHieu;
        TextView textViewTrangWebThuongHieu;
        ImageButton imageButtonSuaThuongHieu;
        ImageButton imageButtonXoaThuongHieu;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ThuongHieuViewHolder viewHolder;

        // Kiểm tra xem convertView có tồn tại không
        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) contex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);

            // Khởi tạo ViewHolder và ánh xạ các view
            viewHolder = new ThuongHieuViewHolder();
            viewHolder.imageViewLogoThuongHieu = convertView.findViewById(R.id.imageViewLogoThuongHieu_dong_thuong_hieu);
            viewHolder.textViewTenThuongHieu = convertView.findViewById(R.id.textViewTenThuongHieu_dong_thuong_hieu);
            viewHolder.textViewMoTaThuongHieu = convertView.findViewById(R.id.textViewMoTaThuongHieu_dong_thuong_hieu);
            viewHolder.textViewTrangWebThuongHieu = convertView.findViewById(R.id.textViewTrangWebThuongHieu_dong_thuong_hieu);
            viewHolder.imageButtonSuaThuongHieu = convertView.findViewById(R.id.imageButtonThuongHieu_sua_dong_thuong_hieu);
            viewHolder.imageButtonXoaThuongHieu = convertView.findViewById(R.id.imageButtonThuongHieu_xoa_dong_thuong_hieu);

            // Đặt tag cho convertView để sử dụng lại ViewHolder
            convertView.setTag(viewHolder);
        } else {
            // Nếu convertView đã tồn tại, lấy ViewHolder từ tag
            viewHolder = (ThuongHieuViewHolder) convertView.getTag();
        }

        // Lấy thương hiệu tại vị trí hiện tại
        ThuongHieu thuongHieu = thuongHieuList.get(position);

        // Gán dữ liệu vào ViewHolder
        viewHolder.textViewTenThuongHieu.setText(thuongHieu.getTenThuongHieu());
        viewHolder.textViewMoTaThuongHieu.setText(thuongHieu.getMoTa());
        viewHolder.textViewTrangWebThuongHieu.setText("Website: " + thuongHieu.getTrangWeb());


        String urlHinhAnhTrenSever = contex.getString(R.string.url_img_on_sever).trim() + thuongHieu.getLogo().trim();
        Picasso.get().load(urlHinhAnhTrenSever).error(R.drawable.ic_launcher_foreground).into(viewHolder.imageViewLogoThuongHieu);

        viewHolder.imageButtonSuaThuongHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contex, ActivitySuaThuongHieu.class);
                intent.putExtra("thuongHieu", thuongHieu);
                contex.startActivityForResult(intent, contex.getREQUEST_CODE_SUA_THUONG_HIEU());
            }
        });

        viewHolder.imageButtonXoaThuongHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanXoaThuongHieu(thuongHieu.getId(), thuongHieu.getTenThuongHieu());
            }
        });


        return convertView;
    }

    public void xacNhanXoaThuongHieu(int idThuongHieu, String tenThuongHieu) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(contex);

        alertDialog.setMessage("Bạn có muốn xoá thương hiệu " + tenThuongHieu + " không?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String urlXoaThuongHieu = contex.getResources().getString(R.string.url_xoa_thuong_hieu);
                MyVolleyStringRequest.GuiStringRequestDenSever(urlXoaThuongHieu, contex, new MyVolleyStringRequest.thaoTacVoiStringRequestNay() {
                    @Override
                    public Map<String, String> guiMapLenSever(Map<String, String> param) {
                        param.put("idThuongHieuCanXoa", idThuongHieu + "");
                        return param;
                    }

                    @Override
                    public void xuLyChuoiDocDuocTuSever(String response) {
                        Log.d("testcode", response);
                        if (response.equals("success")) {
                            Toast.makeText(contex, "Xóa thương hiệu thành công", Toast.LENGTH_SHORT).show();
                            contex.recreate();
                        } else {
                            Toast.makeText(contex, "Xóa thất bại, lỗi ở sever", Toast.LENGTH_SHORT).show();
                            Log.e("loi cua toi", "xóa thương hiệu fail: " + response);
                        }
                    }

                    @Override
                    public void baoLoiCuaOnErrorResponse(VolleyError error) {
                        // Xử lý lỗi khi gửi request
                    }
                });
            }
        });

        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

}
