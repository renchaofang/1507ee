package test.bwie.com.dianapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.dianapp.R;
import test.bwie.com.dianapp.bean.ShopSecondBean;
import test.bwie.com.dianapp.bean.ShopThreeBean;

/**
 * Created by 1 on 2017/10/19.
 */

public class ListViewAdapter extends BaseAdapter {

    private List<ShopSecondBean> list;
    private Context context;

    public ListViewAdapter(List<ShopSecondBean> secondBeanList, Context context) {
        this.list = secondBeanList;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context,R.layout.listviewitem,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)convertView.findViewById(R.id.item_tv);
            viewHolder.gridView = (GridView) convertView
                    .findViewById(R.id.gv_listView_main_gridView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShopSecondBean shopSecondBean = list.get(i);
        //得到三级的字符串
        JSONArray jsonArray = shopSecondBean.getJsonArray();
        Log.i("mmm",jsonArray.length()+"");
        //展示数据
        String name = shopSecondBean.getName();
        viewHolder.textView.setText(name);
        if (jsonArray.length() == 0) {
            viewHolder.gridView.setVisibility(View.GONE);
            Toast.makeText(context,"什么都没有",Toast.LENGTH_SHORT).show();
        } else {
                try {
                     List<ShopThreeBean > threelist = new ArrayList<>();
                    for(int j=0;j<jsonArray.length();j++){
                        ShopThreeBean shopThreeBean = new ShopThreeBean();
                        JSONObject jsonObject = jsonArray.getJSONObject(j);
                        String title = jsonObject.getString("name");
                        String icon = jsonObject.getString("icon");
                        shopThreeBean.setIcon(icon);
                        shopThreeBean.setName(title);
                        threelist.add(shopThreeBean);
                    }
                    viewHolder.gridView.setAdapter(new MyGridViewAdapter(threelist,context));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }
        return convertView;
    }
    class ViewHolder{
        TextView textView;
        GridView gridView;
    }
}
