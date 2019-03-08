package son.dualai.mvp2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Created on 2019/3/8.
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView<P>{

    protected P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        if(mPresenter != null){
            mPresenter.attachView(this);
            mPresenter.setContext(getApplicationContext());
        }
    }

    @Override
    protected void onDestroy() {
        if(mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}
