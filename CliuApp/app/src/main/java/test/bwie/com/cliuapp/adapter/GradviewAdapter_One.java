package test.bwie.com.cliuapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.bean.LogBean;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/4 20:22
 */
public class GradviewAdapter_One extends BaseAdapter {
    private List<LogBean> list;
    private Context context;

    public GradviewAdapter_One(List<LogBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context,R.layout.home_title_log,null);
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.home_log);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.home_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //进行适配数据
        viewHolder.textView.setText(list.get(position).getTitle());
        viewHolder.imageView.setImageResource(list.get(position).getPick());
        return convertView;
    }
    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
