package com.bwie.voovapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 1 on 2017/10/30.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> slist;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> slist) {
        super(fm);
        this.list = list;
        this.slist = slist;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return slist.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
