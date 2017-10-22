package test.bwie.com.cliuapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.mvp_persenter.ResgterPerenter;
import test.bwie.com.cliuapp.mvp_view.IUserNameView;

public class ResgiterActivity extends AppCompatActivity implements IUserNameView{
    private EditText name;
    private EditText password;
    private EditText agast_password;
    private EditText email;
    private TextView rsgit;
    private ResgterPerenter resgterPerenter;
    //开启线程
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Toast.makeText(ResgiterActivity.this, "老兄，记住注册，不要急，慢慢来", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    ResgiterActivity.this.finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgiter);
        resgterPerenter = new ResgterPerenter(ResgiterActivity.this);
        initview();
        rsgit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行调用方法传值
                resgterPerenter.sendData(getname(),getpassword(),getemail(),ResgiterActivity.this);
            }
        });
    }
    public void initview(){
        name = (EditText)findViewById(R.id.resgiter_name);
        password = (EditText)findViewById(R.id.resgiter_password);
        agast_password = (EditText)findViewById(R.id.resgiter_angit_pass);
        email = (EditText)findViewById(R.id.resgiter_email);
        rsgit = (TextView) findViewById(R.id.reg_reg);
    }
    @Override
    public String getname() {
        return name.getText().toString().trim();
    }
    @Override
    public String getpassword() {
        return password.getText().toString().trim();
    }
    @Override
    public String getemail() {
        return email.getText().toString().trim();
    }
    //成功
    @Override
    public void ResgitSuccess() {
        //退回到登录的界面
        handler.sendEmptyMessage(1);
    }
    //失败
    @Override
    public void ResgitFail() {
        handler.sendEmptyMessage(0);
    }
}
