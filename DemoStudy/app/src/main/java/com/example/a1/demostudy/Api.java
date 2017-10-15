package com.example.a1.demostudy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 1 on 2017/10/14.
 */

public class Api {

    public static  String getShopping = "";
    public static String getResgit = "http://120.27.23.105/user/reg";
    public static String getLogin = "http://120.27.23.105/user/login";
    public static  String getUp = "http://120.27.23.105/file/upload";
    public static SharedPreferences sp;
    public static SharedPreferences.Editor edit;

    public static void init(Context context) {
        sp = context.getSharedPreferences("system", Context.MODE_PRIVATE);
        edit = sp.edit();
    }
}