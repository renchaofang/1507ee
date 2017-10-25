package test.bwie.com.renchaofang20171025.paint;

import android.animation.TypeEvaluator;

/**
 * Created by 1 on 2017/10/25.
 */

public class Yuan implements TypeEvaluator {
    @Override
    public Object evaluate(float v, Object o, Object t1) {
        Point startPoint = (Point) o;
        Point endPoint = (Point) t1;
        float x = startPoint.getX() + v * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + v * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }
}
