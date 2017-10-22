package com.bwie.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.test.Api;
import com.bwie.test.R;
import com.bwie.test.activity.MakeInfoActivity;
import com.bwie.test.activity.UserActivity;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/27 20:19
 */
public class Fragment_USER extends Fragment {

    private View view;
    private ImageView imageView;
    private TextView name;
    private TextView tuichu;
    private LinearLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.user,container,false);
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
        //进行记住登录状态
        Api.init(getActivity());
        boolean login = Api.sp.getBoolean("login", false);
        if(login==true){
            //获取用户的信息
            Toast.makeText(getActivity(), "来到了用户的界面", Toast.LENGTH_SHORT).show();
            //进行展示页面
            relativeLayout.setVisibility(View.VISIBLE);
            //进行修改用户信息
            String moblie = Api.sp.getString("moblie", null);
            String substring = moblie.substring(7, 10);
            name.setText("App用户"+substring);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity( new Intent(getActivity(), MakeInfoActivity.class));
                }
            });

        }else{
            //进行跳转到登录界面
            Intent intent = new Intent(getActivity(), UserActivity.class);
            startActivity(intent);
        }
    }
    public void init(){
        imageView = (ImageView)view.findViewById(R.id.user_img);
        name = (TextView)view.findViewById(R.id.user_name);
        tuichu = (TextView)view.findViewById(R.id.tuichu);
        relativeLayout = (LinearLayout)view.findViewById(R.id.liner);
    }

    public void MakeInfo(){


    }
}
