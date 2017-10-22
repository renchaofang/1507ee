package test.bwie.com.cliuapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import test.bwie.com.cliuapp.Api;
import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.activity.LoginInfoActivity;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/8/31 23:38
 */
public class Fragment_user extends Fragment {

    private View view;
    private ImageView imageView;
    private RelativeLayout relativeLayout;
    private GridView gridView;
    private GridView gridView1;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.fragment_user,container,false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }
        //获取控件
        init();
        return  view;
    }
    //进行获取控件资源
    public void init(){
        imageView = (ImageView)view.findViewById(R.id.user_image);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.s);
        textView = (TextView)view.findViewById(R.id.login_resgit_user);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Api.init(getActivity());
        boolean login = Api.sp.getBoolean("login", false);
        if(login==true){
            String username = Api.sp.getString("username","登录注册");
            textView.setText(username);
        }else{
            login();
        }
    }
    public void login(){
        //点击进行登录账号
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行跳转
                Intent intent = new Intent(getActivity(), LoginInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
