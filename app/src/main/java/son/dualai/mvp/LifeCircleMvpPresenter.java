package son.dualai.mvp;

import java.lang.ref.WeakReference;

/**
 * Created on 2019/3/6.
 * Top 基类P层
 */
public abstract class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle {

    /**
     * P层关联V层的弱引用，并且，P层还把自己扔到 mvpControler对象的Set集合中，最后由V层关联mvpControler对象
     */
    //关联view的弱引用
    protected WeakReference<T> weakReference;

    protected LifeCircleMvpPresenter() {
        super();
    }

    public LifeCircleMvpPresenter(IMvpView iMvpView) {
        super();
        attachView(iMvpView);
        MvpController mvpController = iMvpView.getMvpController();
        mvpController.savePresenter(this);
    }


    @Override
    public void attachView(IMvpView iMvpView) {
        if (weakReference == null) {
            weakReference = new WeakReference(iMvpView);
        } else { //如果不是同一个view，重新关联
            T view = weakReference.get();
            if (view != iMvpView) {
                weakReference = new WeakReference(iMvpView);
            }
        }
    }

    @Override
    public void onDestroy() {
        //清空弱引用
        weakReference = null;
    }


    protected T getView(){
        T view = weakReference != null ? weakReference.get() :null;
        if(view == null){
            return getEmptyView();
        }
        return view;
    }

    protected abstract T getEmptyView();


}
