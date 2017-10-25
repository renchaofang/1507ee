package test.bwie.com.dianapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import test.bwie.com.dianapp.p.IUserPre;
import test.bwie.com.dianapp.p.UserPre;
import test.bwie.com.dianapp.v.IUserLoginView;
import test.bwie.com.dianapp.v.IUserView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,IUserLoginView {

    private ImageView back;
    private EditText name;
    private EditText pass;
    private Button login;
    private TextView resgit;
    private IUserPre iUserPre;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                   startActivity(new Intent(LoginActivity.this,ShowActivity.class));
                    break;
                case 1:
                    Toast.makeText(LoginActivity.this,"真是个傻孩子哟，错啦",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    //初始化资源
    public void init(){
        back = (ImageView)findViewById(R.id.tuichu);
        name = (EditText)findViewById(R.id.user_name);
        pass = (EditText)findViewById(R.id.user_pass);
        login = (Button)findViewById(R.id.btn);
        resgit = (TextView)findViewById(R.id.user_resgit);
        resgit.setOnClickListener(this);
        login.setOnClickListener(this);
        iUserPre = new UserPre(LoginActivity.this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_resgit:
                startActivity(new Intent(LoginActivity.this,ResgitActivity.class));
                break;
            case R.id.btn:
                //进行登录操作
            iUserPre.getLoginUser(getLoginName(),getLoginPass());
                break;
        }
    }
    @Override
    public String getLoginName() {
        return name.getText().toString();
    }
    @Override
    public String getLoginPass() {
        return pass.getText().toString();
    }
    @Override
    public void getJudgementName() {
        Toast.makeText(LoginActivity.this,"请输入为空或者手机号格式不对",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void getJudgementPass() {
        Toast.makeText(LoginActivity.this,"密码格式输入不正确",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSuccess() {
            handler.sendEmptyMessage(0);
    }
    @Override
    public void getFail() {
        handler.sendEmptyMessage(1);
    }
}
