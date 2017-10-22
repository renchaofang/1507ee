package test.bwie.com.cliuapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/8/31 23:43
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private Context context;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list, Context context) {
        super(fm);
        this.list = list;
        this.context = context;
    }

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
    @Override
    public int getCount() {
        return  list!=null?list.size():0;
    }
}
