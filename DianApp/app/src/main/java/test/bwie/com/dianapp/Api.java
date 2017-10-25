package test.bwie.com.dianapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 1 on 2017/10/17.
 */

public class Api {
    //首页轮播
    public static  String LUNBO = "http://120.27.23.105/ad/getAd";
    //查询订单
    public static String FINDODER = "http://120.27.23.105/product/getOrders";
    //订单的获取
    public static  String SHOPORDER = "http://120.27.23.105/product/createOrder";
    //查询购物车
    public static  String FINDSHOP = "http://120.27.23.105/product/getCarts";
    //添加购物车
    public static String ADDShopCard = "http://120.27.23.105/product/addCart";
    //详情
    public static  String ShopDetails = "http://120.27.23.105/product/getProductDetail";
    public static  String ShopSecond = "http://120.27.23.105/product/getProductCatagory";
    public static  String ShopFirst = "http://120.27.23.105/product/getCatagory";
    public static  String ShopPath = "http://120.27.23.105/product/searchProducts";
    public static String ResgitPath = "http://120.27.23.105/user/reg";
    public static  String LoginPath = "http://120.27.23.105/user/login";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor edit;
    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences("key",Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
    }
}
