package test.bwie.com.dianapp.wxapi;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

import test.bwie.com.dianapp.R;

/**
 * Created by 1 on 2017/10/11.
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    public void onReq(BaseReq baseReq) {
    }
    @Override
    public void onResp(BaseResp resp) {
        Log.d("xxx", "onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("");
            builder.setMessage(getString(R.string.pay_result_callback_msg, resp.errStr + ";code=" + String.valueOf(resp.errCode)));
            builder.show();
        }
    }
}
