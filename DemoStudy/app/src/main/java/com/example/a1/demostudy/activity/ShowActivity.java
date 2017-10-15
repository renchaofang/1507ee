package com.example.a1.demostudy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.example.a1.demostudy.R;
import com.example.a1.demostudy.adapter.RecycleAdapter;
import com.example.a1.demostudy.bean.ShopBean;
import com.example.a1.demostudy.presenter.UserPresenter;
import com.example.a1.demostudy.view.IUserShop;

import org.json.JSONArray;

import java.util.List;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener,IUserShop {

    private Handler handler = new Handler();
    private static int LINERSTYLE = 1;
    private static int GARDSTYLE = 2;
    private int page=1;
    private String key ;
    private String path = "http://120.27.23.105/product/searchProducts";
    private EditText info;
    private TextView textView;
    private TextView sousuo;
    private CheckBox qiehuan;
    private UserPresenter userPresenter;
    private RecyclerView recyclerView;
    private SwipyRefreshLayout srl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        init();
        //将P层进行关联起来
        userPresenter = new UserPresenter(ShowActivity.this);

        //进行改变recycle的布局管理器
        qiehuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ShowActivity.this,2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    Toast.makeText(ShowActivity.this,"选中了",Toast.LENGTH_SHORT).show();
                    Data();
                }else{
                    LinearLayoutManager liner = new LinearLayoutManager(ShowActivity.this);
                    recyclerView.setLayoutManager(liner);
                    Data();
                    Toast.makeText(ShowActivity.this,"取消选中了",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //进行刷新加载数据
        srl.setDirection(SwipyRefreshLayoutDirection.BOTH);srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                page = 1;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       Data();
                        srl.setRefreshing(false);
                    }
                }, 2000);
            }

            @Override
            public void onLoad(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.i("iii",page+"");
                        Data();
                        srl.setRefreshing(false);
                    }
                }, 2000);
            }
        });

    }
    //初始化资源控件
    public void init(){
        info = (EditText)findViewById(R.id.suosou);
        sousuo = (TextView)findViewById(R.id.ss);
        qiehuan = (CheckBox)findViewById(R.id.qiehuan);
        //进行查询刷新展示控件
        recyclerView = (RecyclerView)findViewById(R.id.recycle);
        srl = (SwipyRefreshLayout)findViewById(R.id.srl);
        sousuo.setOnClickListener(this);
        qiehuan.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
            switch (view.getId()){
                //点击进行搜索
                case R.id.ss:
                    LinearLayoutManager liner = new LinearLayoutManager(ShowActivity.this);
                    recyclerView.setLayoutManager(liner);
                    //调用方法进行获取的数据，请求资源
                    Data();
                    break;
            }
    }
    @Override
    public void getData(String type ,int  id) {
        userPresenter.getShoppingData(type,id);
    }
    @Override
    public void AddData(List<ShopBean> jsonArray) {
        //进行加载数据
        RecycleAdapter recycleAdapter = new RecycleAdapter(jsonArray,ShowActivity.this);
        recyclerView.setAdapter(recycleAdapter);
    }
    //进行封装一个方法，来进行请求数据
    public void Data(){
        String s = info.getText().toString();
        getData(s,page);
    }

}
