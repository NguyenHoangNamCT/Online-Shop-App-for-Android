package com.nhnam1710.OnlineShopAppForAndroid;

public class LoaiSanPham {
    private int id;
    private String tenLoaiSanPham;
    private String moTa;
    private boolean trangThai;
    private String hinhAnh;

    // Constructor không đối số
    public LoaiSanPham() {
    }

    // Constructor có đối số
    public LoaiSanPham(int id, String tenLoaiSanPham, String moTa, boolean trangThai, String hinhAnh) {
        this.id = id;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
    }

    // Getter và Setter cho id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter và Setter cho tenLoaiSanPham
    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    // Getter và Setter cho moTa
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // Getter và Setter cho trangThai
    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    // Getter và Setter cho hinhAnh
    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "LoaiSanPham{" +
                "id=" + id +
                ", tenLoaiSanPham='" + tenLoaiSanPham + '\'' +
                ", moTa='" + moTa + '\'' +
                ", trangThai=" + trangThai +
                ", hinhAnh='" + hinhAnh + '\'' +
                '}';
    }
}
