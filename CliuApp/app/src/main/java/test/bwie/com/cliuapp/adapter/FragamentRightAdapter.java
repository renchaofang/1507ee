package test.bwie.com.cliuapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.bean.ShopInfoBean;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/12 08:20
 */
public class FragamentRightAdapter extends RecyclerView.Adapter<FragamentRightAdapter.MyHolder>{

    private List<ShopInfoBean.DatasBean.GoodsListBean> list;
    private Context context;

    public FragamentRightAdapter(List<ShopInfoBean.DatasBean.GoodsListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragmentright_item,null);
        final MyHolder myHolder = new MyHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myHolder.getAdapterPosition();
                if(listenter!=null){
                    listenter.onRecycleitemclick(position);
                }
            }
        });
        return  myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        //进行设置图片的在软件当中的适配
        ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
        // position = position%2;
        if(position==0){
            layoutParams.height=350;
        }else{
            layoutParams.height=350;
        }
        holder.imageView.setLayoutParams(layoutParams);
            String title = list.get(position).getGoods_name();
            String img = list.get(position).getGoods_image_url();
            //进行展示数据
            Picasso.with(context).load(img).placeholder(R.mipmap.ic_launcher).into(holder.imageView);
            holder.title.setText(title);
            holder.price.setText(list.get(position).getGoods_price());
            holder.add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进行添加服务器
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                    //进行获取的购物车的信息
                    //key   59bb610e3afbe4bd61b71795a4dbe022
                    // goods_id    100008
                    // quantity     商品的数量 1
                    String goods_id = list.get(position).getGoods_id();


                }
            });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title;
        private TextView price;
        private ImageView add_btn;
        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.shop_info_image);
            title = (TextView)itemView.findViewById(R.id.shop_info_textview);
            price = (TextView)itemView.findViewById(R.id.shop_info_price);
            add_btn = (ImageView)itemView.findViewById(R.id.shop_info_addcard);

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
