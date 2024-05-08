package com.nhnam1710.OnlineShopAppForAndroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterQuanLySanPham extends BaseAdapter {

    QuanLySanPham_Activity context;
    int layout;
    List<SanPham> sanPhamList;

    public AdapterQuanLySanPham(QuanLySanPham_Activity context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.layout = layout;
        this.sanPhamList = sanPhamList;
    }

    @Override
    public int getCount() {
        return sanPhamList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgAnhSP;
        TextView txtTenSP, txtGia, txtSoLuong;
        ImageButton imageButtonEdit, imageButtonRemove;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder = new ViewHolder();
            // Ánh xạ các view từ layout XML vào ViewHolder
            viewHolder.imgAnhSP = convertView.findViewById(R.id.imageViewSanPham_anh_dong_quan_ly_san_pham);
            viewHolder.txtTenSP = convertView.findViewById(R.id.textViewSanPham_ten_dong_quan_ly_san_pham);
            viewHolder.txtGia = convertView.findViewById(R.id.textViewSanPham_gia_dong_quan_ly_san_pham);
            viewHolder.txtSoLuong = convertView.findViewById(R.id.textViewSanPham_mo_ta_dong_quan_ly_san_pham);
            viewHolder.imageButtonEdit = convertView.findViewById(R.id.imageButtonSanPham_sua_dong_quan_ly_san_pham);
            viewHolder.imageButtonRemove = convertView.findViewById(R.id.imageButtonSanPham_xoa_dong_quan_ly_san_pham);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lấy đối tượng sản phẩm tương ứng với vị trí
        SanPham sanPham = sanPhamList.get(position);
        viewHolder.imageButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanXoa(sanPham.getId(), sanPham.getTenSanPham());
            }
        });



        // Đặt dữ liệu cho các view trong ViewHolder từ đối tượng sản phẩm
        Picasso.get().load(context.getResources().getString(R.string.url_img_on_sever) + sanPham.getHinhAnh()).into(viewHolder.imgAnhSP);
        viewHolder.txtTenSP.setText(sanPham.getTenSanPham());
        viewHolder.txtGia.setText(String.valueOf(sanPham.getGiaTien()));
        viewHolder.txtSoLuong.setText(String.valueOf(sanPham.getSoLuong()));

        return convertView;
    }

    public void xacNhanXoa(int idSP, String ten){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setMessage("Bán có muốn xoá sản phẩm " + ten + " không?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String urlXoaCuaSever = context.getResources().getString(R.string.url_xoa_san_pham);
                context.xoaSanPham(urlXoaCuaSever, idSP);
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
