package test.bwie.com.cliuapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/7 15:59
 */

public class Api {
    public static  String shopjiesuan = "http://169.254.229.178/mobile/index.php?act=member_buy&op=buy_step1";
    public static  String showCard = " http://169.254.229.178/mobile/index.php?act=member_cart&op=cart_list";
    public static  String addcard = "http://169.254.229.178/mobile/index.php?act=member_cart&op=cart_add";
    public static  String shopinfo = "http://169.254.229.178/mobile/index.php?act=goods&op=goods_list&page=100&gc_id=587";
    public static  String  resgitpath = "http://169.254.229.178/mobile/index.php?act=login&op=register";
    public static  String lofinpath = "http://169.254.229.178/mobile/index.php?act=login";
    public static  String lunPath = "http://m.yunifang.com/yunifang/mobile/home ";
    public static  String classpath = "http://169.254.229.178/mobile/index.php?act=goods_class";
    public static  String path ="http://qiang.mogujie.com/jsonp/actGroupItem/1?callback=jQuery21104587953138117029_1504264031748&groupKey=11q&_=1504264031749";
    public static SharedPreferences sp;
    public static SharedPreferences.Editor edit;

    public static  void init(Context context){
        sp = context.getSharedPreferences("system", Context.MODE_PRIVATE);
        edit = sp.edit();
    }
}
