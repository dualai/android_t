package son.dualai.mvp;

import android.content.Intent;
import android.os.Bundle;

/**P 层中间类做这一层的意义，如果直接继承LifeCircleMvpPresenter，每次具体中介者都要实现所有的方法，所以嫁接一个抽象类在中间
 * Created on 2019/3/7.
 */
public abstract class BaseMvpPresenter<T extends IMvpView> extends LifeCircleMvpPresenter<T>{


    // 加抽象类，不复写她
    //    @Override
//    protected T getEmptyView() {
//        return null;
//    }


    public BaseMvpPresenter(T v) {
        super(v);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }
}
