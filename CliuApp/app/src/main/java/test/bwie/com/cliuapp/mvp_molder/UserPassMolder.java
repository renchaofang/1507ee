package test.bwie.com.cliuapp.mvp_molder;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.cliuapp.Api;
import test.bwie.com.cliuapp.mvp_view.IUserNameLogin;
import test.bwie.com.cliuapp.mvp_view.IUserNameView;
import test.bwie.com.cliuapp.utils.OkHHttpClientUtils;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/12 23:12
 */
public class UserPassMolder implements IPassWordMolder {
  /*  private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String resgit = (String) msg.obj;
                    Log.i("www", resgit);
                    break;
                case 1:
                    String loginstring = (String) msg.obj;
                    Log.i("www", loginstring);
                    break;
            }
        }
    };*/
    @Override
    public void doResgitUser(String name, String password, String email, final IUserNameView iusername) {
        //获取的到数据进行请求网络
          HashMap<String,String > resgit_params = new HashMap<>();
            resgit_params.put("username",name);
            resgit_params.put("password",password);
            resgit_params.put("password_confirm",password);
            resgit_params.put("email", email);
            resgit_params.put("client","android");
            OkHHttpClientUtils.dopost(Api.resgitpath, resgit_params, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //成功之后进行请求数据
                    String string = response.body().string();
                    Log.i("xxx", string);
                    try {
                        JSONObject jsonObject = new JSONObject(string);
                        int code = jsonObject.optInt("code");
                        if(code==200){
                            //注册成功
                            JSONObject datas = jsonObject.getJSONObject("datas");
                            String key = datas.optString("key");
                            String user_id = datas.optString("userid");
                            //进行保存状态操作
                            Api.edit.putString("login_key",key);
                            Api.edit.putString("login_userid",user_id);
                            Api.edit.commit();
                            iusername.ResgitSuccess();
                        }else if(code==400){
                            iusername.ResgitFail();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                  /*  Message message = new Message();
                    message.what=0;
                    message.obj=string;
                    handler.sendMessage(message);*/
                }
            });
        }
    @Override
    public void doLoginUser(String name, String pass, final IUserNameLogin iUserNameLogin) {
            Map<String, String> params = new HashMap<>();
            params.put("username",name);
            params.put("password",pass);
            params.put("client","android");
            OkHHttpClientUtils.dopost(Api.lofinpath, params, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //获取得到数据
                    String string = response.body().string();
                    Log.i("xxx", string);
                    try {
                        JSONObject jsonObject = new JSONObject(string);
                        int code = jsonObject.optInt("code");
                        if(code==200){
                            JSONObject datas = jsonObject.getJSONObject("datas");
                            //登录成功
                            //进行登录成功之后信息状态的保存
                            Api.edit.putBoolean("login",true);
                            Api.edit.putInt("id",datas.optInt("userid"));
                            Api.edit.putString("keyy",datas.optString("key"));
                            Api.edit.putString("username",datas.optString("username"));
                            //进行提交
                            Api.edit.commit();
                            iUserNameLogin.LoginSuccess();
                        }else if(code==400){
                            iUserNameLogin.LoginFail();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                   /* Message message = new Message();
                    message.what=1;
                    message.obj=string;
                    handler.sendMessage(message);*/
                }
            });
        }
}

