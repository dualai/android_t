package son.dualai.mvp2.pager.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import son.dualai.R;
import son.dualai.mvp2.base.BaseMvpActivity;
import son.dualai.mvp2.bean.LoginBean;
import son.dualai.mvp2.bean.ResultBean;
import son.dualai.mvp2.component.ProgressDialog;
import son.dualai.mvp2.contract.LoginContract;
import son.dualai.mvp2.core.ApiDataEntity;
import son.dualai.mvp2.presenter.LoginPresenter;
import son.dualai.mvp2.util.ViewLayoutId;

/**
 * Created on 2019/3/8.
 */
@ViewLayoutId(layoutId = R.layout.activity_login)
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.IView {
    @BindView(R.id.et_username_login)
    TextInputEditText etUsernameLogin;
    @BindView(R.id.et_password_login)
    TextInputEditText etPasswordLogin;

    @Override
    public void afterBindView() {

    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(this);
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void showToast(String message, int time) {
        Toast.makeText(getApplicationContext(),message,time).show();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @OnClick(R.id.btn_signin_login)
    public void onViewClicked() {
        String username = etUsernameLogin.getText().toString();
        String password = etPasswordLogin.getText().toString();
        mPresenter.login(username, password);
    }
}
