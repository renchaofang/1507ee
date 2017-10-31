package com.bwie.voovapp.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.voovapp.R;
import com.bwie.voovapp.fragment.DuanZiFragment;
import com.bwie.voovapp.fragment.ShiPinFragment;
import com.bwie.voovapp.fragment.TuiJIanFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class ZhuJieMianActivity extends FragmentActivity {

    private TextView title;
    private FrameLayout frameLayout;
    private RadioGroup group;
    private RadioButton duanzi;
    private RadioButton tuijian;
    private RadioButton shipon;
    private FragmentManager supportFragmentManager;
    private ImageView userimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_zhu_jie_mian);
        init();
        supportFragmentManager = getSupportFragmentManager();
        addToStack("fragmentA",new TuiJIanFragment());
        title.setText(tuijian.getText().toString());
        getFragmentButton();
        getSideframe();
    }
    //初始化资源控件
    public void init(){
        userimg = (ImageView)findViewById(R.id.user);
        title = (TextView)findViewById(R.id.textview);
        ImageView  bianji = (ImageView)findViewById(R.id.bianji);
        frameLayout = (FrameLayout)findViewById(R.id.fragment);
        group = (RadioGroup)findViewById(R.id.group);
        tuijian = (RadioButton)findViewById(R.id.tuijian);
        duanzi = (RadioButton)findViewById(R.id.duanzi);
        shipon = (RadioButton)findViewById(R.id.shipin);
    }
    //进行动态添加fragmeny
    private void addToStack(String tag, Fragment fragment) {
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.fragment, fragment);
        //添加到回退栈
        beginTransaction.addToBackStack(tag);
        beginTransaction.commit();
    }
    public void getFragmentButton(){
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(tuijian.isChecked()==true){
                    Toast.makeText(ZhuJieMianActivity.this,"我点解了1",Toast.LENGTH_SHORT).show();
                    addToStack("fragmentA",new TuiJIanFragment());
                    title.setText(tuijian.getText().toString());
                }else if(duanzi.isChecked()==true){
                    Toast.makeText(ZhuJieMianActivity.this,"我点解了2",Toast.LENGTH_SHORT).show();
                    addToStack("fragmentB",new DuanZiFragment());
                    title.setText(duanzi.getText().toString());
                } else if (shipon.isChecked()==true) {
                    Toast.makeText(ZhuJieMianActivity.this,"我点解了3",Toast.LENGTH_SHORT).show();
                    addToStack("fragmentC",new ShiPinFragment());
                    title.setText(shipon.getText().toString());
                }
            }
        });
    }
    //监听模拟器的返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            //获取当前回退栈里面fragment 的个数
            int backStackEntryCount = supportFragmentManager.getBackStackEntryCount();
            //判断回退栈里面有多个fragment
            if(backStackEntryCount>1){
                while(supportFragmentManager.getBackStackEntryCount()>1 ){
                    supportFragmentManager.popBackStackImmediate();
                    tuijian.setChecked(true);
                }
            }else{
                finish();
            }
        }
        return true;
    }
    //操作侧拉矿的显示
    public void getSideframe(){

                final SlidingMenu slidingMenu = new SlidingMenu(ZhuJieMianActivity.this);
                slidingMenu.setMode(SlidingMenu.LEFT);
                slidingMenu.setBehindOffset(200);

                slidingMenu.attachToActivity(ZhuJieMianActivity.this, SlidingMenu.SLIDING_CONTENT);
                //加载布局
                slidingMenu.setMenu(R.layout.menu);
        userimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenu.toggle();
            }
        });
    }
}
