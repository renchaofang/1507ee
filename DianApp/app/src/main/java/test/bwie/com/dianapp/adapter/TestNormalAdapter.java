package test.bwie.com.dianapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import test.bwie.com.dianapp.R;

/**
 * Created by 1 on 2017/10/20.
 */

public class TestNormalAdapter extends StaticPagerAdapter {

    private List<String> list;
    private Context context;

    public TestNormalAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public TestNormalAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        Picasso.with(context).load(list.get(position)).placeholder(R.mipmap.ic_launcher).into(view);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
