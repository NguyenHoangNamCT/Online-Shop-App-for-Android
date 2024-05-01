package com.nhnam1710.OnlineShopAppForAndroid;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SanPham {
    private int id;
    private int idLoaiSanPham;
    private int idThuongHieu;
    private String tenSanPham;
    private String moTa;
    private double giaTien;
    private double giamGia;
    private int soLuong;
    private String hinhAnh;
    private int luotXem;
    private int luotMua;

    public SanPham(int id, int idLoaiSanPham, int idThuongHieu, String tenSanPham, String moTa, double giaTien, double giamGia, int soLuong, String hinhAnh, int luotXem, int luotMua) {
        this.id = id;
        this.idLoaiSanPham = idLoaiSanPham;
        this.idThuongHieu = idThuongHieu;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.giaTien = giaTien;
        this.giamGia = giamGia;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.luotXem = luotXem;
        this.luotMua = luotMua;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLoaiSanPham() {
        return idLoaiSanPham;
    }

    public void setIdLoaiSanPham(int idLoaiSanPham) {
        this.idLoaiSanPham = idLoaiSanPham;
    }

    public int getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(int idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getLuotXem() {
        return luotXem;
    }

    public void setLuotXem(int luotXem) {
        this.luotXem = luotXem;
    }

    public int getLuotMua() {
        return luotMua;
    }

    public void setLuotMua(int luotMua) {
        this.luotMua = luotMua;
    }


    //  Các phương thức tĩnh
    public static Uri getDrawableUriByName(String imageName, Context context) {
        return Uri.parse("android.resource://" + context.getPackageName() + "/drawable/" + imageName);
    }

    //Chuyển 1 json oject thành SanPham oject
    public static SanPham fromJsonOject(JSONObject jsonObject) {
        SanPham sp = null;
        try {
            int id = jsonObject.getInt("id");
            int idLoaiSanPham = jsonObject.getInt("id_loai_san_pham");
            int idThuongHieu = jsonObject.getInt("id_thuong_hieu");
            String tenSanPham = jsonObject.getString("ten_san_pham");
            String moTa = jsonObject.getString("mo_ta");
            double giaTien = jsonObject.getDouble("gia_tien");
            double giamGia = jsonObject.getDouble("giam_gia");
            int soLuong = jsonObject.getInt("so_luong");
            String hinhAnh = jsonObject.getString("hinh_anh");
            int luotXem = jsonObject.getInt("luot_xem");
            int luotMua = jsonObject.getInt("luot_mua");
            sp = new SanPham(id, idLoaiSanPham, idThuongHieu, tenSanPham, moTa, giaTien, giamGia, soLuong, hinhAnh, luotXem, luotMua);
        } catch (JSONException e) {
            Log.d("loi cua toi", "Lỗi try catch phương thức tĩnh fromJson trong class SanPham: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return sp;
    }

}
