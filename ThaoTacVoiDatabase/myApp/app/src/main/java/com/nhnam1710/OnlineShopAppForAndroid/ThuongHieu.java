package com.nhnam1710.OnlineShopAppForAndroid;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class ThuongHieu implements Serializable {
    // Các thuộc tính của đối tượng Thương hiệu
    private int id;
    private String tenThuongHieu;
    private String moTa;
    private String trangWeb;
    private String logo;

    // Phương thức khởi tạo không tham số
    public ThuongHieu() {
    }

    // Phương thức khởi tạo với tất cả các thuộc tính
    public ThuongHieu(int id, String tenThuongHieu, String moTa, String trangWeb, String logo) {
        this.id = id;
        this.tenThuongHieu = tenThuongHieu;
        this.moTa = moTa;
        this.trangWeb = trangWeb;
        this.logo = logo;
    }

    // Phương thức khởi tạo từ một JSONObject
    public ThuongHieu(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getInt("id");
            this.tenThuongHieu = jsonObject.getString("TenThuongHieu");
            this.moTa = jsonObject.getString("MoTa");
            this.trangWeb = jsonObject.getString("TrangWeb");
            this.logo = jsonObject.getString("Logo");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    // Getter và Setter cho các thuộc tính
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTrangWeb() {
        return trangWeb;
    }

    public void setTrangWeb(String trangWeb) {
        this.trangWeb = trangWeb;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
