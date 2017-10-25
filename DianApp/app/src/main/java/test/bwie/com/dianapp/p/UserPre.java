package test.bwie.com.dianapp.p;

import test.bwie.com.dianapp.m.IUserModle;
import test.bwie.com.dianapp.m.UserModle;
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

public class UserPre implements IUserPre{

    private ILunBoView iLunBoView;
    private IFindShop iFindShop;
    private IShopDetails iShopDetails;
    private IFirstShop iFirstShop;
    private IShopInfo iShopInfo;
    private IUserLoginView iUserLoginView;
    private IUserView iUserView;
    private final IUserModle userfModle;

    public UserPre(IUserView iUserView) {
        this.iUserView=iUserView;
        userfModle = new UserModle();
    }
    public UserPre(IUserLoginView iUserLoginView) {
        this.iUserLoginView=iUserLoginView;
        userfModle = new UserModle();
    }
    public UserPre(IShopInfo iShopInfo) {
        this.iShopInfo=iShopInfo;
        userfModle = new UserModle();
    }
    public UserPre(IFirstShop iFirstShop) {
        this.iFirstShop=iFirstShop;
        userfModle = new UserModle();
    }

    //商品详情
    public UserPre(IShopDetails iShopDetails) {
        this.iShopDetails=iShopDetails;
        userfModle = new UserModle();
    }
    //查询购物车
    public UserPre(IFindShop iFindShop) {
        this.iFindShop=iFindShop;
        userfModle = new UserModle();
    }
    //轮播图
    public UserPre(ILunBoView iLunBoView) {
        this.iLunBoView=iLunBoView;
        userfModle = new UserModle();
    }

    //用户注册的操作
    @Override
    public void getDta(String name,String pass) {
        userfModle.getUserInfo(name,pass,iUserView);
    }
    //用户的登录操作
    @Override
    public void getLoginUser(String name, String pass) {
            userfModle.getUserLogin(name,pass,iUserLoginView);
    }
    @Override
    public void getShopInfo(String title, int page) {
        userfModle.getShop(title,page,iShopInfo);
    }

    @Override
    public void getFirstShow(int cid) {
        userfModle.getFirst(cid,iFirstShop);
    }

    @Override
    public void getDetails(int pid) {
            userfModle.getDetailsSP(pid,iShopDetails);
    }

    //添加购物车
    @Override
    public void getCard(String uid, String pid, String sid) {
        userfModle.getCardShop(uid,pid,sid);
    }

    @Override
    public void findcard(String uid) {
        userfModle.getFindShopModle(uid,iFindShop);
    }

    @Override
    public void getOrderP(String uid, String price) {
        userfModle.getOrderM(uid,price);
    }

    @Override
    public void getFindOrder(String uid,int tid) {
        userfModle.getOrderInfo(uid,tid,iFindShop);
    }

    @Override
    public void getLunBo() {
            userfModle.getLunBo(iLunBoView);
    }
}
