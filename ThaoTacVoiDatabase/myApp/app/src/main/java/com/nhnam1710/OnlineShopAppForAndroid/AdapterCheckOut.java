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

public class AdapterCheckOut extends BaseAdapter {

    Context context;
    int layout;
    List<SanPhamTrongGio> sanPhamTrongGioList;

    public AdapterCheckOut(Context context, int layout, List<SanPhamTrongGio> sanPhamTrongGioList) {
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

    private class ViewHolder {
        ImageView imageViewAnhSanPham;
        TextView textViewTenSanPham;
        TextView textViewGiaSanPham;
        TextView textViewSoLuongSanPham;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.imageViewAnhSanPham = convertView.findViewById(R.id.imageView_anhsanpham_dong_check_out);
            holder.textViewTenSanPham = convertView.findViewById(R.id.textView_textviewSanPham_dong_check_out);
            holder.textViewGiaSanPham = convertView.findViewById(R.id.textView_giasanpham_dong_check_out);
            holder.textViewSoLuongSanPham = convertView.findViewById(R.id.textView_soluongsanpham_dong_check_out);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        SanPhamTrongGio sanPham = sanPhamTrongGioList.get(position);
        holder.textViewTenSanPham.setText(sanPham.getTenSanPham());
        holder.textViewGiaSanPham.setText(String.valueOf(sanPham.getGia()));
        holder.textViewSoLuongSanPham.setText(String.valueOf(sanPham.getSoLuong()));
        String urlImageOnServer = context.getString(R.string.url_img_on_sever) + sanPham.getHinhAnh();
        Picasso.get()
                .load(urlImageOnServer)                     // Tải hình ảnh từ URL
                .error(R.drawable.ic_launcher_foreground)        // Hình ảnh mặc định nếu lỗi
                .into(holder.imageViewAnhSanPham);          // Hiển thị vào imageView

        return convertView;
    }

}
