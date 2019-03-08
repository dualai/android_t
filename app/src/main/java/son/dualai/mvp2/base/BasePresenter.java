package son.dualai.mvp2.base;

import android.content.Context;
import android.util.Log;

import java.lang.ref.WeakReference;

import son.dualai.Util;

/**
 * Created on 2019/3/8.
 */
public class BasePresenter<V extends BaseView> {
    protected WeakReference<V> mWeakView;
    public Context mContext;
    /**
     * 绑定View，初始化的时候调用
     *
     * @param view
     */
    public void attachView(V view) {
        Log.d(Util.TAG,"attachView...");
        this.mWeakView = new WeakReference<>(view);
    }

    /**
     * 解除绑定的时候调用
     */
    public void detachView() {
        Log.d(Util.TAG,"detachView...");
        mWeakView = null;
    }

    public V getView() {
        if (mWeakView == null) {
            throw new RuntimeException("no bindView to presenter");
        }
        return mWeakView.get();
    }

    public void setContext(Context context){
        this.mContext = context;
    }
}
