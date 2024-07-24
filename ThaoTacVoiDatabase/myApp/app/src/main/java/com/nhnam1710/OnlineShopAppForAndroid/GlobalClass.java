package com.nhnam1710.OnlineShopAppForAndroid;

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
}
