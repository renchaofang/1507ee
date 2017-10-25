package test.bwie.com.dianapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import test.bwie.com.dianapp.R;
import test.bwie.com.dianapp.adapter.ListViewAdapter;
import test.bwie.com.dianapp.bean.FirstKndBean;
import test.bwie.com.dianapp.bean.ShopSecondBean;
import test.bwie.com.dianapp.bean.ShopThreeBean;
import test.bwie.com.dianapp.p.UserPre;
import test.bwie.com.dianapp.v.IFindShop;
import test.bwie.com.dianapp.v.IFirstShop;
import test.bwie.com.dianapp.v.IShopInfo;
import test.bwie.com.okhttoutils_rcf.Utils.OkHttp3Utils;

/**
 * Created by 1 on 2017/10/19.
 */

public class RightClassFragment extends Fragment implements IFirstShop{

    private View view;
    private ListView listView;
    private UserPre userPre;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.rightfragment,container,false);
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
        //得到数据
        FirstKndBean str = (FirstKndBean) getArguments().getSerializable("fragment");
        //得到一级列表传递过来的cid
        userPre = new UserPre(this);
        int cid = str.getCid();
        //进行请求数据
        getShop(cid);
    }
    public void init(){
        listView = (ListView)view.findViewById(R.id.listview);
    }
    @Override
    public void getShop(int cid) {
        userPre.getFirstShow(cid);
    }
    @Override
    public void getAdapter(List<ShopSecondBean> secondBeanList) {
        ListViewAdapter listViewAdapter = new ListViewAdapter(secondBeanList,getActivity());
        listView.setAdapter(listViewAdapter);
    }
}
