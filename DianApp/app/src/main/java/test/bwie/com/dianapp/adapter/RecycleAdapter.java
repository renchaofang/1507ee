package test.bwie.com.dianapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import test.bwie.com.dianapp.R;
import test.bwie.com.dianapp.bean.ShowInfoBean;

/**
 * Created by 1 on 2017/10/18.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>{

    private List<ShowInfoBean> list;
    private Context context;

    public RecycleAdapter(List<ShowInfoBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleitem,null);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        //进行view监听方法进行点击效果
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行获取自己的下标
                int layoutPosition = myViewHolder.getLayoutPosition();
                Log.i("xxx", "layoutPosition:" + layoutPosition);
                //设置监听
                if (listenter != null) {
                    listenter.onRecycleitemclick(layoutPosition);
                }
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("wwww",list.size()+"");
        holder.futitle.setText(list.get(position).getSubhead());
        holder.title.setText(list.get(position).getTitle());
        holder.price.setText("价格："+list.get(position).getPrice()+"");
        String image = list.get(position).getImage();
        String[] split = image.split("\\|");
        if(split.length>1){
            Picasso.with(context).load(split[0]).placeholder(R.mipmap.ic_launcher).into(holder.imageView);
        }else{
            Picasso.with(context).load(list.get(position).getImage()).placeholder(R.mipmap.ic_launcher).into(holder.imageView);

        }


            Log.i("ddd",split.length+"");



    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
       private TextView futitle;
        private TextView title;
        private TextView price;
        private ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.img);
            title = (TextView)itemView.findViewById(R.id.tv1);
            price = (TextView)itemView.findViewById(R.id.tv2);
            futitle = (TextView)itemView.findViewById(R.id.tv3);

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
}
