package test.bwie.com.renchaofang20171025;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.renchaofang20171025.fragment.FragmentA;
import test.bwie.com.renchaofang20171025.fragment.FragmentB;

public class ShowActivity extends FragmentActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> list;
    private List<Fragment> flist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        init();
        list = new ArrayList<>();
        list.add("第一个");
        list.add("第二个");
        flist = new ArrayList<>();
        flist.add(new FragmentA());
        flist.add(new FragmentB());
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(list.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list.get(1)));
        //获得fragment的管理者
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        //进行viewpager的数据适配器
        viewPager.setAdapter(new ViewAdapter(supportFragmentManager));
        //进行关联
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(new ViewAdapter(supportFragmentManager));
    }
    public void init(){
        tabLayout = (TabLayout)findViewById(R.id.tablay);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }
    class ViewAdapter extends FragmentPagerAdapter{
        public ViewAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override

        public Fragment getItem(int position) {
            return flist.get(position);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
        @Override
        public int getCount() {
            return flist.size();
        }
    }

}
