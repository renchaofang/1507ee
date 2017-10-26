package com.example.a1.yinyue.activitty;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a1.yinyue.DrawActivity;
import com.example.a1.yinyue.R;
import com.example.a1.yinyue.bean.InfoBean;
import com.example.a1.yinyue.custom.CustomViewProPass;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class EventBusActivity extends AppCompatActivity implements View.OnClickListener{

    private int i = 0 ;
    private Button button;
    private Button gaibian;
    private Button start;
    private Button result;
    private CustomViewProPass image;
    private Handler handler = new Handler();
       Runnable runnable = new Runnable() {
           @Override
           public void run() {
                if(i<30){
                    i++;
                    image.setProgress(i);
                    handler.postDelayed(runnable, 1000);
                }else{
                    //将线程移除
                    handler.removeCallbacks(runnable);
                }
           }
       };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        init();
        button = (Button)findViewById(R.id.jump);
        //进行EventBus注册
        EventBus.getDefault().register(EventBusActivity.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(EventBusActivity.this,JumpActivity.class));
            }
        });

        //设置最大进度
        image.setMax(30);
    }
    @Subscribe
    public void onEventMainThread(InfoBean infoBean){
        Toast.makeText(EventBusActivity.this,"我的第一个"+infoBean.getTitle(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(EventBusActivity.this);
    }
    public void init(){
        gaibian = (Button)findViewById(R.id.gaibian);
        start = (Button)findViewById(R.id.start);
        result = (Button)findViewById(R.id.result);
        image = (CustomViewProPass)findViewById(R.id.image_btn);
        gaibian.setOnClickListener(this);
        start.setOnClickListener(this);
        result.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gaibian:
                //改变颜色
                image.setCricleProgressColor(Color.BLUE);
                break;
            case R.id.start:
                //开始旋转
                handler.postDelayed(runnable,1000);
                break;
            case R.id.result:
                //重置
                //将线程移除
                handler.removeCallbacks(runnable);
                image.setProgress(0);
                i=0;
                startActivity(new Intent(EventBusActivity.this, DrawActivity.class));
                break;
        }
    }
}
