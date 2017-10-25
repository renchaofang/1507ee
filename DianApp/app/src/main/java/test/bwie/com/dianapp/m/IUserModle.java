package test.bwie.com.dianapp.m;

import test.bwie.com.dianapp.v.IFindShop;
import test.bwie.com.dianapp.v.IFirstShop;
import test.bwie.com.dianapp.v.ILunBoView;
import test.bwie.com.dianapp.v.IShopDetails;
import test.bwie.com.dianapp.v.IShopInfo;
import test.bwie.com.dianapp.v.IUserLoginView;
import test.bwie.com.dianapp.v.IUserView;

/**
 * Created by 1 on 2017/10/17.
 */

public interface IUserModle {
    void getUserInfo(String name , String pass, IUserView iUserView);
    void getUserLogin(String name, String pass, IUserLoginView iUserLoginView);
    void getShop(String title, int page, IShopInfo iShopInfo);
    void getFirst(int cid, IFirstShop iFirstShop);
    void getDetailsSP(int pid, IShopDetails iShopDetails);
    //添加购物车
    void getCardShop(String uid,String pid,String sid);
    //查询购物车
    void getFindShopModle(String uid, IFindShop iFindShop);
    //得到订单号
    void getOrderM(String uid,String price);
    //查询订单号
    void getOrderInfo(String uid,int tid,IFindShop iFindShop);

    void getLunBo(ILunBoView iLunBoView);
}
