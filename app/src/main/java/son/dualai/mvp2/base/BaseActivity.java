package son.dualai.mvp2.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import son.dualai.mvp2.util.ViewLayoutId;

/**
 * Created on 2019/3/8.
 */
public abstract class BaseActivity extends Activity {
    protected Unbinder unBinderBk;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewLayoutId viewLayoutId = this.getClass().getAnnotation(ViewLayoutId.class);
        if(viewLayoutId != null){
            int layoutId = viewLayoutId.layoutId();
            if(layoutId > 0){
                setContentView(layoutId);
                bindView();
                afterBindView();
            }else{
                throw new RuntimeException("layoutId < 0");
            }
        }else{
            throw new RuntimeException("annotation == null");
        }
    }

    @Override
    protected void onDestroy() {
        if(unBinderBk != null){
            unBinderBk.unbind();
        }
        super.onDestroy();
    }

    // View 的依赖注入绑定
    private void bindView() {
        unBinderBk = ButterKnife.bind(this);
    }

    // 模板方法 设计模式
    public abstract void afterBindView();
}
