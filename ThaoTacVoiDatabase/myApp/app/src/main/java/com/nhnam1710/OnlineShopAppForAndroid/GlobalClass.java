package com.nhnam1710.OnlineShopAppForAndroid;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GlobalClass {
    private static String UserName;
    private static String Password;

    public static String getUserName() {
        return UserName;
    }

    public static void setUserName(String userName) {
        UserName = userName;
    }

    public static String getPassword() {
        return Password;
    }

    public static void setPassword(String password) {
        Password = password;
    }

    public static String getMD5(String input) {
        try {
            // Khởi tạo đối tượng MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Cập nhật dữ liệu cần mã hóa (chuỗi đầu vào)
            md.update(input.getBytes());

            // Thực hiện mã hóa và lấy kết quả
            byte[] digest = md.digest();

            // Chuyển đổi byte array thành chuỗi hex
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
