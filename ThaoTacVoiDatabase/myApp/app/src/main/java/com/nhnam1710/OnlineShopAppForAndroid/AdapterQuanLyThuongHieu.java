package com.nhnam1710.OnlineShopAppForAndroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

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


        return convertView;
    }
}
