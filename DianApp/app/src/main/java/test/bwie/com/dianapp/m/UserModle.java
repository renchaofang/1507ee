package test.bwie.com.dianapp.m;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.dianapp.Api;
import test.bwie.com.dianapp.bean.ShopDetailsBean;
import test.bwie.com.dianapp.bean.ShopSecondBean;
import test.bwie.com.dianapp.bean.ShopThreeBean;
import test.bwie.com.dianapp.bean.ShowInfoBean;
import test.bwie.com.dianapp.bean.UserBean;
import test.bwie.com.dianapp.utils.SPUtil;
import test.bwie.com.dianapp.v.IFindShop;
import test.bwie.com.dianapp.v.IFirstShop;
import test.bwie.com.dianapp.v.ILunBoView;
import test.bwie.com.dianapp.v.IShopDetails;
import test.bwie.com.dianapp.v.IShopInfo;
import test.bwie.com.dianapp.v.IUserLoginView;
import test.bwie.com.dianapp.v.IUserView;
import test.bwie.com.okhttoutils_rcf.Utils.OkHttp3Utils;

/**
 * Created by 1 on 2017/10/17.
 */

public class UserModle implements IUserModle{
    private Handler handler = new Handler();
    private List<ShopThreeBean> threeList = new ArrayList<>();
    private List<ShopSecondBean> secondList = new ArrayList<>();
    private List<ShowInfoBean> list = new ArrayList<>();
    private ArrayList<ShowInfoBean> newList;

    //进行处理用户注册信息的方法
    @Override
    public void getUserInfo(String name, String pass, final IUserView iUserView) {
        //进行判断处理数据
        if(TextUtils.isEmpty(name) || name.length()==11){
            if(TextUtils.isEmpty(pass) || pass.length()>6){
                //满足两个条件进行出数据
                HashMap<String, String> map = new HashMap<>();
                map.put("mobile",name);
                map.put("password",pass);
                OkHttp3Utils.doPost(Api.ResgitPath, map, new Callback() {
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
                                    UserBean userBean = new UserBean();
                                    JSONObject json = new JSONObject(string);
                                    Log.i("xxx",json.toString()+"");
                                    String code = json.getString("code");
                                    if("0".equals(code)){
                                        //进行保存数据
                                        JSONObject data = json.getJSONObject("data");
                                        String mobile = data.getString("mobile");
                                        String password = data.getString("password");
                                        String username = data.getString("username");
                                        int uid = data.getInt("uid");
                                        int money = data.getInt("money");
                                        //进行保存用户的注册信息保存
                                        Api.edit.putInt("resgit_id",uid);
                                        Api.edit.commit();
                                        iUserView.getSuccess();
                                    }else{
                                        iUserView.getFail();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                });
            }else{
                iUserView.getJudgementPass();
            }
        }else{
            iUserView.getJudgementName();
        }
    }

    @Override
    public void getUserLogin(String name, String pass, final IUserLoginView iUserLoginView) {
        if(TextUtils.isEmpty(name) || name.length()==11){
                if(TextUtils.isEmpty(pass) || pass.length()>6){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("mobile",name);
                        map.put("password",pass);
                        OkHttp3Utils.doPost(Api.LoginPath,map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String string = response.body().string();
                        Log.i("xxxx",string);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject jsonObject = new JSONObject(string);
                                    Log.i("xxxx",jsonObject.toString());
                                    String code = jsonObject.getString("code");
                                    if("0".equals(code)){
                                        JSONObject data = jsonObject.getJSONObject("data");
                                        int uid = data.getInt("uid");
                                        Log.i("qqq",uid+"");
                                        SPUtil spUtil = new SPUtil((Context)iUserLoginView,"key");
                                        spUtil.putBoolean("IS_LOGIN",true);
                                        spUtil.putInt("login_id",uid);
                                        spUtil.putString("login_mobile",data.getString("mobile"));
                                        spUtil.putString("login_username",data.getString("username"));
                                        iUserLoginView.getSuccess();
                                    }
                                    else{
                                        iUserLoginView.getFail();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
                    }else{
                                iUserLoginView.getJudgementPass();
                        }
            }else{
                    iUserLoginView.getJudgementName();
            }
    }

    @Override
    public void getShop(final String title, final int page, final IShopInfo iShopInfo) {
        HashMap<String, String> map = new HashMap<>();
        String p = String.valueOf(page);
        map.put("keywords",title);
        map.put("page",p);
        Log.i("aaa",Api.ShopPath+"?keywords="+title+"&page="+page);
        OkHttp3Utils.doPost(Api.ShopPath, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i("aaa",string);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            Log.i("aaa",jsonObject.toString());
                            JSONArray data = jsonObject.getJSONArray("data");
                            for(int i=0;i<data.length();i++){
                                ShowInfoBean showInfoBean = new ShowInfoBean();
                                JSONObject jsonObject1 = data.getJSONObject(i);
                                //标题
                                String title1 = jsonObject1.getString("title");
                                //图片
                                String images = jsonObject1.getString("images");
                                //副标题
                                String subhead = jsonObject1.getString("subhead");
                                //价格
                                int price = jsonObject1.getInt("price");
                                //pid唯一标识
                                int pid = jsonObject1.getInt("pid");
                                showInfoBean.setPid(pid);
                                showInfoBean.setImage(images);
                                showInfoBean.setPrice(price);
                                showInfoBean.setSubhead(subhead);
                                showInfoBean.setTitle(title1);

                                list.add(showInfoBean);
                                //jsonObject1.getInt("");
                                //进行保存得到商品唯一的id
                                SPUtil spUtil = new SPUtil((Context) iShopInfo,"key");
                                spUtil.putInt("pid",pid);
                            }
                            if(page==1){
                                newList = new ArrayList<>();
                            }
                            for(int i = 0 ; i<list.size();i++){
                                newList.add(list.get(i));
                            }
                            Log.i("www",newList.size()+"");
                            iShopInfo.getRecycle(newList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void getFirst(int cid, final IFirstShop iFirstShop) {
        final String s = Api.ShopSecond + "?cid=" + cid + "";
        OkHttp3Utils.doGet(s, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i("xxx","二级条目"+string);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            JSONArray data = jsonObject.getJSONArray("data");
                            for (int i =0;i<data.length();i++){
                                ShopSecondBean shopSecondBean = new ShopSecondBean();
                                JSONObject arr = data.getJSONObject(i);
                                JSONArray threelist = arr.getJSONArray("list");
                                String name = arr.getString("name");
                                String pcid = arr.getString("pcid");
                                shopSecondBean.setName(name);
                                shopSecondBean.setPic(pcid);
                                shopSecondBean.setJsonArray(threelist);
                                secondList.add(shopSecondBean);
                            }
                           iFirstShop.getAdapter(secondList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void getDetailsSP(int pid, final IShopDetails iShopDetails) {
        String s = Api.ShopDetails + "?pid=" + pid;
        OkHttp3Utils.doGet(s,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i("ddd",string);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ShopDetailsBean shopDetailsBean = new ShopDetailsBean();
                            JSONObject jsonObject = new JSONObject(string);
                            JSONObject data = jsonObject.getJSONObject("data");
                            JSONObject seller = jsonObject.getJSONObject("seller");
                            shopDetailsBean.setImg( data.getString("images"));
                            shopDetailsBean.setDianapu(    seller.getString("name"));
                            shopDetailsBean.setPid(data.getInt("pid"));
                            shopDetailsBean.setFubiaoti(   data.getString("subhead"));
                            shopDetailsBean.setTitle(  data.getString("title"));
                            shopDetailsBean.setPrice( data.getInt("price"));
                            shopDetailsBean.setSellerid(String.valueOf(data.getInt("sellerid")));
                            SPUtil spUtil = new SPUtil((Context)iShopDetails,"key");
                            spUtil.putString("SHOP_ID", String.valueOf(data.getInt("pid")));
                            spUtil.putString("SELLERD_ID", String.valueOf(data.getInt("sellerid")));
                            iShopDetails.setShopInfo(shopDetailsBean);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void getCardShop(String uid, String pid, String sid) {
        Map<String ,String >  map = new HashMap<>();
        map.put("uid",uid);
        map.put("pid",pid);
        OkHttp3Utils.doPost(Api.ADDShopCard, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i("lll",string);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            String code = jsonObject.getString("code");
                            if("0".equals(code)){
                                Log.i("xxx","添加成功");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    //查询购物车
    @Override
    public void getFindShopModle(String uid, final IFindShop iFindShop) {
        Map<String ,String> map = new HashMap<>();
        map.put("uid",uid);
            OkHttp3Utils.doPost(Api.FINDSHOP, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String string = response.body().string();
                    Log.i("kkk","商品的信息"+string);
                    handler.post(new Runnable() {



                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(string);
                                String code = jsonObject.getString("code");
                                if("0".equals(code)){
                                    Log.i("ooo",code);
                                    JSONArray data = jsonObject.getJSONArray("data");

                                    iFindShop.getAddCard(data);
                                }else{
                                    Log.i("ooo","cnd");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
    }
    //得到订单号
    @Override
    public void getOrderM(String uid, String price) {
        HashMap<String , String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("price",price);
        OkHttp3Utils.doPost(Api.SHOPORDER, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i("xxx","订单生成功"+string);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    //查询订单号
    @Override
    public void getOrderInfo(String uid, final int tid, final IFindShop iFindShop) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid",uid);
        OkHttp3Utils.doPost(Api.FINDODER, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i("fff",string);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            if(tid==0){
                                    iFindShop.WeiXIN("");
                            }else if(tid==1){
                                iFindShop.ZhiFuBao("");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void getLunBo(final ILunBoView iLunBoView) {
        OkHttp3Utils.doGet(Api.LUNBO, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i("uuu",string);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            JSONArray data = jsonObject.getJSONArray("data");
                            List<String> objects = new ArrayList<>();
                            for(int i=0;i<data.length();i++){
                                JSONObject jsonObject1 = data.getJSONObject(i);
                                String icon = jsonObject1.getString("icon");
                                objects.add(icon);
                            }
                                    iLunBoView.getAdapter(objects);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }


}
