package com.bwie.voovapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.voovapp.R;
import com.bwie.voovapp.adapter.ViewPagerAdapter;
import com.bwie.voovapp.childfragment.LeftFragment;
import com.bwie.voovapp.childfragment.RightFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2017/10/30.
 */

public class TuiJIanFragment extends Fragment {

    private View view;
    private ViewPager viewpager;
    private TabLayout tabLayout;
    private List<String > slist = new ArrayList<>();
    private List<Fragment> flist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.tuijianfragment,container,false);
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
        slist.add("热门");
        slist.add("关注");
        flist.add(new LeftFragment());
        flist.add(new RightFragment());
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("热门"));
        tabLayout.addTab(tabLayout.newTab().setText("关注"));
        //获得fragment的管理者
        //进行viewpager的数据适配器
            viewpager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(),flist,slist));
        //进行关联
        tabLayout.setupWithViewPager(viewpager);
        //tabLayout.setTabsFromPagerAdapter(new ViewPagerAdapter(getChildFragmentManager(),flist,slist));
    }
    public void init(){
        viewpager = (ViewPager)view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout)view.findViewById(R.id.table);
    }
}
