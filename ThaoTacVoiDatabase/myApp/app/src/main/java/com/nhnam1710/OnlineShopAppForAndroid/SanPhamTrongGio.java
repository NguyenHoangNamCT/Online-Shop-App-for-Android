package com.nhnam1710.OnlineShopAppForAndroid;

import java.io.Serializable;

public class SanPhamTrongGio implements Serializable {
    private int id;
    private String tenSanPham;
    private int gia;
    private int soLuong;
    private int hinhAnh;
    private boolean chonMua;

    public SanPhamTrongGio(int id, String tenSanPham, int gia, int soLuong, int hinhAnh, boolean chonMua) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.chonMua = chonMua;
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

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public boolean isChonMua() {
        return chonMua;
    }

    public void setChonMua(boolean chonMua) {
        this.chonMua = chonMua;
    }
}
