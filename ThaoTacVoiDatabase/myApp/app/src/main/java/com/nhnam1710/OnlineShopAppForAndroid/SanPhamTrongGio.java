package com.nhnam1710.OnlineShopAppForAndroid;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class SanPhamTrongGio implements Serializable {
    private int id;
    private String tenSanPham;
    private int gia;
    private int soLuong;
    private String hinhAnh;
    private boolean chonMua;
    private int soLuongSanPhamCuaShop;
    private float giamGia;


    public SanPhamTrongGio(int id, String tenSanPham, int gia, int soLuong, String hinhAnh, boolean chonMua, int soLuongSanPhamCuaShop) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.chonMua = chonMua;
        this.soLuongSanPhamCuaShop = soLuongSanPhamCuaShop;
    }

    // Constructor nhận JSONObject
    public SanPhamTrongGio(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getInt("id");
            this.tenSanPham = jsonObject.getString("ten_san_pham");
            this.gia = Integer.parseInt(jsonObject.getString("gia_tien")); // Chuyển từ chuỗi sang số nguyên
            this.soLuong = jsonObject.getInt("so_luong");
            this.hinhAnh = jsonObject.getString("hinh_anh");
            this.soLuongSanPhamCuaShop = jsonObject.getInt("so_luong_san_pham_cua_shop");
            this.giamGia = (float) jsonObject.getDouble("giam_gia");
            this.chonMua = false;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isChonMua() {
        return chonMua;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
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

    public int getSoLuongSanPhamCuaShop() {
        return soLuongSanPhamCuaShop;
    }

    public void setSoLuongSanPhamCuaShop(int soLuongSanPhamCuaShop) {
        this.soLuongSanPhamCuaShop = soLuongSanPhamCuaShop;
    }

    public boolean getChonMua() {
        return chonMua;
    }

    public void setChonMua(boolean chonMua) {
        this.chonMua = chonMua;
    }

    @Override
    public String toString() {
        return "SanPhamTrongGio{" +
                "id=" + id +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", gia=" + gia +
                ", soLuong=" + soLuong +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", chonMua=" + chonMua +
                '}';
    }
}
