package test.bwie.com.dianapp.v;

import org.json.JSONArray;

/**
 * Created by 1 on 2017/10/20.
 */

public interface IFindShop {
    void getFindShop(String uid);
    void getAddCard(JSONArray jsonArray);
    void getOrderInfo(String uid, String price);
    //查询订单详情
    void FindOrder(String uid,int tid);
    //微信支付
    void WeiXIN(String p_id);
    //支付宝支付
    void ZhiFuBao(String p_id);
}
