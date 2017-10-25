package test.bwie.com.dianapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.dianapp.Api;
import test.bwie.com.dianapp.R;
import test.bwie.com.dianapp.adapter.KindRecycleAdapter;
import test.bwie.com.dianapp.adapter.RecycleAdapter;
import test.bwie.com.dianapp.bean.FirstKndBean;
import test.bwie.com.okhttoutils_rcf.Utils.OkHttp3Utils;
import test.bwie.com.recyclelibrary.view.MyDecoration;

/**
 * Created by 1 on 2017/10/17.
 */

public class FragmentFenLei extends Fragment{

    private List<FirstKndBean> list = new ArrayList<>();
    private View view;
    private RecyclerView recyclerView;
    private Handler handler = new Handler();
    private KindRecycleAdapter kindRecycleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.fenlei,container,false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }
        init();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();

    }
    public void add(final List<FirstKndBean> list){
        Log.i("qqq",list.size()+"");
        //添加华丽分割线
        MyDecoration decoration = new MyDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        kindRecycleAdapter = new KindRecycleAdapter(list, getActivity());
        recyclerView.setAdapter(kindRecycleAdapter);

        //创建MyFragment对象
        RightClassFragment myFragment = new RightClassFragment();
        FragmentTransaction fragmentTransaction = getChildFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.shop_fragment, myFragment);
        //通过bundle传值给MyFragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("fragment", (Serializable) list.get(0));
        myFragment.setArguments(bundle);
        fragmentTransaction.commit();


        kindRecycleAdapter.setOnRecycleitemListenter(new RecycleAdapter.OnRecycleitemListenter() {
            @Override
            public void onRecycleitemclick(int position) {
                Toast.makeText(getActivity(),"点击了"+list.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                //创建MyFragment对象
                RightClassFragment myFragment = new RightClassFragment();
                FragmentTransaction fragmentTransaction = getChildFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.shop_fragment, myFragment);
                //通过bundle传值给MyFragment
                Bundle bundle = new Bundle();
                bundle.putSerializable("fragment", (Serializable) list.get(position));
                myFragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });
    }
    public void getData(){
        OkHttp3Utils.doGet(Api.ShopFirst, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i("xxx",string);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            JSONArray data = jsonObject.getJSONArray("data");
                            for(int i = 0 ; i<data.length();i++){
                                FirstKndBean firstKndBean = new FirstKndBean();
                                JSONObject bean = data.getJSONObject(i);
                                String name = bean.getString("name");
                                int cid = bean.getInt("cid");
                                int ishome = bean.getInt("ishome");
                                if(ishome==1){
                                    //添加集合
                                    firstKndBean.setTitle(name);
                                    firstKndBean.setCid(cid);
                                    list.add(firstKndBean);
                                }
                                Log.i("aaa",list.size()+"");
                                add(list);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    public void init(){
        recyclerView = (RecyclerView)view.findViewById(R.id.view);
    }

}
