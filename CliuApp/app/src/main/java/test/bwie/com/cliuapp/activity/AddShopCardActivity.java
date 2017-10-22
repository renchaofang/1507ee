package test.bwie.com.cliuapp.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.cliuapp.Api;
import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.utils.OkHHttpClientUtils;

public class AddShopCardActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView image;
    private TextView back;
    private TextView titel;
    private TextView jinln;
    private TextView price;
    private Button but;
    private View contentView;
    private ImageView imageView;
    private TextView price1;
    private TextView kuncun;
    private TextView size;
    private Button button;
    private String id;
    private String pick;
    private String price11;
    private static int quantity = 1;
    private String name;
    private String jieshao;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String string = (String) msg.obj;
                    Toast.makeText(AddShopCardActivity.this, string, Toast.LENGTH_SHORT).show();
                    try {
                        JSONObject jsonObject = new JSONObject(string);
                        int code = jsonObject.optInt("code");
                        if(code==200){
                            Toast.makeText(AddShopCardActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
                            contentView.setEnabled(false);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    private TextView jianshao1;
    private TextView sum;
    private TextView add;
    //进行获取的购物车的信息
    //key   59bb610e3afbe4bd61b71795a4dbe022
    // goods_id    100008
    // quantity     商品的数量 1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_card);
        Api.init(AddShopCardActivity.this);
        //获取传过来的数据
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pick = intent.getStringExtra("image");
        price11 = intent.getStringExtra("price");
        name = intent.getStringExtra("title");
        jieshao = intent.getStringExtra("jianshao");
        Toast.makeText(this, "商品的ID是："+ id, Toast.LENGTH_SHORT).show();
        init();
        Picasso.with(AddShopCardActivity.this).load(pick).into(image);
        titel.setText(name);
        jinln.setText(jieshao);
        price.setText(price11);
        //进行点击
    }
    //进行初始化资源的控件
    public void init(){
        image = (ImageView)findViewById(R.id.show_shop_image);
        back = (TextView)findViewById(R.id.show_shop_tv);
        titel = (TextView)findViewById(R.id.shop_show_gname);
        jinln = (TextView)findViewById(R.id.shop_show_jianshao);
        price = (TextView)findViewById(R.id.shop_show_price);
        but = (Button)findViewById(R.id.show_shop_btn);
        back.setOnClickListener(this);
        but.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_shop_tv:
                AddShopCardActivity.this.finish();
                break;
            case R.id.show_shop_btn:
                //进行弹选框
                Toast.makeText(this, "点击了谈选矿", Toast.LENGTH_SHORT).show();
                Dialog bottomDialog = new Dialog(AddShopCardActivity.this, R.style.BottomDialog);
                contentView = LayoutInflater.from(AddShopCardActivity.this).inflate(R.layout.tankuang, null);
                bottomDialog.setContentView(contentView);
                ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
                layoutParams.height=950;
                layoutParams.width = getResources().getDisplayMetrics().widthPixels;
                contentView.setLayoutParams(layoutParams);
                bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
                bottomDialog.setCanceledOnTouchOutside(true);
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                bottomDialog.show();
                queren();
                Picasso.with(AddShopCardActivity.this).load(pick).into(imageView);
                price1.setText(price11);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(AddShopCardActivity.this, "点击", Toast.LENGTH_SHORT).show();
                        Toast.makeText(AddShopCardActivity.this, "这里的地址是" + Api.addcard, Toast.LENGTH_SHORT).show();
                        //得到商品的数量
                        String shopsum = sum.getText().toString();
                        //进行请求接口地址，进行请求服务器
                        getData(shopsum);
                    }
                });
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        quantity++;
                        sum.setText(quantity+"");
                    }
                });
                //进行减少数量
                jianshao1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        quantity--;
                        if(quantity<1){
                            Toast.makeText(AddShopCardActivity.this, "亲，不可以为负数", Toast.LENGTH_SHORT).show();
                            quantity=1;
                        }
                        sum.setText(quantity+"");
                    }
                });
                break;
        }
    }
    public void queren(){
        imageView = (ImageView)contentView.findViewById(R.id.ivShow_popShopCart);
        price1 = (TextView)contentView.findViewById(R.id.tvReal_popShopCart);
        kuncun = (TextView)contentView.findViewById(R.id.tvStock_popShopCart);
        size = (TextView)contentView.findViewById(R.id.tvStock_popShopCart);
        button = (Button)contentView.findViewById(R.id.add_btn);
        jianshao1 = (TextView)contentView.findViewById(R.id.iv_sub);
        sum = (TextView)contentView.findViewById(R.id.tv_sum);
        add = (TextView)contentView.findViewById(R.id.iv_add);
    }
    public void getData(String sum){
        Log.i("xxx", "进入了这方法");
        String  keyy = Api.sp.getString("keyy",null);
        Log.i("xxx", "打印key"+keyy);
        Map<String,String> pramars = new HashMap<>();
        //进行拼接接口地址，进行网络请求数据
        // key     goods_id     quantity
        pramars.put("goods_id",id);
        pramars.put("key",keyy);
        pramars.put("quantity",sum);
        OkHHttpClientUtils.dopost(Api.addcard, pramars, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(AddShopCardActivity.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取的得到数据
                String string = response.body().string();
                Message message = new Message();
                message.what=0;
                message.obj=string;
                handler.sendMessage(message);
            }
        });
    }
}
