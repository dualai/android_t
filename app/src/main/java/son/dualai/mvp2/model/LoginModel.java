package son.dualai.mvp2.model;

import android.util.Log;

import son.dualai.Util;
import son.dualai.mvp2.bean.ErrorBean;
import son.dualai.mvp2.bean.ResultBean;
import son.dualai.mvp2.contract.LoginContract;
import son.dualai.mvp2.core.ApiDataEntity;
import son.dualai.mvp2.core.ApiServer;

/**
 * Created on 2019/3/8.
 */
public class LoginModel implements LoginContract.IModel {
    @Override
    public void login(String username, String password, final ApiServer.ApiCallback apiCallback) {
        ApiServer.getInstance().apiLogin(username, password, new ApiServer.HttpCallback() {
            @Override
            public void onHttpCallback(boolean success, String result, int resultCode) {
                boolean isApiSuccess = false;
                if (!success) {
                    ErrorBean errorBean = new ErrorBean();
                    errorBean.errorMsg = "网络请求失败...";
                    errorBean.errorType = ErrorBean.ErrorTypeHttp;
                    ResultBean<ApiDataEntity.ApiLoginData> resultBean = new ResultBean<>();
                    resultBean.result = null;
                    resultBean.error = errorBean;
                    apiCallback.onApiCallback(false, resultBean);
                } else {
                    Log.d(Util.TAG, result);
                    //TODO 1 处理登录成功后的逻辑，比如持久化token等？？？

                    //TODO 2 回调

                }
            }
        });
    }
}
