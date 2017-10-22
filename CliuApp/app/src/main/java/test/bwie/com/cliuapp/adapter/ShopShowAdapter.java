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
import test.bwie.com.cliuapp.bean.ShoppingBean;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/12 08:20
 */
public class ShopShowAdapter extends RecyclerView.Adapter<ShopShowAdapter.MyHolder>{

    private List<ShoppingBean.ResultBean.WallBean.DocsBean> list;
    private Context context;

    public ShopShowAdapter(List<ShoppingBean.ResultBean.WallBean.DocsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.shopshopview,null);
        MyHolder myHolder = new MyHolder(v);
        return  myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        //进行设置图片的在软件当中的适配
        ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
        // position = position%2;
        if(position==0){
            layoutParams.height=450;
        }else{
            layoutParams.height=450;
        }
        holder.imageView.setLayoutParams(layoutParams);
            String title = list.get(position).getTitle();
            String img = list.get(position).getImg();
            //进行展示数据
            Picasso.with(context).load(img).placeholder(R.mipmap.ic_launcher).into(holder.imageView);
            holder.title.setText(title);
            holder.price.setText(list.get(position).getPrice()+"");
            holder.add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进行添加服务器
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
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
            imageView = (ImageView)itemView.findViewById(R.id.shop_image);
            title = (TextView)itemView.findViewById(R.id.shop_textview);
            price = (TextView)itemView.findViewById(R.id.shop_price);
            add_btn = (ImageView)itemView.findViewById(R.id.shop_addcard);

        }
    }
 }
