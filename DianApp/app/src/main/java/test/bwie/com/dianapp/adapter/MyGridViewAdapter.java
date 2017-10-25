package test.bwie.com.dianapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import test.bwie.com.dianapp.R;
import test.bwie.com.dianapp.bean.ShopThreeBean;

/**
 * Created by 1 on 2017/10/19.
 */

public class MyGridViewAdapter extends BaseAdapter {
    private List<ShopThreeBean> list;
    private Context context;

    public MyGridViewAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = View.inflate(context, R.layout.gridview,null);
            vh.textView = (TextView)convertView.findViewById(R.id.grid_tv);
            vh.imageView = (ImageView) convertView
                    .findViewById(R.id.grid_img);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        ShopThreeBean shopThreeBean = list.get(i);
        vh.textView.setText(shopThreeBean.getName());
        Picasso.with(context).load(shopThreeBean.getIcon()).placeholder(R.mipmap.ic_launcher).into(vh.imageView);
        return convertView;
    }
    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
