package test.bwie.com.cliuapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.bwie.com.cliuapp.R;

/**
 * 1.类的用途   进行适配数据
 * 2.@author1
 * 3.@data2017/9/7 20:15
 */
public class FragmentLeftAdapter extends RecyclerView.Adapter<FragmentLeftAdapter.MyViewholder>{

    //进行传过来的数据集合
    private JSONArray list;
    private Context context;

    public FragmentLeftAdapter(JSONArray list, Context context) {
        this.list = list;
        this.context = context;
    }
    //创建view设置给ViewHolder
    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragmentleft_item,null);
        
        final MyViewholder myViewholder = new MyViewholder(view);
        //进行设置点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取自己视图的下标
                int position = myViewholder.getAdapterPosition();
                if(onResShopClassLinter!=null){
                    onResShopClassLinter.onShopclassClick(position);
                }
            }
        });
        return myViewholder;
    }
    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        try {
            //进行设置图片的在软件当中的适配
           /* ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
           // position = position%2;
            if(position==0){
                layoutParams.height=350;
            }else{
                layoutParams.height=450;
            }
            holder.imageView.setLayoutParams(layoutParams);*/
            JSONObject jsonObject = list.getJSONObject(position);
            //获取的到具体的数据
            String gc_name = jsonObject.optString("gc_name");
            Log.i("xxx", "商品的名称"+gc_name);
            holder.textView.setText(gc_name);
           // holder.imageView.setImageResource(R.mipmap.ic_launcher);
           //Picasso.with(context).load(image_url).placeholder(R.mipmap.ic_launcher).into(holder.imageView);
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
        public MyViewholder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.class_item_textview);
        }
    }

    public OnResShopClassLinter onResShopClassLinter;
    public interface OnResShopClassLinter{
        void onShopclassClick(int postion);
    }
    public void setOnResShopClassLinter(OnResShopClassLinter onResShopClassLinter){
        this.onResShopClassLinter=onResShopClassLinter;
    }
}
