package son.dualai.adt.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import son.dualai.adt.ViewInject;
import son.dualai.mvp.LifeCircleMvpActivity;

public abstract class BaseActivity extends LifeCircleMvpActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInject annotation =  this.getClass().getAnnotation(ViewInject.class);
        if(annotation != null){
            int mainlayoutid = annotation.mainlayoutid();
            if(mainlayoutid > 0){
                setContentView(mainlayoutid);
                bindView();
                afterBindView();
            }else{
                throw new RuntimeException("mainlayoutid < 0");
            }
        }else{
            throw new RuntimeException("annotation == null");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // View 的依赖注入绑定
    private void bindView() {
        ButterKnife.bind(this);
    }

    // 模板方法 设计模式
    public abstract void afterBindView();

}
