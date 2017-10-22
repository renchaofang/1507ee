package test.bwie.com.cliuapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.bwie.com.cliuapp.R;

/**
 * 1.类的用途   进行适配数据
 * 2.@author1
 * 3.@data2017/9/7 20:15
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewholder>{

    //进行传过来的数据集合
    private JSONArray list;
    private Context context;

    public RecyclerViewAdapter(JSONArray list, Context context) {
        this.list = list;
        this.context = context;
    }
    //创建view设置给ViewHolder
    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleritem,null);
        final MyViewholder myViewholder = new MyViewholder(view);

        //进行view监听方法进行点击效果
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行获取自己的下标
                int layoutPosition = myViewholder.getLayoutPosition();
                Log.i("xxx", "layoutPosition:" + layoutPosition);
                //设置监听
                if (listenter != null) {
                    listenter.onRecycleitemclick(layoutPosition);
                }
            }
        });
        //进行view监听方法进行点击效果
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //进行获取自己的下标
                int layoutPosition = myViewholder.getLayoutPosition();
                Log.i("xxx", "layoutPosition:" + layoutPosition);
                //设置监听
                if (listenter != null) {
                    longListener.onRecyclerViewLongItemClick(layoutPosition);
                }
                return true;
            }
        });
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        try {
            //进行设置图片的在软件当中的适配
            ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
           // position = position%2;
            if(position==0){
                layoutParams.height=400;
            }else{
                layoutParams.height=450;
            }
            holder.imageView.setLayoutParams(layoutParams);
            JSONObject jsonObject = list.getJSONObject(position);
            //获取的到具体的数据
            String image_url = jsonObject.optString("itemImage");
            String title = jsonObject.getString("title");
            String price = jsonObject.getString("price");
            Log.i("xxx", "图片的地址"+image_url);
            Log.i("xxx", "商品的名称"+title);
            //进行控件展示
            String colorpricr = "￥<font color = '#FF0000'>"+price+"</font>";
            holder.price.setText(Html.fromHtml(colorpricr));
            holder.textView.setText(title);
           // holder.imageView.setImageResource(R.mipmap.ic_launcher);
           Picasso.with(context).load(image_url).placeholder(R.mipmap.ic_launcher).into(holder.imageView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return list.length();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView price;
        private ImageView imageView;
        public MyViewholder(View itemView) {
            super(itemView);
            price = (TextView)itemView.findViewById(R.id.recycle_textview_price);
            textView = (TextView)itemView.findViewById(R.id.recycle_textview);
            imageView = (ImageView)itemView.findViewById(R.id.recycle_image);
        }
    }
    public  OnRecycleitemListenter listenter;
    //进行设置点击的条目
    public interface  OnRecycleitemListenter{
        void onRecycleitemclick(int position);
    }
    public void setOnRecycleitemListenter( OnRecycleitemListenter listenter){
        this.listenter=listenter;
    }
    //new Asyn

    //进行长按点击效果
    //声明接口
    public OnRecyclerViewLongItemClickListener longListener;
    //定义接口 和抽象方法
    public interface OnRecyclerViewLongItemClickListener {
        void onRecyclerViewLongItemClick(int position);
    }
    //提供设置监听的方法
    public void setOnRecyclerViewLongItemClickListener(OnRecyclerViewLongItemClickListener longListener) {
        this.longListener = longListener;
    }
}
