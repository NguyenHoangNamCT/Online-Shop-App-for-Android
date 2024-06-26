package com.nhnam1710.OnlineShopAppForAndroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSanPham extends BaseAdapter {

    public Context context;
    public int layout;
    public List<SanPham> listSanPham;

    public AdapterSanPham(Context context, int layout, List<SanPham> listSanPham) {
        this.context = context;
        this.layout = layout;
        this.listSanPham = listSanPham;
    }

    @Override
    public int getCount() {
        return listSanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        public ImageView imgSP;
        public TextView txtTenSP, txtGiamGia, txtGia, txtLuotBan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.imgSP = convertView.findViewById(R.id.imageViewHinhAnh_dong_san_pham);
            holder.txtTenSP = convertView.findViewById(R.id.textViewTenSanPham_dong_san_pham);
            holder.txtGiamGia = convertView.findViewById(R.id.textViewGia_dong_san_pham);
            holder.txtGia = convertView.findViewById(R.id.textViewGia_dong_san_pham);
            holder.txtLuotBan = convertView.findViewById(R.id.textViewLuotBan_dong_san_pham);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SanPham sanPham = listSanPham.get(position);

        // dùng thư viện picasso để hiển thị hình ảnh từ sever
        String imageName = sanPham.getHinhAnh();
        String urlImg = context.getResources().getString(R.string.url_img_on_sever)+imageName;
        Picasso.get().load(urlImg).into(holder.imgSP);
        holder.txtTenSP.setText(sanPham.getTenSanPham());
        holder.txtGiamGia.setText(String.valueOf(sanPham.getGiamGia()));
        holder.txtGia.setText(String.valueOf(sanPham.getGiaTien()));
        holder.txtLuotBan.setText(String.valueOf(sanPham.getLuotMua()));

        return convertView;
    }
}
