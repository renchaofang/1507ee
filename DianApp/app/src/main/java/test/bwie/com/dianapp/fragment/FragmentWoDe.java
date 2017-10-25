package test.bwie.com.dianapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import test.bwie.com.dianapp.LoginActivity;
import test.bwie.com.dianapp.R;
import test.bwie.com.dianapp.utils.SPUtil;

/**
 * Created by 1 on 2017/10/17.
 */

public class FragmentWoDe extends Fragment implements View.OnClickListener{

    private View view;
    private TextView textView;
    private TextView back;
    private RelativeLayout login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      if(view==null){
          view = inflater.inflate(R.layout.userinfo,container,false);
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
        //得到数据
        SPUtil key = new SPUtil(getActivity(), "key");
        boolean is_login = key.getBoolean("IS_LOGIN", false);
        Log.i("qqq",is_login+"");
        if(is_login==true){
            String login_username = key.getString("login_username",null);
            textView.setText(login_username);
        }else{
            textView.setText("未登录/注册");
        }
    }
    public void init(){
        textView = (TextView)view.findViewById(R.id.login_resgit_user);
        back = (TextView)view.findViewById(R.id.back);
        login = (RelativeLayout)view.findViewById(R.id.s);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.s:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
}
