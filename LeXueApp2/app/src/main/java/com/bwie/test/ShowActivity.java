package com.bwie.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ShowActivity extends FragmentActivity {

    private FrameLayout frameLayout;
    private RadioGroup group;
    private RadioButton brn1;
    private RadioButton brn2;
    private RadioButton brn3;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        init();
        //获取Fragmrnt的管理者
        supportFragmentManager = getSupportFragmentManager();

        //进行改变fragment状态
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(){

                }
            }
        });
    }
    //初始化资源控件
    public void init(){
        frameLayout = (FrameLayout)findViewById(R.id.fragment);
        group = (RadioGroup)findViewById(R.id.group);
        brn1 = (RadioButton)findViewById(R.id.shouye_btn);
        brn2 = (RadioButton)findViewById(R.id.tuijian_btn);
        brn3 = (RadioButton)findViewById(R.id.wode_btn);
    }

    /**
     * 需要确定的tag,需要fragment 在commit之前,添加到回退栈
     */
    private void addToStack(String tag, Fragment fragment) {
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.fragment,fragment);
        //添加到回退栈
        beginTransaction.addToBackStack(tag);
        beginTransaction.commit();
    }

}
