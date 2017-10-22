package test.bwie.com.cliuapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.cliuapp.Api;
import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.adapter.FragmentLeftAdapter;
import test.bwie.com.cliuapp.utils.OkHHttpClientUtils;
import test.bwie.com.recyclelibrary.view.MyDecoration;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/8 14:49
 */
public class FragmentLeft extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String string = (String) msg.obj;
                    //得到数据进行展示数据
                    try {
                        JSONObject jsonObject = new JSONObject(string);
                        JSONObject datas = jsonObject.getJSONObject("datas");
                        JSONArray class_list = datas.getJSONArray("class_list");
                        Log.i("xxx", "class_list:" + class_list);
                        adapterview(class_list);
                        fragmentLeftAdapter.setOnResShopClassLinter(new FragmentLeftAdapter.OnResShopClassLinter() {
                            @Override
                            public void onShopclassClick(int postion) {
                                Toast.makeText(getActivity(), "点击了"+postion, Toast.LENGTH_SHORT).show();
                            if(postion==6){

                            }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    break;
            }
        }
    };
    private FragmentLeftAdapter fragmentLeftAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     if(view == null){
            view = inflater.inflate(R.layout.fragmentleft,container,false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }
        initview();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //进行数据的适配
        data();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        //进行设置分割线
        MyDecoration decoration = new MyDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(decoration);
    }

    //初始化空间的资源
    public void initview(){
        recyclerView = (RecyclerView)view.findViewById(R.id.class_left);
    }
    //进行网络请求分类内容
    public void data(){
        OkHHttpClientUtils.doget(Api.classpath, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i("sss", string);
                Message message = new Message();
                message.what=0;
                message.obj = string;
                handler.sendMessage(message);
            }
        });
    }
    public void adapterview(JSONArray jsonArray){
        if(fragmentLeftAdapter==null){
            fragmentLeftAdapter = new FragmentLeftAdapter(jsonArray,getActivity());
            recyclerView.setAdapter(fragmentLeftAdapter);
        }else{
            fragmentLeftAdapter.notifyDataSetChanged();
        }
    }
}
