package com.nhnam1710.OnlineShopAppForAndroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterGioHang extends BaseAdapter {
    Context context;
    int layout;
    List<SanPhamTrongGio> sanPhamTrongGioList;


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
        ViewHolder holder = null;
        if(convertView == null){
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
        return convertView;
    }
}
