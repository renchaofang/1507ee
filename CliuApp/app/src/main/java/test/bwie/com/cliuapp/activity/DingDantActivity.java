package test.bwie.com.cliuapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.cliuapp.Api;
import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.bean.IDandNumBean;
import test.bwie.com.cliuapp.utils.OkHHttpClientUtils;

public class DingDantActivity extends AppCompatActivity implements View.OnClickListener{
    private String card_id = null;
    private String keyy;
    private List<IDandNumBean> key;
    private Handler hanaler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String string = (String) msg.obj;
                    Log.i("xxxxx", string);
                    //进行解析字符串

                    break;
            }
        }
    };
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        init();
        Api.init(this);
        keyy = Api.sp.getString("keyy",null);
        Intent intent = getIntent();
        key = (List<IDandNumBean>) intent.getSerializableExtra("key");
        getdata();
    }
    public void getdata(){

        for(int i = 0; i< key.size(); i++){
            //打印价格
            Log.i("xxxx", key.get(i).getId()+""+ key.get(i).getNum());
            if(i==0){
                card_id = key.get(i).getId()+"|"+key.get(i).getNum();
            }else{
                card_id += ","+key.get(i).getId()+"|"+key.get(i).getNum();
            }
        }
        //将得到数据数据进行接口拼接请求数据
        HashMap<String,String> map = new HashMap<>();
        map.put("key",keyy);
        Log.i("qqq", keyy);
        map.put("cart_id",card_id);
        map.put("ifcart","1");
        Log.i("rrr",card_id);
        OkHHttpClientUtils.dopost(Api.shopjiesuan, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i("qqq", string);
                Message message = new Message();
                message.what=0;
                message.obj=string;
                hanaler.sendMessage(message);
            }
        });
    }
    public void init(){
        button = (Button)findViewById(R.id.tijiao);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tijiao:
                Intent intent = new Intent(DingDantActivity.this,PaymentListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
