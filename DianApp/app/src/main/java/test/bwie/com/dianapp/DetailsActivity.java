package test.bwie.com.dianapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import test.bwie.com.dianapp.bean.ShopDetailsBean;
import test.bwie.com.dianapp.p.UserPre;
import test.bwie.com.dianapp.utils.SPUtil;
import test.bwie.com.dianapp.v.IShopDetails;

public class DetailsActivity extends AppCompatActivity implements IShopDetails,View.OnClickListener{

    private ImageView detail_image;
    private UserPre userPre;
    private TextView price;
    private TextView title;
    private TextView futitle;
    private TextView dianpu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        userPre = new UserPre(DetailsActivity.this);
        Intent intent = getIntent();
        int shop = intent.getIntExtra("shop",0);
        Log.i("xxx",shop+"");
        getDetailsShop(shop);

       //绑定p层进行数据请求


    }
    public void init(){
        detail_image = (ImageView)findViewById(R.id.detail_img);
        price = (TextView)findViewById(R.id.details_price);
        title = (TextView)findViewById(R.id.details_title);
        futitle = (TextView)findViewById(R.id.details_fubiaoti);
        dianpu = (TextView)findViewById(R.id.details_dianpu);
        Button btn = (Button)findViewById(R.id.join_btn);
        btn.setOnClickListener(this);
    }
    @Override
    public void getDetailsShop(int pid) {
        userPre.getDetails(pid);
    }

    @Override
    public void setShopInfo(ShopDetailsBean bean) {
        //进行得到对象数据
        String img = bean.getImg();
        String[] split = img.split("\\|");
        if(split.length>1){
            Picasso.with(DetailsActivity.this).load(split[0]).placeholder(R.mipmap.ic_launcher).into(detail_image);
        }else{
            Picasso.with(DetailsActivity.this).load(img).placeholder(R.mipmap.ic_launcher).into(detail_image);
        }
        dianpu.setText("来自"+ bean.getDianapu());
        price.setText("价格"+bean.getPrice());
        title.setText(bean.getTitle());
        futitle.setText(bean.getFubiaoti());
    }

    @Override
    public void getShoppingCard(String uid, String pid, String sid) {
            userPre.getCard(uid,pid,sid);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.join_btn:
                Toast.makeText(DetailsActivity.this,"点击了加入",Toast.LENGTH_SHORT).show();
                getjoinCard();
                break;
        }
    }
    public void getjoinCard(){
        SPUtil spUtil = new SPUtil(DetailsActivity.this,"key");
        //得到用户id
        int login_id = spUtil.getInt("login_id", 0);
        String uid = String.valueOf(login_id);
        String pid = spUtil.getString("SHOP_ID", null);
        String sellerd_id = spUtil.getString("SELLERD_ID", null);
        Toast.makeText(DetailsActivity.this,uid+"----"+pid+""+sellerd_id,Toast.LENGTH_SHORT).show();
        getShoppingCard(uid,pid,sellerd_id);
    }
}
