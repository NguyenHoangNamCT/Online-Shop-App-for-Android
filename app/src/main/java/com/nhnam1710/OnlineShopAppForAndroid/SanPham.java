package com.nhnam1710.OnlineShopAppForAndroid;

public class SanPham {
    private String  tenSP;
    private double giamGia;
    private int gia, luotBan, hinhAnh;

    public SanPham(int hinhAnh, String tenSP, double giamGia, int gia, int luotBan) {
        this.hinhAnh = hinhAnh;
        this.tenSP = tenSP;
        this.giamGia = giamGia;
        this.gia = gia;
        this.luotBan = luotBan;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getLuotBan() {
        return luotBan;
    }

    public void setLuotBan(int luotBan) {
        this.luotBan = luotBan;
    }
}
