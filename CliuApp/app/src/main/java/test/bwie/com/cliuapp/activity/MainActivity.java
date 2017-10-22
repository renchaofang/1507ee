package test.bwie.com.cliuapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.adapter.ViewPagerAdapterFirst;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private List<Integer> viewList = new ArrayList<>();
    private List<ImageView> imageViewList = new ArrayList<>();
    private int lastposition = 0;
    private int djs = 5;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what>0){

                textView.setText("倒计时"+msg.what);

            }else{
                //进行跳转
                Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                startActivity(intent);
                //进行保存状态
                edit.putBoolean("key",true);
                edit.commit();
            }
        }
    };
    private RelativeLayout relativeLayout;
    private TextView textView;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件资源
        init();
        //进行添加倒计时的布局
        textView = new TextView(MainActivity.this);
        relativeLayout.addView(textView);

        //进行保存状态的操作
        sp = getSharedPreferences("falg", MODE_PRIVATE);
        edit = sp.edit();

        viewList.add(R.drawable.jdone);
        viewList.add(R.drawable.jdtwo);
        viewList.add(R.drawable.jdthree);
        viewList.add(R.drawable.jdfinaly);
        //进行判断状态
        boolean key = sp.getBoolean("key", false);
        if(key){
            View view = View.inflate(MainActivity.this,R.layout.viewone,null);
            setContentView(view);
            Button button = (Button)view.findViewById(R.id.btn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }else{
            addpoint();
            //viewpager的轮播图
            viewPager.setAdapter(new ViewPagerAdapterFirst(viewList,MainActivity.this));
            //进行轮播图的监听事件
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    switch (position){
                        case 0:break;
                        case 1:break;
                        case 2:break;
                        case 3:
                            //进行倒计时进入跳转页面
                            new Thread(){
                                @Override
                                public void run() {
                                    super.run();
                                    while (djs>0){
                                        try {
                                            djs--;
                                            sleep(1000);
                                            handler.sendEmptyMessage(djs);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }.start();
                            Toast.makeText(MainActivity.this, "我滑到了这里", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                @Override
                public void onPageSelected(int position) {
                    position = position%imageViewList.size();
                    imageViewList.get(lastposition).setImageResource(R.drawable.dot_normal);
                    imageViewList.get(position).setImageResource(R.drawable.dot_focus);
                    lastposition=position;
                }
                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
    }
    public void init(){
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        linearLayout = (LinearLayout)findViewById(R.id.liner);
        relativeLayout = (RelativeLayout)findViewById(R.id.linertwo);
    }
    //添加小点的的方法
    public void addpoint(){
        for(int i = 0 ;i<viewList.size();i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.dot_normal);
            LinearLayout.LayoutParams layoutParams = new
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin=36;
            linearLayout.addView(imageView,layoutParams);
            imageViewList.add(imageView);
        }
    }
}
