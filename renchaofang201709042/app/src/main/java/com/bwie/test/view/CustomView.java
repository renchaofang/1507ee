package com.bwie.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.bwie.test.R;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/4 08:50
 */
public class CustomView extends View {

private int mFirstColor;
    private int mCircleWidth;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 圆弧的度数
     */
    private int mProgress;
    /**
     * 圆弧绘制的速度
     */
    private int mSpeed;

    /**
     * 是否继续绘制
     */
    private boolean isDrawCircle = true;


    private int presage;
    private int radio;
    private int resourceId;
    private int color;
    private int anInt;
    private boolean net;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    //进行重写里面的方法
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //进行实例化画笔，进行画圆
         mPaint = new Paint();
        int center = getWidth()/2;
        canvas.translate(getWidth() / 2, getHeight() / 2);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(radio);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(-radio,-radio,radio,radio);
       // canvas.drawArc(rectF,180,180,true,mPaint);
        canvas.drawCircle(80,100,120,mPaint);
        if(!net){
            mPaint.setColor(color); // 设置圆环的颜色
            canvas.drawCircle(center, center, radio, mPaint); // 画出圆环
            mPaint.setColor(resourceId); // 设置圆环的颜色
            canvas.drawArc(rectF, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }else{
            mPaint.setColor(resourceId); // 设置圆环的颜色
            canvas.drawCircle(center, center, radio, mPaint); // 画出圆环
            mPaint.setColor(color); // 设置圆环的颜色
            canvas.drawArc(rectF, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }
    }
    //开启线程


    public void init(Context context,AttributeSet attr){
        TypedArray typedArray = context.obtainStyledAttributes(attr, R.styleable.CustomView);
        color = typedArray.getColor(R.styleable.CustomView_secondcolor, Color.GREEN);
        resourceId = typedArray.getResourceId(R.styleable.CustomView_backgroupcolor, Color.BLACK);
         radio = typedArray.getInteger(R.styleable.CustomView_radio, 10);
        anInt = typedArray.getInt(R.styleable.CustomView_speed, 20);
        //进行引用布局a
        typedArray.recycle();

        new Thread(new Runnable() {
            @Override
            public void run() {
                    while(true){
                        presage++;
                        if(presage==360){
                            presage=0;
                            if(!net){
                                net=true;
                            }else{
                                net=false;
                            }
                        }
                        postInvalidate();
                        try {
                            Thread.sleep(mSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }).start();
    }
    public void  setColor(){
       //进行改变颜色
       mPaint.setColor(Color.RED);
        return;
    }



}
