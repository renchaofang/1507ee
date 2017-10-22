package test.bwie.com.cliuapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.cliuapp.Api;
import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.activity.AddShopCardActivity;
import test.bwie.com.cliuapp.adapter.FragamentRightAdapter;
import test.bwie.com.cliuapp.bean.ShopInfoBean;
import test.bwie.com.cliuapp.utils.OkHHttpClientUtils;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/8 14:49
 */
public class FragmentRight extends Fragment{
    private View view;
    private Handler handler = new Handler(){

        private List<ShopInfoBean.DatasBean.GoodsListBean> goods_list;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String string = (String) msg.obj;
                    //进行性解析字符串
                    Gson gson = new Gson();
                    ShopInfoBean shoppingBean = gson.fromJson(string, ShopInfoBean.class);
                    ShopInfoBean.DatasBean datas = shoppingBean.getDatas();
                    goods_list = datas.getGoods_list();
                    dataAdapterr(goods_list);
                    fragamentRightAdapter.setOnRecycleitemListenter(new FragamentRightAdapter.OnRecycleitemListenter() {
                        @Override
                        public void onRecycleitemclick(int position) {
                            Intent intent = new Intent(getActivity(), AddShopCardActivity.class);
                            intent.putExtra("id",goods_list.get(position).getGoods_id());
                            intent.putExtra("image",goods_list.get(position).getGoods_image_url());
                            intent.putExtra("title",goods_list.get(position).getGoods_name());
                            intent.putExtra("jianshao",goods_list.get(position).getGoods_jingle());
                            intent.putExtra("price",goods_list.get(position).getGoods_price());
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };
    private FragamentRightAdapter fragamentRightAdapter;
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragmentright,container,false);
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
        initview();
        pullLoadMoreRecyclerView.setGridLayout(2);
        getdata();
    }
    //进行拼接接口，最后进行网络请求数据
    public void getdata(){
        //进行请求网络数据
        OkHHttpClientUtils.doget(Api.shopinfo, new Callback() {
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
    //适配器进行适配数据
    public void dataAdapterr(List<ShopInfoBean.DatasBean.GoodsListBean> list){
        if(fragamentRightAdapter==null){
            fragamentRightAdapter = new FragamentRightAdapter(list, getActivity());
            pullLoadMoreRecyclerView.setAdapter(fragamentRightAdapter);
        }else{
            fragamentRightAdapter.notifyDataSetChanged();
        }
    }
    public void initview(){
        pullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.shop_info_pullview);
    }
}

