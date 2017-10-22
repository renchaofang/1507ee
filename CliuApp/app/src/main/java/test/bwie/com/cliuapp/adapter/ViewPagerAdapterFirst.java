package test.bwie.com.cliuapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/8/31 14:11
 */
public class ViewPagerAdapterFirst extends PagerAdapter {
    private List<Integer> list;
    private Context context;

    public ViewPagerAdapterFirst(List<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //进行添加imager图片
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(list.get(position));
        //将图片进行拉伸
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
