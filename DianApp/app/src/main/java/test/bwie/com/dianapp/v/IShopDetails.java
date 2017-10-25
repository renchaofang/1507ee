package test.bwie.com.dianapp.v;

import test.bwie.com.dianapp.bean.ShopDetailsBean;

/**
 * Created by 1 on 2017/10/19.
 */

public interface IShopDetails {
    void getDetailsShop(int pid);
    void setShopInfo(ShopDetailsBean bean);
    void getShoppingCard(String uid,String pid,String sid);
}
