package son.dualai.mvp2.presenter;

import android.text.TextUtils;
import android.widget.Toast;

import com.socks.library.KLog;

import son.dualai.Util;
import son.dualai.mvp2.base.BasePresenter;
import son.dualai.mvp2.bean.ResultBean;
import son.dualai.mvp2.contract.LoginContract;
import son.dualai.mvp2.core.ApiDataEntity;
import son.dualai.mvp2.core.ApiServer;
import son.dualai.mvp2.core.INetwork;
import son.dualai.mvp2.model.LoginModel;

/**
 * Created on 2019/3/8.
 */
public class LoginPresenter extends BasePresenter<LoginContract.IView> implements LoginContract.IPresenter {

    private LoginContract.IModel model;

    public LoginPresenter() {
        model = new LoginModel();
    }

    @Override
    public void login(String username, String password) {
        username = username != null ? username.trim() : null;
        password = password != null ? password.trim() : null;
        username = "test123321";
        password = username;
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            getView().showToast("用户名或者密码不可以为空...", Toast.LENGTH_SHORT);
            return;
        }
        getView().showLoading();

        model.login(username, password, new INetwork.ApiCallback<ApiDataEntity.ApiLoginData>() {
            @Override
            public void onApiCallback(boolean success, ResultBean<ApiDataEntity.ApiLoginData> resultBean) {
                getView().hideLoading();
                if(success){
                    KLog.d(Util.TAG,resultBean.result.contentString);
                    KLog.d(Util.TAG,resultBean.result.loginBean.getCollectIds().getClass());
                    KLog.d(Util.TAG,resultBean.result.loginBean.getId());
                }else{

                }
            }
        });
    }
}
