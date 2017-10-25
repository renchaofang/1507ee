package test.bwie.com.dianapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if(what==0){
                startActivity(new Intent(MainActivity.this,ShowActivity.class));
                MainActivity.this.finish();
            }
        }
    };
    private int i=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     new Thread(){
         @Override
         public void run() {
             super.run();
             while (i>0){
                 try {
                     sleep(1000);
                     i--;
                     handler.sendEmptyMessage(i);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
     }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
