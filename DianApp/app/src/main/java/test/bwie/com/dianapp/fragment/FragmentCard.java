package test.bwie.com.dianapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import test.bwie.com.dianapp.R;
import test.bwie.com.dianapp.ZhiFuActivity;
import test.bwie.com.dianapp.adapter.ShopcartAdapter;
import test.bwie.com.dianapp.bean.GoodsInfo;
import test.bwie.com.dianapp.bean.StoreInfo;
import test.bwie.com.dianapp.p.UserPre;
import test.bwie.com.dianapp.utils.SPUtil;
import test.bwie.com.dianapp.v.IFindShop;

/**
 * Created by 1 on 2017/10/17.
 */

public class FragmentCard extends Fragment implements ShopcartAdapter.CheckInterface,
        ShopcartAdapter.ModifyCountInterface, ShopcartAdapter.GroupEdtorListener,IFindShop{
    @InjectView(R.id.back)
    ImageView back;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.subtitle)
    TextView subtitle;
    @InjectView(R.id.top_bar)
    LinearLayout topBar;
    @InjectView(R.id.exListView)
    ExpandableListView exListView;
    @InjectView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @InjectView(R.id.all_chekbox)
    CheckBox allChekbox;
    @InjectView(R.id.tv_delete)
    TextView tvDelete;
    @InjectView(R.id.tv_go_to_pay)
    TextView tvGoToPay;

    @InjectView(R.id.ll_shar)
    LinearLayout llShar;
    @InjectView(R.id.ll_info)
    LinearLayout llInfo;
    @InjectView(R.id.tv_share)
    TextView tvShare;
    @InjectView(R.id.tv_save)
    TextView tvSave;
    private Context context;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private ShopcartAdapter selva;
    private List<StoreInfo> groups = new ArrayList<StoreInfo>();// 组元素数据列表
    private Map<String, List<GoodsInfo>> children = new HashMap<String, List<GoodsInfo>>();// 子元素数据列表
    private int flag = 0;
    private View view;
    private UserPre userPre;
    private boolean is_login;
    private int login_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.shopcard,container,false);
            ButterKnife.inject(this,view);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SPUtil key = new SPUtil(getActivity(), "key");
        //登录状态以及登录用户的id
        is_login = key.getBoolean("IS_LOGIN", false);
        login_id = key.getInt("login_id", 0);
        userPre = new UserPre(this);
        context = getActivity();
        getData();

    }

    private void initEvents() {
        selva = new ShopcartAdapter(groups, children, getActivity());
        selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
        selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        selva.setmListener(this);
        exListView.setAdapter(selva);
        for (int i = 0; i < selva.getGroupCount(); i++) {
            exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        setCartNum();
    }

    /**
     * 设置购物车产品数量
     */
    private void setCartNum() {
        int count=0;
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(allChekbox.isChecked());
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (GoodsInfo goodsInfo:childs){
                count+=1;
            }
        }
        title.setText("购物车" + "(" + count + ")");
    }

    /**
     * 模拟数据<br>
     * 遵循适配器的数据列表填充原则，组元素被放在一个List中，对应的组元素下辖的子元素被放在Map中，<br>
     * 其键是组元素的Id(通常是一个唯一指定组元素身份的值)
     */
    private void initDatas(JSONArray jsonArray) {

        Log.i("ooo","商家的集合："+jsonArray.length()+"");
            try {
                for(int i = 0 ; i<jsonArray.length();i++){
                    JSONObject datalist = jsonArray.getJSONObject(i);
                    //得到商家信息
                    String sellerName = datalist.getString("sellerName");
                    //进行添加将集合组名
                    groups.add(new StoreInfo(i + "",sellerName));
                    List<GoodsInfo> products = new ArrayList<GoodsInfo>();
                    JSONArray list = datalist.getJSONArray("list");
                    Log.i("ooo","商品的集合："+list.length()+"");
                for(int j=0;j<list.length();j++){
                    //ShowInfoBean showInfoBean = new ShowInfoBean();
                    JSONObject shoplist = list.getJSONObject(j);
                   /* showInfoBean.setCount(shoplist.getInt("num"));
                    showInfoBean.setImage(shoplist.getString("images"));
                    showInfoBean.setPrice(shoplist.getInt("price"));
                    showInfoBean.setTitle(shoplist.getString("title"));
                    showInfoBean.setSubhead(shoplist.getString("subhead"));*/
                    products.add(new GoodsInfo(j + "", "商品",shoplist.getString("title"),shoplist.getInt("price"),shoplist.getInt("num"), "豪华", "1", shoplist.getString("images"),6.00+ new Random().nextInt(13)));
                }
                    children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    /**
     * 删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    protected void doDelete() {
        List<StoreInfo> toBeDeleteGroups = new ArrayList<StoreInfo>();// 待删除的组元素列表
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
            List<GoodsInfo> toBeDeleteProducts = new ArrayList<GoodsInfo>();// 待删除的子元素列表
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoosed()) {
                    toBeDeleteProducts.add(childs.get(j));
                }
            }
            childs.removeAll(toBeDeleteProducts);
        }
        groups.removeAll(toBeDeleteGroups);
        selva.notifyDataSetChanged();
        calculate();
    }
    @Override
    public void doIncrease(int groupPosition, int childPosition,
                           View showCountView, boolean isChecked) {
        GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition,
                childPosition);
        int currentCount = product.getCount();
        currentCount++;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        selva.notifyDataSetChanged();
        calculate();
    }
    @Override
    public void doDecrease(int groupPosition, int childPosition,
                           View showCountView, boolean isChecked) {

        GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition,
                childPosition);
        int currentCount = product.getCount();
        if (currentCount == 1)
            return;
        currentCount--;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        selva.notifyDataSetChanged();
        calculate();
    }
    @Override
    public void childDelete(int groupPosition, int childPosition) {
        children.get(groups.get(groupPosition).getId()).remove(childPosition);
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        if (childs.size() == 0) {
            groups.remove(groupPosition);
        }
        selva.notifyDataSetChanged();
        handler.sendEmptyMessage(0);
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            allChekbox.setChecked(true);
        else
            allChekbox.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosiTion,
                           boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            // 不全选中
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        //获取店铺选中商品的总金额
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck()) {
            allChekbox.setChecked(true);// 全选
        } else {
            allChekbox.setChecked(false);// 反选
        }
        selva.notifyDataSetChanged();
        calculate();

    }

    private boolean isAllCheck() {

        for (StoreInfo group : groups) {
            if (!group.isChoosed())
                return false;

        }

        return true;
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(allChekbox.isChecked());
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(allChekbox.isChecked());
            }
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                GoodsInfo product = childs.get(j);
                if (product.isChoosed()) {
                    totalCount++;
                    totalPrice += product.getPrice() * product.getCount();
                }
            }


        }
        tvTotalPrice.setText("￥" + totalPrice);
        tvGoToPay.setText("去支付(" + totalCount + ")");
    }

    @OnClick({R.id.all_chekbox, R.id.tv_delete, R.id.tv_go_to_pay, R.id.subtitle, R.id.tv_save, R.id.tv_share})
    public void onClick(View view) {
        AlertDialog alert;
        switch (view.getId()) {
            case R.id.all_chekbox:
                doCheckAll();
                break;
            case R.id.tv_delete:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要移除的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                doDelete();
                            }
                        });
                alert.show();
                break;
            case R.id.tv_go_to_pay:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要支付的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), ZhiFuActivity.class);
                String price = String.valueOf(this.totalPrice);
                String id = String.valueOf(this.login_id);
                intent.putExtra("price", price);
                intent.putExtra("uid",id);
                Toast.makeText(getActivity(), this.totalPrice +"...."+ this.login_id,Toast.LENGTH_SHORT).show();
                startActivity(intent);

               /* alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("总计:\n" + totalCount + "种商品\n" + totalPrice + "元");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.show();*/
                break;
            case R.id.subtitle:
                if (flag == 0) {
                    llInfo.setVisibility(View.GONE);
                    tvGoToPay.setVisibility(View.GONE);
                    llShar.setVisibility(View.VISIBLE);
                    subtitle.setText("完成");
                } else if (flag == 1) {
                    llInfo.setVisibility(View.VISIBLE);
                    tvGoToPay.setVisibility(View.VISIBLE);
                    llShar.setVisibility(View.GONE);
                    subtitle.setText("编辑");
                }
                flag = (flag + 1) % 2;//其余得到循环执行上面2个不同的功能
                break;
            case R.id.tv_share:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要分享的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getActivity(), "分享成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_save:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要保存的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void groupEdit(int groupPosition) {
        groups.get(groupPosition).setIsEdtor(true);
        selva.notifyDataSetChanged();
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //删除购物车后动态改变数量
            setCartNum();
        }
    };

    @Override
    public void getFindShop(String uid) {
        userPre.findcard(uid);
    }

    @Override
    public void getAddCard(JSONArray jsonArray) {
        initDatas(jsonArray);
        initEvents();
    }
    //两个没有用的方法
    @Override
    public void getOrderInfo(String uid, String price) {

    }
    @Override
    public void FindOrder(String uid, int tid) {
    }

    @Override
    public void WeiXIN(String p_id) {

    }

    @Override
    public void ZhiFuBao(String p_id) {

    }

    //进行请求的得到购物车里面的数据
    public void getData(){
        //得到用户的ID以及登录状态
        if(is_login==true){
            getFindShop(String.valueOf(login_id));
        }else{
            Toast.makeText(getActivity(),"请登录",Toast.LENGTH_SHORT).show();
        }
    }
}
