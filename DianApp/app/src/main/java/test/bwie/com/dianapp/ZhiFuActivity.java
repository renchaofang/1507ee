package test.bwie.com.dianapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import cz.msebera.android.httpclient.Header;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.dianapp.adapter.ChangePayApater;
import test.bwie.com.dianapp.aliplay.PayResult;
import test.bwie.com.dianapp.aliplay.SignUtils;
import test.bwie.com.dianapp.bean.PayItemData;
import test.bwie.com.dianapp.p.UserPre;
import test.bwie.com.dianapp.v.IFindShop;
import test.bwie.com.okhttoutils_rcf.Utils.OkHttp3Utils;

public class ZhiFuActivity extends AppCompatActivity implements IFindShop,View.OnClickListener{

    // 商户PID
    public static final String PARTNER = "2088901305856832";
    // 商户收款账号
    public static final String SELLER = "8@qdbaiu.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM" +
            "/KCxg/OIj6er2GEig0DOkHqBSzEPHGigMbTXP1k2nrxEHeE59xOOuy" +
            "ovQH/A1LgbuVKyOac3uAN4GXIBEoozRVzDhu5dobeNm48BPcpYSAfvN3K" +
            "/5GLacvJeENqsiBx8KufM/9inlHaDRQV7WhX1Oe2ckat1EkdHwxxQgc" +
            "36NhAgMBAAECgYEAwn3sWpq6cUR65LD8h9MIjopTImTlpFjgz72bhsHD" +
            "ZK6A+eJDXcddrwh7DI34t/0IBqu+QEoOc/f0fIEXS9hMwTvFY59XG7M8" +
            "M6SdeaAbemrGmZ1IdD6YDmpbQFHn2ishaYF0YDZIkBS3WLDFrtk/efaar" +
            "BCpGAVXeEiVQE4LewECQQD5W1rpkq+dHDRzzdtdi1bJ479wun5CfmVDV" +
            "b2CJH7Iz0t8zyp/iEVV2QELnxZMphmoSzKaLXutTTj2OImpzCtRAkEA1" +
            "VMxG6nQq9NkU51H1+I3mlUXRZ0XeFA1GFJ7xWpNRAVhEWlDz2zy9v/g" +
            "X+RFpNC3f5uznycas70Xp78ew43TEQJAZFFqi9mlqTF1sLk67bFnIyX" +
            "rGPEOZrXvC13tNfR0xVkQZ4/46wHp0xXQo9pG4GNaoyhNnVV7EkelCPn" +
            "J+HPZYQJAQh6T9QgQZoGR8hyovPAf3dUL7oa/VIo/urcuJ8VIB5JHQNdI" +
            "rk0NjaNHj1E4iNosVgATj3vWWel9IIArb99QkQJAKvfm78lwnImtg5IM6" +
            "04hdn/Wu1XF8tpxsKLWcnfchMr0bM9rCmKmhAY+wdmqSyPZRiNb1QaaaD" +
            "TqJxLy6AnQ+Q==";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCd6rV3vOE578e6V" +
            "lGEakZpPdsX2QmGdIfi/yHe cg1CIEWzX9wn2LNFGtu1EzYQyKACG/RKeog0pUJEVGfBG30zFdNY2YocYJNdPtA" +
            "DqhJbS0GJm7f8 1vRiLKtOwKjdiz9oMEwxhc/5fysfMbercidRmlCDPU9BNL1UPb9bAx25JwIDAQAB";
    private static final int SDK_PAY_FLAG = 1;
    //阿里云服务器，用真机测试
    private static final String URL_JSON = "http://101.200.142.201:8080/alipayServer/AlipayDemo";
    //private static final String URL_JSON = "http://192.168.190.1:8080/alipayServer/AlipayDemo";
    //本机服务器，用真机访问不到，用模拟器能访问，但是支付宝不允许模拟器支付
//	private static final String URL_JSON = "http://169.254.63.148:8080/alipayServer/AlipayDemo";

    private TextView tv_paymoney;
    private TextView tv_paypay;
    private ListView pay_list;
    private Button btn_pay;
    private UserPre userPre;
    private String uid;
    private String price;
    private IWXAPI api;
    List<PayItemData> payitem_list=new ArrayList<>();
    @SuppressLint("unused")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(ZhiFuActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(ZhiFuActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(ZhiFuActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_zhi_fu);
        //  商户APP工程中引入微信JAR包，调用API前，需要先向微信注册您的APPID
        api = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        api.registerApp("wx8d99aede85c725a5");//wx8d99aede85c725a5


        Intent intent = getIntent();
        price = intent.getStringExtra("price");
        uid = intent.getStringExtra("uid");
        Toast.makeText(ZhiFuActivity.this, price +"...."+ uid,Toast.LENGTH_SHORT).show();
        userPre = new UserPre(ZhiFuActivity.this);
        getOrderInfo(uid, price);
        init(price);
        initData();
        ChangePayApater changepayadapter=new ChangePayApater(ZhiFuActivity.this,payitem_list);
        pay_list.setAdapter(changepayadapter);
    }
    public void init(String price){
        tv_paymoney = (TextView) findViewById(R.id.tv_paymoney);
        tv_paymoney.setText(price+"¥");
        tv_paypay = (TextView) findViewById(R.id.tv_paypay);
        tv_paypay.setText(price+"¥");
        pay_list = (ListView) findViewById(R.id.pay_list);
        btn_pay = (Button) findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(this);
    }
    private void initData() {
        payitem_list.add(new PayItemData("微信支付","微信支付，银行卡支付",R.mipmap.weixin,true));
        payitem_list.add(new PayItemData("支付宝支付","支付宝支付，银行卡支付",R.mipmap.allipay,false));
    }
    @Override
    public void getFindShop(String uid) {
    }
    @Override
    public void getAddCard(JSONArray jsonArray) {

    }
    //进行提交订单
    @Override
    public void getOrderInfo(String uid, String price) {
            userPre.getOrderP(uid,price);
    }
    //进行查询的达到订单
    @Override
    public void FindOrder(String uid,int tid) {
        userPre.getFindOrder(uid,tid);
    }
    @Override
    public void WeiXIN(String pid) {
        Toast.makeText(this, "微信支付", Toast.LENGTH_SHORT).show();
        wechatPay(pid);
    }
    @Override
    public void ZhiFuBao(String pid) {
        Toast.makeText(this, "支付宝支付", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pay:
                //进行支付
                int paypay_item=0;
                for (int i = 0; i <payitem_list.size() ; i++) {
                    boolean checked = payitem_list.get(i).isChecked();
                    if (checked==true){
                        paypay_item=i;
                        break;
                    }
                }
                switch (paypay_item){
                    case 0:
                        FindOrder(uid,0);
                        //wechatPay("");
                        break;
                    case 1:
                        FindOrder(uid,1);
                        paySignFromClient();
                        break;
                }
                break;
        }
    }

    //微信支付
    private void wechatPay(String order_sn) {
        String url = "http://www.55chai.com/morder/app_pay?order_sn=" + order_sn;//获取微信支付参数的服务端地址
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getApplicationContext(), url, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "订单生成失败", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i("xml", responseString + "");
                try {
                    JSONObject jsonObject = new JSONObject(responseString);
                    if (jsonObject.getInt("code") == 200) {//正常获取了订单所需参数
                        JSONObject paramsJson = jsonObject.getJSONObject("data");
                        PayReq request = new PayReq();
                        request.appId = paramsJson.getString("appid");
                        request.partnerId = paramsJson.getString("partnerid");
                        request.prepayId = paramsJson.getString("prepayid");
                        request.packageValue = paramsJson.getString("package");
                        request.nonceStr = paramsJson.getString("noncestr");
                        request.timeStamp = paramsJson.getLong("timestamp") + "";
                        request.sign = paramsJson.getString("sign");

                        api.sendReq(request);
                    } else {
                        Toast.makeText(getApplicationContext(), "获取订单信息失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "生成支付失败", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
    //支付宝支付
    private void paySignFromClient() {
       /* //获得订单信息
        String orderInfo = getOrderInfo("来自Client测试商品", "购买一部手机", "0.01");
        //进行加密签名
        String sign = sign(orderInfo);
        //通过URLEncoder进行编码
        try {
            sign = URLEncoder.encode(sign, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        //拼装最终的支付信息
        String path = "http://169.254.250.197:8080/PayServer/AlipayDemo";
        StringBuffer sb = new StringBuffer("?");
        /*sb.append("&sign=\"");
        sb.append(sign);
        sb.append("\"&");
        sb.append(getSignType());*/
        sb.append("subject=");
        sb.append("来自Server测试的商品");
        sb.append("&");
        sb.append("body=");
        sb.append("该测试商品的详细描述");
        sb.append("&");
        sb.append("total_fee=");
        sb.append("20000.0");
        //获取必须来自服务端
        final String payInfo = sb.toString();//获得最终的支付信息
        String path1 = path+payInfo;
        OkHttp3Utils.doGet(path1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // 构造PayTask 对象
                        PayTask alipay = new PayTask(ZhiFuActivity.this);
                        // 调用支付接口，获取支付结果
                        String result = alipay.pay(string, true);
                        Log.i("TAG", "走了pay支付方法.............");
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });
    }
    /**
     * get the sdk version. 获取SDK版本号
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }
    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";
        return orderInfo;
    }
    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }
    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }
}
