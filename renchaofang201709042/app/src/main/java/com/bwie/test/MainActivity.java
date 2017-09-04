package com.bwie.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bwie.test.view.CustomView;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //进行初始化控件
        button1 = (Button)findViewById(R.id.btn1);
        button2 = (Button)findViewById(R.id.btn2);
        button3 = (Button)findViewById(R.id.btn3);
        customView = (CustomView)findViewById(R.id.view);

        //进行点击事件
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.setColor();
            }
        });
    }
}
