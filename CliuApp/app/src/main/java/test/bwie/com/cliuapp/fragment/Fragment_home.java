package test.bwie.com.cliuapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.cliuapp.Api;
import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.activity.SeekActivity;
import test.bwie.com.cliuapp.adapter.GradviewAdapter_One;
import test.bwie.com.cliuapp.adapter.RecyclerViewAdapter;
import test.bwie.com.cliuapp.bean.LogBean;
import test.bwie.com.cliuapp.custom.MarqueeTextView;
import test.bwie.com.cliuapp.listenter.MarqueeTextViewClickListener;
import test.bwie.com.cliuapp.utils.OkHHttpClientUtils;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/8/31 23:38
 */
public class Fragment_home extends Fragment {

    private static int count = 1 ;
    private View view;
    private ImageView saoma_btn;
    private ImageView message_btn;
    private EditText editText;
    private ImageView image_btn;
    private Banner banner;
    private GridView gridView;
    private RecyclerView listView;
    private JSONArray itemList;
    private String [] textArrays = new String[]{"this is content No.1","this is content No.2","this is content No.3"};
    private Handler handler = new Handler(){



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String obj = (String) msg.obj;
                    //进行解析字符串
                    Log.i("xxx", obj);
                    //进行截取字符串
                    String haha = "jQuery21104587953138117029_1504264031748(";
                    String sb = obj.substring(haha.length(),obj.length()-1);
                    Log.i("xxx", sb);
                    try {
                        JSONObject jsonObject = new JSONObject(sb);
                        JSONObject data = jsonObject.getJSONObject("data");
                        itemList = data.getJSONArray("itemList");
                        int length = itemList.length();
                        Log.i("xxx", "数据的长度" + length);
                        //得到数据之后进行数据的展示
                        //RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(itemList, getActivity());
                        //listView.setAdapter(recyclerViewAdapter);
                        initadapter(itemList);
                        //进行点击时间的监听方式
                        recyclerViewAdapter.setOnRecycleitemListenter(new RecyclerViewAdapter.OnRecycleitemListenter() {
                            @Override
                            public void onRecycleitemclick(int position) {
                                Toast.makeText(getActivity(), "我点击了" + position, Toast.LENGTH_SHORT).show();
                            }
                        });
                        recyclerViewAdapter.setOnRecyclerViewLongItemClickListener(new RecyclerViewAdapter.OnRecyclerViewLongItemClickListener() {
                            @Override
                            public void onRecyclerViewLongItemClick(int position) {
                                Toast.makeText(getActivity(), "我长安点击了" + position, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    String lunaddress = (String) msg.obj;
                    //进行解析
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(lunaddress);
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONArray ad1 = data.getJSONArray("ad1");
                        List<String> stringlist = new ArrayList<>();
                        for(int i =0;i<ad1.length();i++){
                            JSONObject jsonObject1 = ad1.getJSONObject(i);
                            String image = jsonObject1.getString("image");
                            stringlist.add(image);
                        }
                        //封装好自动轮播图的操作L
                   //设置小点居中
                        banner.setIndicatorGravity(Banner.CENTER);
                        //进行设置样式
                        banner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
                        banner.isAutoPlay(true);
                        banner.setDelayTime(3000);
                        banner.setImages(stringlist);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    private MarqueeTextView marqueeTextView;
    private JSONArray jsonArray;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //进行优化fragment操作
        if(view==null){
            view = inflater.inflate(R.layout.fragment_home,container,false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }
        //获取控件
       initview();
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击文本框", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SeekActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //进行轮播图的操作
        lunbogetData();
        //进行首页数据的展示操作
        showData();
        //设置StaggeredGridLayoutManager布局管理器
        //设置GridLayoutManager布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //把布局管理器给RecyclerView
        listView.setLayoutManager(gridLayoutManager);
        //进行设置华丽的分割线
        // MyDecoration myDecoration = new MyDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        //listView.addItemDecoration(myDecoration);
        getGridView();
        //告示栏的点击监听事件
        marqueeTextView.setTextArraysAndClickListener(textArrays,new MarqueeTextViewClickListener(){
            @Override
            public void onClick(View view) {
            }
        });
    }

    private void getGridView() {
        List<LogBean> loglist = new ArrayList<>();
        loglist.add(new LogBean(R.mipmap.a,"天猫"));
        loglist.add(new LogBean(R.mipmap.b,"聚划算"));
        loglist.add(new LogBean(R.mipmap.c,"国际猫"));
        loglist.add(new LogBean(R.mipmap.d,"外卖"));
        loglist.add(new LogBean(R.mipmap.e,"超市猫"));
        loglist.add(new LogBean(R.mipmap.f,"充值"));
        loglist.add(new LogBean(R.mipmap.g,"飞猪旅行"));
        loglist.add(new LogBean(R.mipmap.h,"领金币"));
        loglist.add(new LogBean(R.mipmap.i,"拍卖场"));
        loglist.add(new LogBean(R.mipmap.j,"分类"));
        //调用适配器进行展示数据
        gridView.setAdapter(new GradviewAdapter_One(loglist,getActivity()));
    }
    public void initview(){
        saoma_btn = (ImageView) view.findViewById(R.id.shaoma_btn);
        message_btn = (ImageView) view.findViewById(R.id.message_btn);
        editText = (EditText)view.findViewById(R.id.edt_operator_name);
        banner = (Banner)view.findViewById(R.id.home_viewpager);
        gridView = (GridView) view.findViewById(R.id.home_gridview);
        listView = (RecyclerView) view.findViewById(R.id.home_listview);
        marqueeTextView = (MarqueeTextView)view.findViewById(R.id.marquertext);
    }
    private void showData() {
        //利用Ok网络请求数据
        OkHHttpClientUtils.doget(Api.path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到数据之后
                String string = response.body().string();
                Message message = new Message();
                message.obj=string;
                message.what=0;
                handler.sendMessage(message);
            }
        });
    }
    public void lunbogetData(){
        OkHHttpClientUtils.doget(Api.lunPath, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到数据之后
                String string = response.body().string();
                Message message = new Message();
                message.obj=string;
                message.what=1;
                handler.sendMessage(message);
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        marqueeTextView.releaseResources();
    }

    //进行多条目加载的操作
    public void loadData() {
        if(count==1){
            jsonArray = new JSONArray();
        }
        for( int i =0;i<itemList.length();i++){
            try {
                jsonArray.put(itemList.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //进行引用

    }
    //进行适配器的判空处理
    public void  initadapter(JSONArray jsonArray){
        if(recyclerViewAdapter==null){
            recyclerViewAdapter = new RecyclerViewAdapter(jsonArray, getActivity());
            listView.setAdapter(recyclerViewAdapter);
        }else{
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

}
