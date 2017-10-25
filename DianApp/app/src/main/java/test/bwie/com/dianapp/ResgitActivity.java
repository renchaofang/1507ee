package test.bwie.com.dianapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import test.bwie.com.dianapp.m.UserModle;
import test.bwie.com.dianapp.p.UserPre;
import test.bwie.com.dianapp.v.IUserView;

public class ResgitActivity extends AppCompatActivity implements View.OnClickListener,IUserView{
    private ImageView back;
    private EditText name;
    private EditText pass;
    private Button login;
    private UserPre userpre;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    //注册成功进行销毁当前类
                    ResgitActivity.this.finish();
                    break;
                case 1:
                    Toast.makeText(ResgitActivity.this,"注册失败，傻小子",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgit);
        init();
        //进行绑定P层
        userpre = new UserPre(ResgitActivity.this);
    }
    //初始化资源
    public void init(){
        back = (ImageView)findViewById(R.id.res_tuichu);
        name = (EditText)findViewById(R.id.res_user_name);
        pass = (EditText)findViewById(R.id.res_user_pass);
        login = (Button)findViewById(R.id.res_btn);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.res_btn:
                userpre.getDta(getUserName(),getUserPass());
                break;
        }
    }
    @Override
    public String getUserName() {
        String s = name.getText().toString();
            return s;
    }
    @Override
    public String getUserPass() {
        String s = pass.getText().toString();
            return s;
    }
    @Override
    public void getJudgementName() {
        Toast.makeText(ResgitActivity.this,"请输入为空或者手机号格式不对",Toast.LENGTH_SHORT).show();

    }
    @Override
    public void getJudgementPass() {

        Toast.makeText(ResgitActivity.this,"密码格式输入不正确",Toast.LENGTH_SHORT).show();
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
