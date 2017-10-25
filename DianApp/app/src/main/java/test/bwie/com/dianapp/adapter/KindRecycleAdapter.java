package test.bwie.com.dianapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.bwie.com.dianapp.R;
import test.bwie.com.dianapp.bean.FirstKndBean;

/**
 * Created by 1 on 2017/10/19.
 */

public class KindRecycleAdapter extends RecyclerView.Adapter<KindRecycleAdapter.MyViewHolder>{

    private List<FirstKndBean> list;
    private Context context;

    public KindRecycleAdapter(List<FirstKndBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kindlistitem,null);
        final KindRecycleAdapter.MyViewHolder myViewHolder = new KindRecycleAdapter.MyViewHolder(view);
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
        Log.i("fff", "layoutPosition:" + list.size()+"");
        //进行绑定数据
        holder.futitle.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView futitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            futitle = (TextView)itemView.findViewById(R.id.kind_title);
        }
    }

    public RecycleAdapter.OnRecycleitemListenter listenter;
    //进行设置点击的条目
    public interface  OnRecycleitemListenter{
        void onRecycleitemclick(int position);
    }
    public void setOnRecycleitemListenter( RecycleAdapter.OnRecycleitemListenter listenter){
        this.listenter=listenter;
    }


}
