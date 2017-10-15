package com.example.a1.demostudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1.demostudy.R;
import com.example.a1.demostudy.bean.ShopBean;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by 1 on 2017/10/14.
 */

public class RecycleAdapter extends RecyclerView.Adapter{

        private static int MOREITEM = 0;
        private static int ONEITEM = 1;
        private List<ShopBean> jsonArray;
        private Context context;

    public RecycleAdapter(List<ShopBean> jsonArray, Context context) {
        this.jsonArray = jsonArray;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType==MOREITEM){
            view = View.inflate(parent.getContext(),R.layout.recycleitem2,null);
            RecycleMoreHolder recycleMoreHolder = new RecycleMoreHolder(view);
            return recycleMoreHolder;
        }else if(viewType==ONEITEM){
            view = View.inflate(parent.getContext(),R.layout.recycleitem,null);
            RecycleViewHolder recycleViewHolder = new RecycleViewHolder(view);
            return recycleViewHolder;
        }
            return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            //将得到数据，进行适配展示在页面上去
        ShopBean shopBean = jsonArray.get(position);
        if(holder instanceof RecycleViewHolder){
              ((RecycleViewHolder) holder).title.setText(shopBean.getTitle());
              ((RecycleViewHolder) holder).price.setText(shopBean.getPrice()+"");
              Picasso.with(context).load(shopBean.getImage()).placeholder(R.mipmap.ic_launcher).into(((RecycleViewHolder) holder).imageView);
      }else if (holder instanceof RecycleMoreHolder){
          //进行加载多个图片
            String[] split = shopBean.getImage().split("\\|");
                ((RecycleMoreHolder) holder).title.setText(shopBean.getTitle());
                ((RecycleMoreHolder) holder).price.setText(shopBean.getPrice()+"");
                Picasso.with(context).load(split[0]).placeholder(R.mipmap.ic_launcher).into(((RecycleMoreHolder) holder).imageView);
                Picasso.with(context).load(split[1]).placeholder(R.mipmap.ic_launcher).into(((RecycleMoreHolder) holder).imageView1);
                Picasso.with(context).load(split[2]).placeholder(R.mipmap.ic_launcher).into(((RecycleMoreHolder) holder).imageView2);
        }
    }

    //进行多条目的判断

    @Override
    public int getItemViewType(int position) {
        //获取得到图片的地址
            ShopBean shopBean = jsonArray.get(position);
            String[] split = shopBean.getImage().split("\\|");
            if(split.length>1){
                return MOREITEM;
            }else{
                return  ONEITEM;
            }
    }

    @Override
    public int getItemCount() {
        return jsonArray.size();
    }
    public class RecycleViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title;
        private TextView price;
        public RecycleViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.show_img);
            title = (TextView)itemView.findViewById(R.id.shop_title);
            price = (TextView)itemView.findViewById(R.id.shop_price);
        }
    }
    public class RecycleMoreHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private ImageView imageView1;
        private ImageView imageView2;
        private TextView title;
        private TextView price;
        public RecycleMoreHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.img1);
            imageView1 = (ImageView)itemView.findViewById(R.id.img2);
            imageView2 = (ImageView)itemView.findViewById(R.id.img3);
            title = (TextView)itemView.findViewById(R.id.tv1);
            price = (TextView)itemView.findViewById(R.id.tv2);
        }
    }

}
