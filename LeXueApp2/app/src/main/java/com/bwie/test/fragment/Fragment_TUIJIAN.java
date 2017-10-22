package com.bwie.test.fragment;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bwie.test.R;
import com.bwie.test.js.JsJava;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/27 20:19
 */
public class Fragment_TUIJIAN extends Fragment {

    private View view;
    private WebView webView;
    private String path = "http://lexue365.51dangao.cn/Page/recommend.html";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.tuijian,container,false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }
        init();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
     /*   //利用webview进行加载网页
        WebSettings settings = webView.getSettings();
        //支持加载javaScript,默认是false,
        settings.setJavaScriptEnabled(true);
        //WebViewClient主要帮助WebView处理各种通知、请求事件的
        webView.setWebViewClient(new WebViewClient(){ });
        //WebChromeClient主要辅助WebView处理JavaScript的对话框、网站图标、网站title、加载进度等比如
        webView.setWebChromeClient(new WebChromeClient(){});
        //给webview设置加载的网页地址
	    webView.loadUrl(path);
        webView.addJavascriptInterface(new ItemLooper(),"text");
        */
        initWebView();
    }
    public void  init(){
        webView = (WebView)view.findViewById(R.id.webview);
    }

    private void initWebView() {
        /**
         * WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
         * WebViewClient就是帮助WebView处理各种通知、请求事件
         *
         * 致命问题
         * 1.不要忘记加权限<uses-permission android:name="android.permission.INTERNET"/>
         *
         */
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//设置滚动条不占位
        //获取webView设置的对象
        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(true);//可缩放
        settings.setJavaScriptEnabled(true);//支持js
        settings.setGeolocationEnabled(true);//开启webView的定位
        settings.setDomStorageEnabled(true);//开启DomStorage缓存
        /**
         * 数据库未开启   数据库路径未开启
         */
        webView.setWebViewClient(new WebViewClient()
        {
            //覆盖第三方浏览器打开网页的操作
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            // 有页面跳转时被回调
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Toast.makeText(getActivity(), "我跳转了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // 页面跳转结束后被回调
                Toast.makeText(getActivity(), "我跳完了",Toast.LENGTH_SHORT).show();
            }
            //出现错误时
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), "Oh no! " + description,
                        Toast.LENGTH_SHORT).show();
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            //此方法可获得网页标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.i("xxx", title);//title就是网页标题
            }

            //处理JavaScript Alert事件
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                // 用Android组件替换
                new AlertDialog.Builder(getActivity()).setMessage(message).setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                }).setCancelable(false).create().show();
                return true;
            }

            /**处理定位的相关，否则WebView不会开启定位功能，类似百度地图这样的就没法定位*/
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);              //这个必须有
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            /**打开网页的进度,newProgress就是*/
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });

        //以注释的方法注册与js交互的方法
        /**
         * window.lexue365
         */
        webView.addJavascriptInterface(new JsJava(getContext()),"lexue365");
        webView.loadUrl(path);
    }
    /*js交互的方法如下*/
   /* @JavascriptInterface
    public void jumpActivity(String url,String id)
    {
        Log.i("xxx","js交互获取的数据    url:"+url+"  id:"+id);
        //跳转到详情页面
        Intent intent=new Intent(getActivity(),TiaoActivity.class);
        //封装Bean对象
        Tiao tiao=new Tiao();
        tiao.id=id;
        tiao.url=url;
        intent.putExtra("tiao",tiao);
        startActivity(intent);
    }*/
}
