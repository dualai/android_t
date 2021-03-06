package son.dualai.adt;

import android.support.v4.app.Fragment;

import son.dualai.mvp.ILifeCircle;
import son.dualai.mvp.IMvpView;
import son.dualai.mvp.MvpController;

/**
 * Created on 2019/3/7.
 * 给双方暴露需要相互调用的方法
 * 将Model、View、Presenter 进行约束管理，方便后期类的查找、维护。
 */
public interface IMainActivityContract {
    interface Iview extends IMvpView {

        void showFragment(Fragment mFragment);

        void addFragment(Fragment mFragment);

        void hideFragment(Fragment mFragment);
    }

    interface IPresenter extends ILifeCircle {

        void initHomeFragment();

        int getCurrentCheckedId();

        void replaceFragment(int mCurrentFragmentIndex);

        int getCurrentCheckedIndex();

        int getTopPosition();

        int getBottomPosition();
    }

    Iview emptyView = new Iview() {

        @Override
        public void hideFragment(Fragment mFragment) {

        }

        @Override
        public void showFragment(Fragment mFragment) {

        }

        @Override
        public void addFragment(Fragment mFragment) {

        }

        @Override
        public MvpController getMvpController() {
            return null;
        }
    };
}
