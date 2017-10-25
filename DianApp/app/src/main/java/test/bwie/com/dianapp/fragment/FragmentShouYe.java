package test.bwie.com.dianapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.List;

import test.bwie.com.dianapp.R;
import test.bwie.com.dianapp.ShopShowActivity;
import test.bwie.com.dianapp.adapter.TestNormalAdapter;
import test.bwie.com.dianapp.p.UserPre;
import test.bwie.com.dianapp.v.ILunBoView;

/**
 * Created by 1 on 2017/10/17.
 */

public class FragmentShouYe extends Fragment implements View.OnClickListener,ILunBoView{

    private View view;
    private ImageView saosao;
    private ImageView message;
    private EditText shopinfo;
    private RollPagerView mRollViewPager ;
    private UserPre userPre;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.shouye,container,false);
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
        userPre = new UserPre(this);
        getPrick();
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(2000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW,Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);

    }
    public void init(){
        saosao = (ImageView)view.findViewById(R.id.saosao);
        message = (ImageView)view.findViewById(R.id.message);
        shopinfo = (EditText)view.findViewById(R.id.shop);
        mRollViewPager  = (RollPagerView)view.findViewById(R.id.roll_view_pager);
        shopinfo.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shop:
                startActivity(new Intent(getActivity(), ShopShowActivity.class));
                break;
        }
    }

    @Override
    public void getPrick() {
            userPre.getLunBo();
    }

    @Override
    public void getAdapter(List<String> list) {
        mRollViewPager.setAdapter(new TestNormalAdapter(list,getActivity()));
    }
}
