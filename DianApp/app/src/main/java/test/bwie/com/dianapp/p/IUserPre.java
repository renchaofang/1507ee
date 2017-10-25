package test.bwie.com.dianapp.p;

/**
 * Created by 1 on 2017/10/17.
 */

public interface IUserPre {

    void getDta(String name, String pass);
    void getLoginUser(String name , String pass);
    void getShopInfo(String title,int page);
    void getFirstShow(int cid);
    void getDetails(int pid);
    //添加购物车
    void getCard(String uid,String pid,String sid);
    //查询购物车
    void findcard(String uid);
    //得到订单
    void getOrderP(String uid,String price);
    //查询订单号
    void getFindOrder(String uid,int tid);
    //得到首页轮播图
    void getLunBo();

}
