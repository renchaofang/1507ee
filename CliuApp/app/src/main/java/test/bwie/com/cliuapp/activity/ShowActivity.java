package test.bwie.com.cliuapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.fragment.Fragment_card;
import test.bwie.com.cliuapp.fragment.Fragment_class;
import test.bwie.com.cliuapp.fragment.Fragment_home;
import test.bwie.com.cliuapp.fragment.Fragment_user;

public class ShowActivity extends FragmentActivity {

    private RadioGroup radioGroup;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private RadioButton button4;
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //调用初始化控件
        init();
        //获取fragment的管理者
        supportFragmentManager = getSupportFragmentManager();
        addToStack("fragment1",new Fragment_home());
        ItemBtn();


    }
    //点击按钮对象操作
    private void ItemBtn() {
        //进行点击事件的发生
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(button1.isChecked()==true){
                    addToStack("fragment1",new Fragment_home());
                    button1 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_home_press), null, null);
                    button2 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_class), null, null);
                    button3 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_cart), null, null);
                    button4 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_user), null, null);

                }else if(button2.isChecked()){
                    button1 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_home), null, null);
                    button2 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_class_press), null, null);
                    button3 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_cart), null, null);
                    button4 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_user), null, null);
                    addToStack("fragment2",new Fragment_class());
                }else if(button3.isChecked()){
                    button1 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_home), null, null);
                    button2 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_class), null, null);
                    button3 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_cart_press), null, null);
                    button4 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_user), null, null);
                    addToStack("fragment3",new Fragment_card());
                }else if(button4.isChecked()){
                    button1 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_home), null, null);
                    button2 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_class), null, null);
                    button3 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_cart), null, null);
                    button4 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.mipmap.ic_nav_user_press), null, null);
                    addToStack("fragment4",new Fragment_user());
                }
            }
        });
    }

    //进行初始化控件
    public void init(){
        radioGroup = (RadioGroup)findViewById(R.id.group);
        button1 = (RadioButton)findViewById(R.id.radio_btn1);
        button2 = (RadioButton)findViewById(R.id.radio_btn2);
        button3 = (RadioButton)findViewById(R.id.radio_btn3);
        button4 = (RadioButton)findViewById(R.id.radio_btn4);
    }
    /**
     * 需要确定的tag,需要fragment 在commit之前,添加到回退栈
     */
    private void addToStack(String tag, Fragment fragment) {
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.show_fragment, fragment);
        //添加到回退栈
        beginTransaction.addToBackStack(tag);
        beginTransaction.commit();
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
                    button1.setChecked(true);
                }
            }else{
                finish();
            }
        }
        return true;
    }
}
