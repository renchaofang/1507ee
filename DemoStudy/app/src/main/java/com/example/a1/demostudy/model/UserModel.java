package com.example.a1.demostudy.model;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.example.a1.demostudy.Api;
import com.example.a1.demostudy.activity.ShowActivity;
import com.example.a1.demostudy.bean.ShopBean;
import com.example.a1.demostudy.view.IUserLogin;
import com.example.a1.demostudy.view.IUserShop;
import com.example.a1.demostudy.view.IUserView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.okhttoutils_rcf.Utils.OkHttp3Utils;

/**
 * Created by 1 on 2017/10/14.
 */

public class UserModel implements IUserModel {
    private Handler handler = new Handler();
    private String path = "http://120.27.23.105/product/searchProducts";
    private List<ShopBean> list = new ArrayList<>();
    private ArrayList<ShopBean> newShoplist;

    @Override
    public void getResgit(String name, String pass, final IUserView iUserView){
            if(TextUtils.isEmpty(name) || name.length()==11 || TextUtils.isEmpty(pass)){
                //进行获取具体数据
                HashMap<String, String> map = new HashMap<>();
                map.put("mobile",name);
                map.put("password",pass);
                OkHttp3Utils.doPost(Api.getResgit, map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String string = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject jsonObject = new JSONObject(string);
                                    Log.i("xxx",jsonObject.toString());
                                    String code = jsonObject.getString("code");
                                    if("0".equals(code)){
                                        iUserView.ResgitSuccess();
                                    }else{
                                        iUserView.ResgitFail();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }else{
            }
    }
    //进行登录界面
    @Override
    public void getLogin(String name, String pass, final IUserLogin iUserLogin) {
        if(TextUtils.isEmpty(name) || name.length()==11 || TextUtils.isEmpty(pass)){
            HashMap<String, String> map = new HashMap<>();
            map.put("mobile",name);
            map.put("password",pass);
            OkHttp3Utils.doPost(Api.getLogin, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String string = response.body().string();
                    Log.i("sss","登陆成功"+string);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(string);
                                Log.i("sss","登陆成功"+jsonObject.toString());
                                String code = jsonObject.getString("code");
                                if("0".equals(code)){
                                    JSONObject data = jsonObject.getJSONObject("data");
                                    String mobile = data.getString("mobile");
                                    String password = data.getString("password");
                                    String username = data.getString("username");
                                    int uid = data.getInt("uid");
                                    Api.edit.putBoolean("login",true);
                                    Api.edit.putString("moblie",mobile);
                                    Api.edit.putString("pass",password);
                                    Api.edit.putString("username",username);
                                    Api.edit.putInt("id",uid);
                                    Api.edit.commit();
                                    iUserLogin.ResgitSuccess();
                                }else{
                                    iUserLogin.ResgitFail();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
    }
    //进行请求数据（视图）、
    @Override
    public void getShowData(String type, final int page, final IUserShop iUserShop) {
        String s = path + "?keywords=" + type + "&page=" + page + "";
        OkHttp3Utils.doGet(s, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String string = response.body().string();
                    Log.i("xxx",string);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(string);
                                JSONArray data = jsonObject.getJSONArray("data");
                                Log.i("xxx",data.length()+"");
                                for(int i = 0 ; i<data.length();i++){
                                    ShopBean shopBean = new ShopBean();
                                    JSONObject jsonObject1 = data.getJSONObject(i);
                                    shopBean.setTitle( jsonObject1.getString("title"));
                                    shopBean.setImage(jsonObject1.getString("images"));
                                    shopBean.setPrice(jsonObject1.getInt("price"));
                                    //进行添加集合
                                    list.add(shopBean);
                                }
                                if(page==1){
                                    //创建新的集合添加数据
                                    newShoplist = new ArrayList<>();
                                }
                                for(int j = 0 ; j<list.size();j++){
                                    newShoplist.add(list.get(j));
                                }
                                Log.i("eee", "run: "+newShoplist.size());
                                iUserShop.AddData(newShoplist);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
    }



}
