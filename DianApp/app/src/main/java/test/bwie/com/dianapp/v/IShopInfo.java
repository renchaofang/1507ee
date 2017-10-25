package test.bwie.com.dianapp.v;

import org.json.JSONArray;

import java.util.List;

import test.bwie.com.dianapp.bean.ShowInfoBean;

/**
 * Created by 1 on 2017/10/18.
 */

public interface IShopInfo {
    String getTile();
    void getRecycle(List<ShowInfoBean> list);
}
