package com.example.a1.demostudy;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a1.demostudy.activity.ResgitActivity;
import com.example.a1.demostudy.activity.ShowActivity;
import com.example.a1.demostudy.activity.UserActivity;
import com.example.a1.demostudy.presenter.IUserPresenter;
import com.example.a1.demostudy.presenter.UserPresenter;
import com.example.a1.demostudy.view.IUserLogin;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IUserLogin{

    private EditText name;
    private EditText pass;
    private Button login;
    private Button resgit;
    private UserPresenter user;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           switch (msg.what){
               case 0:
                   startActivity(new Intent(MainActivity.this, UserActivity.class));
                   break;
               case 1:
                   Toast.makeText(MainActivity.this, "登录失败，认真一点好吗", Toast.LENGTH_SHORT).show();
                   break;
        }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化资源控件
        init();
        Api.init(MainActivity.this);
        user = new UserPresenter(MainActivity.this);
    }
    public void init(){
        name = (EditText)findViewById(R.id.name);
        pass = (EditText)findViewById(R.id.pass);
        login = (Button)findViewById(R.id.login);
        resgit = (Button)findViewById(R.id.resgit);
        resgit.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                    //点击的登录进行操作
                user.getLogin(getname(),getpass());
                break;
            case R.id.resgit:
                startActivity(new Intent(this, ResgitActivity.class));
                break;
        }

    }

    @Override
    public String getname() {
        return name.getText().toString();
    }
    @Override
    public String getpass() {

        return pass.getText().toString();
    }

    @Override
    public void ResgitSuccess() {
        handler.sendEmptyMessage(0);
    }
    @Override
    public void ResgitFail() {
        handler.sendEmptyMessage(1);
    }
}
