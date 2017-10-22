package test.bwie.com.cliuapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import test.bwie.com.cliuapp.R;

public class PaymentListActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout weixin;
    private LinearLayout zhifubao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_list);
        init();


    }

    public void init(){
        weixin = (LinearLayout)findViewById(R.id.weixin);
        zhifubao = (LinearLayout)findViewById(R.id.zhifubao);
        weixin.setOnClickListener(this);
        zhifubao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.weixin:
                Toast.makeText(this, "微信支付", Toast.LENGTH_SHORT).show();
                //进行处理支付操作
                break;
            case R.id.zhifubao:
                Toast.makeText(this, "支付宝支付", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
