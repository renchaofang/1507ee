
package test.bwie.com.cliuapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.mvp_persenter.LoginPerenter;
import test.bwie.com.cliuapp.mvp_view.IUserNameLogin;

public class LoginInfoActivity extends AppCompatActivity implements View.OnClickListener,IUserNameLogin{

    private ImageView imageView;
    private EditText name;
    private EditText password;
    private Button button;
    private TextView phone_btn;
    private TextView rember_btn;
   private LoginPerenter loginPerenter;
    //进行开启线程
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    //保存登录状态
                  //跳转到主页面
                    Intent intent = new Intent(LoginInfoActivity.this, ShowActivity.class);
                    startActivity(intent);
                case 1:
                    Toast.makeText(LoginInfoActivity.this, "登录失败，认真一点好吗", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_info);
        sp = getSharedPreferences("falg", MODE_PRIVATE);
        edit = sp.edit();
        init();
        loginButton();
        loginPerenter = new LoginPerenter(LoginInfoActivity.this);
    }
    //进行文本的监听事件，改变按钮操作
    private void loginButton() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(password.getText())) {
                    button.setEnabled(Boolean.FALSE);
                    Toast.makeText(LoginInfoActivity.this, "按钮不可点击", Toast.LENGTH_SHORT).show();
                } else {
                    button.setEnabled(Boolean.TRUE);
                    Toast.makeText(LoginInfoActivity.this, "按钮可点击", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(password.getText())) {
                    button.setEnabled(Boolean.FALSE);
                    Toast.makeText(LoginInfoActivity.this, "按钮不可点击", Toast.LENGTH_SHORT).show();
                } else {
                    button.setEnabled(Boolean.TRUE);
                    Toast.makeText(LoginInfoActivity.this, "按钮可点击", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

    }
    //进行初始化控件
    public void init(){
        imageView = (ImageView)findViewById(R.id.user_back);
        name = (EditText)findViewById(R.id.user_info_name);
        password =  (EditText)findViewById(R.id.user_info_pass);
        button = (Button)findViewById(R.id.user_btn_userinfo);
        phone_btn = (TextView)findViewById(R.id.phone_user_resgit);
        rember_btn = (TextView)findViewById(R.id.rember_user_resgit);
        //设置点击事件的监听事件
        imageView.setOnClickListener(this);
        name.setOnClickListener(this);
        password.setOnClickListener(this);
        button.setOnClickListener(this);
        phone_btn.setOnClickListener(this);
        rember_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_back:
                //返回
                finish();
                break;
            case R.id.user_btn_userinfo:
                Toast.makeText(LoginInfoActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                loginPerenter.loginData(gname(),gpassword(),this);
                //进行登录
                break;
            case R.id.phone_user_resgit:
                //跳转到注册的界面
                Intent intent = new Intent(LoginInfoActivity.this, ResgiterActivity.class);
                startActivity(intent);
                break;
            case R.id.rember_user_resgit:
                break;
        }
    }
    @Override
    public String gname() {
        return name.getText().toString().trim();
    }

    @Override
    public String gpassword() {
        return password.getText().toString().trim();
    }

    @Override
    public void LoginSuccess() {
        handler.sendEmptyMessage(0);
    }

    @Override
    public void LoginFail() {
        handler.sendEmptyMessage(1);
    }
}
