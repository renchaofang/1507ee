package com.example.a1.demostudy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1.demostudy.Api;
import com.example.a1.demostudy.MainActivity;
import com.example.a1.demostudy.R;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView touxiang;
    private Button back;
    private Button sousuo;
    private TextView login_btn;
    private TextView nichen;
    private TextView username_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();
        //获取登录状态信息
        Api.init(UserActivity.this);
        boolean login = Api.sp.getBoolean("login", false);
        if(login==true){
            //将登录得到的信息赋值给相应的控件
            String username = Api.sp.getString("username", null);
            username_txt.setText(username);
        }else{
         nichen.setText("未登录");
        }

    }
    public void init(){
        username_txt = (TextView)findViewById(R.id.username);
        //登录状态
        nichen = (TextView)findViewById(R.id.nicheng);
        touxiang = (ImageView)findViewById(R.id.user_pick);
        back = (Button)findViewById(R.id.back_login);
        sousuo = (Button)findViewById(R.id.shopping);
        back.setOnClickListener(this);
        sousuo.setOnClickListener(this);
        touxiang.setOnClickListener(this);
        nichen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_login:
                //进行退出登录
                break;
            case R.id.shopping:
                //进行展示商品
                startActivity(new Intent(UserActivity.this,ShowActivity.class));
                break;
            case R.id.nicheng:
                //进行跳转的登录
                startActivity(new Intent(UserActivity.this, MainActivity.class));
                break;
            case R.id.user_pick:
                //进行上传图片
                startActivity(new Intent(UserActivity.this,PickActivity.class));
                break;
        }
    }
}
