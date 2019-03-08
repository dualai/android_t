package son.dualai.mvp2.contract;

import son.dualai.mvp2.base.BasePresenter;
import son.dualai.mvp2.base.BaseView;
import son.dualai.mvp2.bean.LoginBean;
import son.dualai.mvp2.bean.ResultBean;
import son.dualai.mvp2.core.ApiDataEntity;
import son.dualai.mvp2.core.ApiServer;
import son.dualai.mvp2.presenter.LoginPresenter;

/**
 * Created on 2019/3/8.
 */
public interface LoginContract {
    interface IModel {
        void login(String username, String password, ApiServer.ApiCallback callback);
    }

    interface IView extends BaseView<LoginPresenter> {
        void showLoading();

        void hideLoading();

        void showToast(String message,int time);

        void showDialog();
    }

    interface IPresenter{
        /**
         * 登陆
         * @param username
         * @param password
         */
        void login(String username, String password);
    }
}
