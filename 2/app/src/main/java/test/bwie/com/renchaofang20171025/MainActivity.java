package test.bwie.com.renchaofang20171025;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import test.bwie.com.renchaofang20171025.paint.CoustomView;
import test.bwie.com.renchaofang20171025.paint.Yuan;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         final CoustomView coustomView = (CoustomView)findViewById(R.id.img);
        coustomView.start();
        coustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击了",Toast.LENGTH_SHORT).show();

           startActivity(new Intent(MainActivity.this,ShowActivity.class));
            }
        });

    }
}