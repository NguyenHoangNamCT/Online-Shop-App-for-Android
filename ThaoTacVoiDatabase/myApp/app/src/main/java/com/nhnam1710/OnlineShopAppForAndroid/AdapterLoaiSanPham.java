package com.nhnam1710.OnlineShopAppForAndroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterLoaiSanPham extends BaseAdapter {
    private ArrayList<LoaiSanPham> loaiSanPhamArrayList;
    private int layout;
    Context context;

    public AdapterLoaiSanPham(ArrayList<LoaiSanPham> loaiSanPhamArrayList, int layout, Context context) {
        this.loaiSanPhamArrayList = loaiSanPhamArrayList;
        this.layout = layout;
        this.context = context;
    }

    // phương thức này rất quan trọng nếu return về 0 sẽ không hiển thị được gì
    @Override
    public int getCount() {
        return loaiSanPhamArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

        class ViewHolder{
            public ImageView imageViewLoaiSanPham;
            public TextView textViewTenLoaiSanPham;
            public TextView textViewMoTaLoaiSanPham;
            public TextView textViewTrangThai;
            public ImageButton imageButtonSua;
            public ImageButton imageButtonXoa;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            // Nếu convertView là null, tức là cần khởi tạo View mới
            if (convertView == null) {

                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(layout, null);

                // Khởi tạo ViewHolder
                holder = new ViewHolder();
                holder.imageViewLoaiSanPham = convertView.findViewById(R.id.imageViewLoaiSanPham_item_loai_san_pham);
                holder.textViewTenLoaiSanPham = convertView.findViewById(R.id.textViewTenLoaiSanPham_item_loai_san_pham);
                holder.textViewMoTaLoaiSanPham = convertView.findViewById(R.id.textViewMoTaLoaiSanPham_item_loai_san_pham);
                holder.textViewTrangThai = convertView.findViewById(R.id.textViewTrangThai_item_loai_san_pham);
                holder.imageButtonSua = convertView.findViewById(R.id.imageButtonSanPham_sua_dong_loai_san_pham);
                holder.imageButtonXoa = convertView.findViewById(R.id.imageButtonSanPham_xoa_dong_loai_san_pham);

                // Gắn ViewHolder vào convertView để có thể tái sử dụng
                convertView.setTag(holder);
            } else {
                // Tái sử dụng ViewHolder cũ
                holder = (ViewHolder) convertView.getTag();
            }

            // Lấy dữ liệu của item tại vị trí position
            LoaiSanPham loaisanPham = loaiSanPhamArrayList.get(position);

            // Gán dữ liệu vào các view
            holder.textViewTenLoaiSanPham.setText(loaisanPham.getTenLoaiSanPham());
            holder.textViewMoTaLoaiSanPham.setText(loaisanPham.getMoTa());
            holder.textViewTrangThai.setText(loaisanPham.isConHang() ? "Còn hàng" : "Hết hàng");

            // Gán hình ảnh từ sever vào
            String urlImage = context.getResources().getString(R.string.url_img_on_sever) + loaisanPham.getHinhAnh();
            Picasso.get().load(urlImage).into(holder.imageViewLoaiSanPham);

            // Thiết lập sự kiện cho các nút Sửa và Xóa
            holder.imageButtonSua.setOnClickListener(v -> {
                Intent intent = new Intent(context, ActivitySuaLoaiSP.class);
                intent.putExtra("idLoaiSP", loaisanPham.getId());
                intent.putExtra("loaiSP", loaisanPham);
                context.startActivity(intent);
            });

            holder.imageButtonXoa.setOnClickListener(v -> {
                // Xử lý logic xóa sản phẩm tại vị trí position
            });

            // Trả về convertView đã được cập nhật
            return convertView;
        }
}
