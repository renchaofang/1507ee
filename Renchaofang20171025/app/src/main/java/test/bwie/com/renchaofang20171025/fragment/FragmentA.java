package test.bwie.com.renchaofang20171025.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.jude.rollviewpager.RollPagerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import test.bwie.com.ok.okhttp.GsonObjectCallback;
import test.bwie.com.ok.okhttp.OkHttp3Utils;
import test.bwie.com.renchaofang20171025.R;
import test.bwie.com.renchaofang20171025.RecycleAdapter;
import test.bwie.com.renchaofang20171025.TestNormalAdapter;
import test.bwie.com.renchaofang20171025.bean.InfoBean;
import test.bwie.com.renchaofang20171025.bean.LunBoBean;
import test.bwie.com.renchaofang20171025.bean.PicBean;

/**
 * Created by 1 on 2017/10/25.
 */

public class FragmentA extends Fragment {

    private List<InfoBean> list = new ArrayList<>();
    private List<LunBoBean> slist = new ArrayList<>();

    private Handler handler = new Handler();
    private View inflate;
    private SwipyRefreshLayout swipyRefreshLayout;
    private RollPagerView viewpager;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(inflate==null){
            inflate = inflater.inflate(R.layout.fragmenta, container, false);
        }
        ViewGroup parent = (ViewGroup) inflate.getParent();
        if(parent!=null){
            parent.removeView(inflate);
        }
        init();
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        getData();
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipyRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
            @Override
            public void onLoad(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipyRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }
    public void init(){
        swipyRefreshLayout = (SwipyRefreshLayout)inflate.findViewById(R.id.refresh);
        viewpager = (RollPagerView)inflate.findViewById(R.id.rollpager);
        recyclerView = (RecyclerView)inflate.findViewById(R.id.recycle);
        //设置播放时间间隔
        viewpager.setPlayDelay(3000);
        //设置透明度
        viewpager.setAnimationDurtion(500);
    }

    public void getData(){
        OkHttp3Utils.doGet("http://news-at.zhihu.com/api/4/news/latest", new GsonObjectCallback<PicBean>() {
            @Override
            public void onUi(PicBean picBean) {
                Log.i("xxx",picBean.toString());
                Log.i("xxx",picBean.getTop_stories().size()+"");
                //轮播图的图片加载
                List<PicBean.TopStoriesBean> top_stories = picBean.getTop_stories();
                for(int j =0 ; j<top_stories.size();j++){
                    LunBoBean lunBoBean = new LunBoBean();
                    PicBean.TopStoriesBean topStoriesBean = top_stories.get(j);
                    String image = topStoriesBean.getImage();
                    lunBoBean.setImg(image);
                    slist.add(lunBoBean);
                }
                //具体的数据内容
                List<PicBean.StoriesBean> stories = picBean.getStories();
                for (int i =0;i<stories.size();i++){
                    InfoBean infoBean = new InfoBean();
                    PicBean.StoriesBean storiesBean = stories.get(i);
                    List<String> images = storiesBean.getImages();
                    String s = images.get(0);
                    infoBean.setImg(s);
                    infoBean.setTitle( storiesBean.getTitle());
                    list.add(infoBean);
                }
                BindData(list);
            }
            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

    public void BindData(List<InfoBean> list){
        RecycleAdapter recycleAdapter = new RecycleAdapter(list, getActivity());
        recyclerView.setAdapter(recycleAdapter);
        TestNormalAdapter testNormalAdapter = new TestNormalAdapter(slist, getActivity());
        viewpager.setAdapter(testNormalAdapter);
    }
}
