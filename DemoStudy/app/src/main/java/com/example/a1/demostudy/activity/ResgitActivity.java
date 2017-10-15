package com.example.a1.demostudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a1.demostudy.R;
import com.example.a1.demostudy.presenter.UserPresenter;
import com.example.a1.demostudy.view.IUserView;

public class ResgitActivity extends AppCompatActivity implements View.OnClickListener,IUserView {
    private EditText res_name;
    private EditText res_pass;
    private Button resgit_btn;
    private UserPresenter userPresenter;
    //进行开启线程
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    //保存登录状态
                    //跳转到主页面
                    finish();
                case 1:
                    Toast.makeText(ResgitActivity.this, "登录失败，认真一点好吗", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgit);
        userPresenter = new UserPresenter(ResgitActivity.this);
        init();
    }
    public void init(){
        res_name = (EditText)findViewById(R.id.resgit_name);
        res_pass = (EditText)findViewById(R.id.resgit_pass);
        resgit_btn = (Button)findViewById(R.id.regit_btn);
        resgit_btn.setOnClickListener(this);
    }
    @Override
    public String getname() {
        String name = res_name.getText().toString();
        return name;
    }
    @Override
    public String getpass() {
        String pass = res_pass.getText().toString();
        return pass;
    }

    @Override
    public void ResgitSuccess() {
        handler.sendEmptyMessage(0);
    }

    @Override
    public void ResgitFail() {
        handler.sendEmptyMessage(1);
    }
    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.regit_btn:
                    userPresenter.getData(getname(),getpass());
                    break;
            }
    }
}
