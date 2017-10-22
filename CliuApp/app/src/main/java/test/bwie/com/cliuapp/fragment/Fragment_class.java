package test.bwie.com.cliuapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.activity.SeekActivity;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/8/31 23:38
 */
public class Fragment_class extends Fragment {
    private View view;
    private ImageView saoma_btn;
    private ImageView message_btn;
    private EditText editText;
    private ImageView image_btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //进行优化fragment操作

        if(view==null){
            view = inflater.inflate(R.layout.fragment_class,container,false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }
        initview();
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击文本框", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SeekActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void initview(){
        saoma_btn = (ImageView) view.findViewById(R.id.shaoma_btn);
        message_btn = (ImageView) view.findViewById(R.id.message_btn);
        editText = (EditText)view.findViewById(R.id.edt_operator_name);
    }
}
