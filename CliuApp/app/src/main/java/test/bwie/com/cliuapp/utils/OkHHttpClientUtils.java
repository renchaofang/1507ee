package test.bwie.com.cliuapp.utils;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 1.类的用途  封装ok工具类，运用单列模式
 * 2.@author1
    }
}
* 3.@data2017/9/6 19:16
        */
public class OkHHttpClientUtils {

    private static OkHttpClient okHttpClient = null;
    private OkHHttpClientUtils (){}
    public static OkHttpClient getInatance(){
        if(okHttpClient==null){
            //进行同步锁
            synchronized (OkHHttpClientUtils.class){
                if (okHttpClient==null){}
                        okHttpClient = new OkHttpClient();
            }
        }
        return okHttpClient;
    }

        //进行get请求方式
        public static void doget(String url, Callback callback){
            OkHttpClient inatance = getInatance();
            //创建request的对象
            Request request = new Request.Builder().url(url).build();
            //创建Call对象
            Call call = inatance.newCall(request);
            //进行异步请求
            call.enqueue(callback);
        }
    //ok进行post的方式请求数据，并且在子线程里面进行更新UIde
    public static void dopost(String urll, Map<String,String> map,Callback callback){
        OkHttpClient inatance = getInatance();
        //3.x版本post请求换成FormBody 封装键值对参数
        FormBody.Builder builder = new FormBody.Builder();
        //将阐述进行添加
        //遍历集合
        for(String key:map.keySet()){
                builder.add(key,map.get(key));
        }
        Request request = new Request.Builder().url(urll).post(builder.build()).build();
        Call call = inatance.newCall(request);
        call.enqueue(callback);
    }

    //通过ok进行文件的上传
    public static void getUp(){

    }
}


