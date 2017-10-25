package test.bwie.com.renchaofang20171025.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import okhttp3.Call;
import test.bwie.com.ok.okhttp.GsonObjectCallback;
import test.bwie.com.ok.okhttp.OkHttp3Utils;
import test.bwie.com.renchaofang20171025.R;
import test.bwie.com.renchaofang20171025.bean.PicBean;

/**
 * Created by 1 on 2017/10/25.
 */

public class FragmentA extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragmenta, container, false);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        OkHttp3Utils.doGet("http://news-at.zhihu.com/api/4/news/latest", new GsonObjectCallback<PicBean>() {
            @Override
            public void onUi(PicBean picBean) {
                Log.i("xxx",picBean.toString());
                Log.i("xxx",picBean.getTop_stories().size()+"");

            }
            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
