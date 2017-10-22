package test.bwie.com.cliuapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.adapter.ShopShowAdapter;
import test.bwie.com.cliuapp.bean.ShoppingBean;
import test.bwie.com.cliuapp.utils.OkHHttpClientUtils;

public class ShoppingActivity extends AppCompatActivity {

    private List<ShoppingBean.ResultBean.WallBean.DocsBean> docs;
    public Handler hnadler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String string = (String) msg.obj;
                    //进行截取字符串
                    String haha = "/**/jsonp1(";
                    String sb = string.substring(haha.length(),string.length()-2);

                    Log.i("sss", "解析"+sb);
                    //进行解析字符串
                       /* JSONObject jsonObject = new JSONObject(string);
                        JSONObject result = jsonObject.getJSONObject("result");
                        JSONObject wall = result.getJSONObject("wall");
                        JSONArray docs = wall.getJSONArray("docs");*/
                    Gson gson = new Gson();
                    ShoppingBean shoppingBean = gson.fromJson(sb, ShoppingBean.class);
                    ShoppingBean.ResultBean result = shoppingBean.getResult();
                    ShoppingBean.ResultBean.WallBean wall = result.getWall();
                    docs = wall.getDocs();
                    dataAdapterr(docs);
                    break;
            }
        }
    };
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private ShopShowAdapter shopShowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        Log.i("sss", "这里的集合数据" + data);
        init();
        //显示下拉刷新
      /*  pullLoadMoreRecyclerView.setRefreshing(true);
        pullLoadMoreRecyclerView.setIsLoadMore(true);
        //设置上拉加载文字
        pullLoadMoreRecyclerView.setFooterViewText("loading·····正在加载");
        //设置下拉刷新颜色
        pullLoadMoreRecyclerView.setColorSchemeResources(android.R.color.holo_red_dark,android.R.color.holo_blue_dark);
        //获得路径进行解析获取的到数据*/
        pullLoadMoreRecyclerView.setGridLayout(2);
        getdata(data);
        //进行上拉刷新，下拉加载的操作
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

            }
            @Override
            public void onLoadMore() {

            }
        });
    }
    //初始化资源控价
    public void init(){
        pullLoadMoreRecyclerView = (PullLoadMoreRecyclerView)findViewById(R.id.shop_pullview);
    }
    //进行拼接接口，最后进行网络请求数据
    public void getdata(String s){
        Log.i("xxx", s);
        //进行请求网络数据
        OkHHttpClientUtils.doget(s, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = new Message();
                message.what=0;
                message.obj=string;
                hnadler.sendMessage(message);
            }
        });
    }
    //适配器进行适配数据
    public void dataAdapterr(List<ShoppingBean.ResultBean.WallBean.DocsBean> list){
        if(shopShowAdapter==null){
            shopShowAdapter = new ShopShowAdapter(list,ShoppingActivity.this);
            pullLoadMoreRecyclerView.setAdapter(shopShowAdapter);
        }else{
            shopShowAdapter.notifyDataSetChanged();
        }
    }
}
