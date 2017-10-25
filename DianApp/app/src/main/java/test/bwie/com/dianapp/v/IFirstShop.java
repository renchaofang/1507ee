package test.bwie.com.dianapp.v;

import java.util.List;

import test.bwie.com.dianapp.bean.ShopSecondBean;
import test.bwie.com.dianapp.bean.ShopThreeBean;

/**
 * Created by 1 on 2017/10/19.
 */

public interface IFirstShop {
    void getShop(int cid);
    void getAdapter(List<ShopSecondBean> secondBeanList);
}
