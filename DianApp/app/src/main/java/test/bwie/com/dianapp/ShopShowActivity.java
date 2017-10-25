package test.bwie.com.dianapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;

import java.util.List;

import test.bwie.com.dianapp.adapter.RecycleAdapter;
import test.bwie.com.dianapp.bean.ShowInfoBean;
import test.bwie.com.dianapp.p.UserPre;
import test.bwie.com.dianapp.v.IShopInfo;
import test.bwie.com.recyclelibrary.view.MyDecoration;

public class ShopShowActivity extends AppCompatActivity implements View.OnClickListener,IShopInfo{

    private Handler handler = new Handler();
    private TextView sou;
    private ImageView back;
    private EditText shop;
    private RecyclerView recyclerView;
    private SwipyRefreshLayout srl;
    private int page = 1;
    private UserPre userPre;
    private CheckBox checkBox;
    private RecycleAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_show);
        init();
        //利用MVP框架进行请求数据
        userPre = new UserPre(ShopShowActivity.this);
        srl.setDirection(SwipyRefreshLayoutDirection.BOTH);
        srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                page = 1;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        qingqiu();
                        srl.setRefreshing(false);
                    }
                }, 2000);
            }
            @Override
            public void onLoad(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.i("iii",page+"");
                        qingqiu();
                        srl.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ShopShowActivity.this,2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                }else{
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopShowActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
            }
        });

    }
    public void init(){
        sou = (TextView)findViewById(R.id.item_sou);
        shop = (EditText)findViewById(R.id.item_shop);
        back = (ImageView)findViewById(R.id.item_back);
        recyclerView = (RecyclerView)findViewById(R.id.recycle);
        srl = (SwipyRefreshLayout)findViewById(R.id.srl);
        checkBox = (CheckBox)findViewById(R.id.check);
        sou.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_sou:
                Toast.makeText(ShopShowActivity.this,"点击了",Toast.LENGTH_SHORT).show();
                //添加华丽分割线
                MyDecoration decoration = new MyDecoration(this, LinearLayoutManager.VERTICAL);
                recyclerView.addItemDecoration(decoration);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopShowActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                Log.i("aaa",getTile());
                qingqiu();
                break;
            case R.id.item_back:
                ShopShowActivity.this.finish();
                break;
        }
    }
    @Override
    public String getTile() {
        return  shop.getText().toString();
    }
    @Override
    public void getRecycle(final List<ShowInfoBean> list) {
        recycleAdapter = new RecycleAdapter(list,ShopShowActivity.this);
        recyclerView.setAdapter(recycleAdapter);
        recycleAdapter.setOnRecycleitemListenter(new RecycleAdapter.OnRecycleitemListenter() {
            @Override
            public void onRecycleitemclick(int position) {
                Toast.makeText(ShopShowActivity.this,"点击了"+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShopShowActivity.this, DetailsActivity.class);
                intent.putExtra("shop",list.get(position).getPid());
                startActivity(intent);
            }
        });
    }
    //进行得到请求的数据
    public void qingqiu(){
        Log.i("aaa",getTile());
        userPre.getShopInfo(getTile(),page);
    }
}
