package test.bwie.com.dianapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import test.bwie.com.dianapp.fragment.FragmentCard;
import test.bwie.com.dianapp.fragment.FragmentFaXian;
import test.bwie.com.dianapp.fragment.FragmentFenLei;
import test.bwie.com.dianapp.fragment.FragmentShouYe;
import test.bwie.com.dianapp.fragment.FragmentWoDe;

public class ShowActivity extends FragmentActivity {
    private FragmentManager supportFragmentManager;
    private RadioGroup radioGroup;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private RadioButton button4;
    private RadioButton button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        init();
        //获取fragment的管理者
        supportFragmentManager = getSupportFragmentManager();
        addToStack("fragment1",new FragmentShouYe());
        ItemBtn();
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
    }   //进行初始化控件
    public void init(){
        radioGroup = (RadioGroup)findViewById(R.id.group);
        button1 = (RadioButton)findViewById(R.id.radio_btn1);
        button2 = (RadioButton)findViewById(R.id.radio_btn2);
        button3 = (RadioButton)findViewById(R.id.radio_btn3);
        button4 = (RadioButton)findViewById(R.id.radio_btn4);
        button5 = (RadioButton)findViewById(R.id.radio_btn5);
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
    //点击按钮对象操作
    private void ItemBtn() {
        //进行点击事件的发生
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(button1.isChecked()==true){
                    addToStack("fragment1",new FragmentShouYe());
                    button1 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.shouye2), null, null);
                    button2 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.fenlei1), null, null);
                    button3 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.faxian1), null, null);
                    button4 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.gouwuche1), null, null);
                    button5 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.wode1), null, null);

                }else if(button2.isChecked()){
                    button1 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.shouye1), null, null);
                    button2 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.fenlei2), null, null);
                    button3 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.faxian1), null, null);
                    button4 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.gouwuche1), null, null);
                    button5 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.wode1), null, null);
                    addToStack("fragment2",new FragmentFenLei());
                }else if(button3.isChecked()){
                    button1 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.shouye1), null, null);
                    button2 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.fenlei1), null, null);
                    button3 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.faxian2), null, null);
                    button4 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.gouwuche1), null, null);
                    button5 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.wode1), null, null);
                    addToStack("fragment3",new FragmentFaXian());
                }else if(button4.isChecked()){
                    button1 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.shouye1), null, null);
                    button2 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.fenlei1), null, null);
                    button3 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.faxian1), null, null);
                    button4 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.gouwuche2), null, null);
                    button5 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.wode1), null, null);
                    addToStack("fragment4",new FragmentCard());
                }else if(button5.isChecked()){
                    button1 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.shouye1), null, null);
                    button2 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.fenlei1), null, null);
                    button3 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.faxian1), null, null);
                    button4 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.gouwuche1), null, null);
                    button5 .setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(ShowActivity.this,
                            R.drawable.wode2), null, null);
                    addToStack("fragment4",new FragmentWoDe());
                }
            }
        });
    }
}
