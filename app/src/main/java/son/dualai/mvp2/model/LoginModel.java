package son.dualai.mvp2.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.socks.library.KLog;

import son.dualai.Util;
import son.dualai.mvp2.bean.ErrorBean;
import son.dualai.mvp2.bean.LoginBean;
import son.dualai.mvp2.bean.ResultBean;
import son.dualai.mvp2.contract.LoginContract;
import son.dualai.mvp2.core.ApiDataEntity;
import son.dualai.mvp2.core.ApiServer;
import son.dualai.mvp2.core.INetwork;

/** 这一层可以考虑直接写在Presenter中
 * Created on 2019/3/8.
 */
public class LoginModel implements LoginContract.IModel {
    @Override
    public void login(String username, String password, final INetwork.ApiCallback apiCallback) {
        ApiServer.getInstance().apiLogin(username, password, new INetwork.HttpCallback() {
            @Override
            public void onHttpCallback(boolean success, String result, int resultCode) {

                boolean isApiSuccess = false;
                if (!isApiSuccess) {
                    ErrorBean errorBean = new ErrorBean();
                    errorBean.errorMsg = "网络请求失败...";
                    errorBean.errorType = ErrorBean.ErrorTypeHttp;
                    ResultBean<ApiDataEntity.ApiLoginData> resultBean = new ResultBean<>();
                    resultBean.result = null;
                    resultBean.error = errorBean;
                    apiCallback.onApiCallback(false, resultBean);
                } else {
                    JSONObject resultJson = JSON.parseObject(result);
                    //TODO 0 处理一些是否为空等逻辑...

                    //TODO 1 处理登录成功后的逻辑，比如持久化token等？？？

                    //TODO 2 回调
                    final LoginBean loginData = JSON.parseObject(resultJson.get("data").toString(), LoginBean.class); //这样子就把inputstream流直接转换成对象
                    ApiDataEntity.ApiLoginData apiLoginData = new ApiDataEntity.ApiLoginData();
                    apiLoginData.contentString = loginData.toString();
                    apiLoginData.loginBean = loginData;

                    ResultBean<ApiDataEntity.ApiLoginData> resultBean = new ResultBean<>();
                    resultBean.error = null;
                    resultBean.result = apiLoginData;
                    apiCallback.onApiCallback(true,resultBean);
                }
            }
        });
    }
}
