package test.bwie.com.cliuapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.cliuapp.Api;
import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.activity.LoginInfoActivity;
import test.bwie.com.cliuapp.activity.DingDantActivity;
import test.bwie.com.cliuapp.adapter.ShoppingCartAdapter;
import test.bwie.com.cliuapp.bean.IDandNumBean;
import test.bwie.com.cliuapp.bean.ShoppingCartBean;
import test.bwie.com.cliuapp.bean.ShowCardBean;
import test.bwie.com.cliuapp.utils.OkHHttpClientUtils;
import test.bwie.com.cliuapp.utils.ToastUtil;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/8/31 23:38
 */
public class Fragment_card extends Fragment implements View.OnClickListener
        , ShoppingCartAdapter.CheckInterface, ShoppingCartAdapter.ModifyCountInterface  {
    private static final String TAG = "ShoppingCartActivity";
    Button btnBack;
    //全选
    CheckBox ckAll;
    //总额
    TextView tvShowPrice;
    //结算
    TextView tvSettlement;
    //编辑
    TextView btnEdit;//tv_edit
    private int count = 0;
    ListView list_shopping_cart;
    private ShoppingCartAdapter shoppingCartAdapter;
    private boolean flag = false;
    private List<ShoppingCartBean> shoppingCartBeanList = new ArrayList<>();
    private boolean mSelect;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private List<ShowCardBean.DatasBean.CartListBean.GoodsBean> goods;
    private View view;
    private LinearLayout linearLayout;
    private Button button;
    private Handler handler = new Handler(){



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String string = (String) msg.obj;
                    Log.i("xxx", string);
                    Gson gson = new Gson();
                    //进行解析字符串，获取的里面数据的集合对象，进行展示数据
                    ShowCardBean showCardBean = gson.fromJson(string, ShowCardBean.class);
                    ShowCardBean.DatasBean datas = showCardBean.getDatas();
                    List<ShowCardBean.DatasBean.CartListBean> cart_list = datas.getCart_list();
                    for (ShowCardBean.DatasBean.CartListBean list :cart_list){
                        goods = list.getGoods();
                        Log.i("xxx", "购物车的长度" + goods.size());
                        //进行遍历集合
                        for (int i = 0 ; i<goods.size();i++){
                            //获取的到数据进行添加购物的信息对象

                            goods.get(i).getCart_id();

                            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
                            shoppingCartBean.setId(Integer.parseInt(goods.get(i).getCart_id()));
                            shoppingCartBean.setShoppingName(goods.get(i).getGoods_name());
                            shoppingCartBean.setAttribute("蓝色");
                            shoppingCartBean.setCount(Integer.parseInt(goods.get(i).getGoods_num()));
                            shoppingCartBean.setImageUrl(goods.get(i).getGoods_image_url());
                            shoppingCartBean.setPrice(Double.parseDouble(goods.get(i).getGoods_price()));
                            //添加到集合里面去
                            Log.i("xxx","集合里面的数据："+ shoppingCartBean.toString());
                            shoppingCartBeanList.add(shoppingCartBean);
                        }
                        /*shoppingCartBean.setShoppingName("瑞士正品夜光男女士手表情侣精钢带男表防水石英学生非天王星机械");
                        shoppingCartBean.setAttribute("黑白色");
                        shoppingCartBean.setPrice(89);
                        shoppingCartBean.setId(i+2);
                        shoppingCartBean.setCount(3);
                        shoppingCartBean.setImageUrl("https://gd1.alicdn.com/imgextra/i1/*/
                    }
                    list_shopping_cart.setAdapter(shoppingCartAdapter);
                    Log.i("xxx", "shoppingCartBeanList.size():" + shoppingCartBeanList.size());
                    shoppingCartAdapter.setShoppingCartBeanList(shoppingCartBeanList);
                    break;
            }
        }
    };
    private String keyy;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.fragment_card,container,false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }
        initView();
        button = (Button)view.findViewById(R.id.suibian);
        linearLayout = (LinearLayout)view.findViewById(R.id.gow);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Api.init(getActivity());
        boolean login = Api.sp.getBoolean("login", false);
        keyy = Api.sp.getString("keyy", null);
        Toast.makeText(getActivity(), "查看购物车的key"+keyy, Toast.LENGTH_SHORT).show();
        if(login==true){
            initData();
            ImageLoader imageLoader= ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        }else{
            button.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
            //点击button进行页面跳转
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进行跳转的登录界面
                    Intent intent = new Intent(getActivity(), LoginInfoActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
    private void initView() {
        btnBack= (Button)view.findViewById(R.id.btn_back);
        ckAll= (CheckBox)view.findViewById(R.id.ck_all);
        tvShowPrice= (TextView)view.findViewById(R.id.tv_show_price);
        tvSettlement= (TextView) view.findViewById(R.id.tv_settlement);
        btnEdit= (TextView) view.findViewById(R.id.bt_header_right);
        list_shopping_cart= (ListView) view.findViewById(R.id.list_shopping_cart);
        btnEdit.setOnClickListener(this);
        ckAll.setOnClickListener(this);
        tvSettlement.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }
    //初始化数据
    public void getdata(){
        Toast.makeText(getActivity(), "购物车列表的位置"+Api.showCard, Toast.LENGTH_SHORT).show();
        //进行请求网络请求数据
        HashMap<String,String> prames = new HashMap<>();
        prames.put("key",keyy);
        OkHHttpClientUtils.dopost(Api.showCard, prames, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = new Message();
                message.what=0;
                message.obj=string;
                handler.sendMessage(message);
            }
        });
    }
    protected void initData() {
                 getdata();
      /*  for (int i = 0; i < 2; i++) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
            shoppingCartBean.setShoppingName("上档次的T桖");
            shoppingCartBean.setDressSize(20);
            shoppingCartBean.setId(i);
            shoppingCartBean.setPrice(30.6);
            shoppingCartBean.setCount(1);
            shoppingCartBean.setImageUrl("https://img.alicdn.com/bao/uploaded/i2/TB1YfERKVXXXXanaFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg");
            shoppingCartBeanList.add(shoppingCartBean);
        }
        for (int i = 0; i < 2; i++) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
            shoppingCartBean.setShoppingName("瑞士正品夜光男女士手表情侣精钢带男表防水石英学生非天王星机械");
            shoppingCartBean.setAttribute("黑白色");
            shoppingCartBean.setPrice(89);
            shoppingCartBean.setId(i+2);
            shoppingCartBean.setCount(3);
            shoppingCartBean.setImageUrl("https://gd1.alicdn.com/imgextra/i1/2160089910/TB2M_NSbB0kpuFjSsppXXcGTXXa_!!2160089910.jpg");
            shoppingCartBeanList.add(shoppingCartBean);
        }*/
        shoppingCartAdapter = new ShoppingCartAdapter(getActivity());
        shoppingCartAdapter.setCheckInterface(this);
        shoppingCartAdapter.setModifyCountInterface(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //全选按钮
            case R.id.ck_all:
                if (shoppingCartBeanList.size() != 0) {
                    if (ckAll.isChecked()) {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(true);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(false);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    }
                }
                statistics();
                break;
            case R.id.bt_header_right:
                flag = !flag;
                if (flag) {
                    btnEdit.setText("完成");
                    shoppingCartAdapter.isShow(false);
                } else {
                    btnEdit.setText("编辑");
                    shoppingCartAdapter.isShow(true);
                }
                break;
            case R.id.tv_settlement: //结算
                lementOnder();
                break;
            case R.id.btn_back:
                break;
        }
    }

    /**
     * 结算订单、支付
     */
    private void lementOnder() {
    ArrayList<IDandNumBean> shangpinlist = new ArrayList<>();
        //选中的需要提交的商品清单
        for (ShoppingCartBean bean:shoppingCartBeanList ){
            boolean choosed = bean.isChoosed();
            if (choosed){
                String shoppingName = bean.getShoppingName();
                int count = bean.getCount();
                double price = bean.getPrice();
                int size = bean.getDressSize();
                String attribute = bean.getAttribute();
                int id = bean.getId();
                IDandNumBean iDandNumBean = new IDandNumBean(id, count);
                shangpinlist.add(iDandNumBean);
                Log.i("ppp",id+"----id---"+shoppingName+"---"+count+"---"+price+"--size----"+size+"--attr---"+attribute);
            }
        }
        //跳转到订界面
        Intent intent = new Intent(getActivity(), DingDantActivity.class);
        intent.putExtra("key",shangpinlist);
        startActivity(intent);
        ToastUtil.showL(getActivity(),"总价："+totalPrice);


    }
    /**
     * 单选
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {
        shoppingCartBeanList.get(position).setChoosed(isChecked);
        if (isAllCheck())
            ckAll.setChecked(true);
        else
            ckAll.setChecked(false);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }
    /**
     * 遍历list集合
     * @return
     */
    private boolean isAllCheck() {

        for (ShoppingCartBean group : shoppingCartBeanList) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }
    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
            ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(i);
            if (shoppingCartBean.isChoosed()) {
                totalCount++;
                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getCount();
            }
        }
        tvShowPrice.setText("合计:" + totalPrice);
        tvSettlement.setText("结算(" + totalCount + ")");
    }
    /**
     * 增加
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        currentCount++;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }
    /**
     * 删减
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }
    /**
     * 删除
     *
     * @param position
     */
    @Override
    public void childDelete(int position) {
        shoppingCartBeanList.remove(position);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }


}
