package com.example.a1.demostudy.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//2255
import com.example.a1.demostudy.Api;
import com.example.a1.demostudy.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.okhttoutils_rcf.Utils.OkHttp3Utils;

public class PickActivity extends AppCompatActivity {
    private Bitmap bitmap;
    private TextView ji;
    private TextView ce;
    private Button button;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);
        Api.init(PickActivity.this);
        ji = (TextView) findViewById(R.id.ji);
        ce = (TextView) findViewById(R.id.ce);
        button = (Button)findViewById(R.id.shangchuan);
        img = (ImageView) findViewById(R.id.imageView);
        //打开相机
        ji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera(300);
            }
        });
        //打开相册
        ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPin(200);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shangchuan();
            }
        });
    }
    public void shangchuan(){
        File file = saveBitmapFile(bitmap);
        int id = Api.sp.getInt("id",0);
        String s = Api.getUp + "?uid=" + id + "&file=" + file;
        Log.i("xxx",s);
        OkHttp3Utils.doGet(s, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i("xxx",string);
            }
        });
    }
    /**
     * 打开系统相册
     * */
    private void openPin(int code) {
        Intent it = new Intent(Intent.ACTION_PICK);
        it.setType("image/*");
        startActivityForResult(it,code);
    }
    /**
     * 打开系统照相机
     * */
    private void openCamera(int code){
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it,code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            if(data != null){
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    img.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(requestCode == 300){
            bitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);
        }
    }
    /**
     * 将bitmap转换成File的方法
     * */
    public File saveBitmapFile(Bitmap bitmap) {
        //将要保存图片的路径
        File file = new File(Environment.getExternalStorageDirectory(), "01.jpg");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
