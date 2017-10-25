package test.bwie.com.renchaofang20171025.paint;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 1 on 2017/10/25.
 */

public class CoustomView extends View {
    Point currentPoint;//当前point
    public static final float R=50f;
    public CoustomView(Context context) {
        super(context);
    }

    public CoustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CoustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        if(currentPoint==null){
            currentPoint=new Point(100,100);
            float x=currentPoint.getX();
            float y=currentPoint.getY();
            canvas.drawCircle(x,y,R,paint);
        }else{
            float x=currentPoint.getX();
            float y=currentPoint.getY();
            canvas.drawCircle(x,y,R,paint);
        }

    }

    public void start(){
        //设置起点终点
        Point startPoint=new Point(100,100);
        Point endPoint=new Point(460,1100);
        ValueAnimator ballAnim=ValueAnimator.ofObject(new Yuan(),startPoint,endPoint);
        ballAnim.setDuration(3000);
        //监听动画过程
        ballAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                 currentPoint = (Point) animation.getAnimatedValue();//每次变化，都会重新设置currentPoint的位置
                invalidate();//每次位置改变都重绘一下小球位置
            }
        });
        ballAnim.start();
    }

}
