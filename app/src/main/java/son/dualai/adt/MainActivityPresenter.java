package son.dualai.adt;


import android.support.v4.app.Fragment;

import son.dualai.R;
import son.dualai.adt.beijing.BeijingFragment;
import son.dualai.adt.hangzhou.HangzhouFragment;
import son.dualai.adt.shanghai.ShanghaiFragment;
import son.dualai.adt.shenzheng.ShenzhengFragment;
import son.dualai.mvp.BaseMvpPresenter;

/**
 * Created on 2019/3/7.
 */
public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.Iview> implements IMainActivityContract.IPresenter {

    private int mCurrentFragmentIndex; //当前fragment下标

    private Fragment[] mFragments = new Fragment[4];
    private int mCurrentCheckedId;
    private int mTopPosition;
    private int mBottomPositon;

    public MainActivityPresenter(IMainActivityContract.Iview v) {
        super(v);
    }

    @Override
    protected IMainActivityContract.Iview getEmptyView() {
        return IMainActivityContract.emptyView;
    }

    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex = 0;
        replaceFragment(mCurrentFragmentIndex);
    }

    //切换fragment方法
    @Override
    public void replaceFragment(int mCurrentFragmentIndex) {
        for (int i = 0; i < mFragments.length; i++) {
            if (mCurrentFragmentIndex != i) {
                if (mFragments[i] != null) {
                    hideFragment(mFragments[i]);
                }
            }
        }

        Fragment mFragment = mFragments[mCurrentFragmentIndex];
        if (mFragment != null) {
            addAndShowFragment(mFragment);
            setCurChecked(mCurrentFragmentIndex);
        } else {
            newCurrentFragment(mCurrentFragmentIndex);
            setCurChecked(mCurrentFragmentIndex);
        }
    }

    /**
     * 记录当前 下标
     *
     * @param mCurrentFragmentIndex
     */
    private void setCurChecked(int mCurrentFragmentIndex) {
        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex) {
            case 0:
                mCurrentCheckedId = R.id.rb_main_shanghai;
                mTopPosition = 0;
                break;
            case 1:
                mCurrentCheckedId = R.id.rb_main_hangzhou;
                mTopPosition = 1;
                break;
            case 2:
                mCurrentCheckedId = R.id.rb_main_nav_home_beijing;
                mBottomPositon = 2;
                break;
            case 3:
                mCurrentCheckedId = R.id.rb_main_nav_car_source_shenzhen;
                mBottomPositon = 3;
                break;
        }
    }

    /**
     * 创建fragment
     *
     * @param mCurrentFragmentIndex
     */
    private void newCurrentFragment(int mCurrentFragmentIndex) {
        android.support.v4.app.Fragment fragment = null;
        switch (mCurrentFragmentIndex) {
            case 0:
                fragment = new ShanghaiFragment();
                break;
            case 1:
                fragment = new HangzhouFragment();
                break;
            case 2:
                fragment = new BeijingFragment();
                break;
            case 3:
                fragment = new ShenzhengFragment();
                break;
        }
        mFragments[mCurrentFragmentIndex] = fragment;
        addAndShowFragment(fragment);
    }

    /**
     * 显示fragment
     *
     * @param mFragment
     */
    private void addAndShowFragment(Fragment mFragment) {
        if (mFragment.isAdded()) {
            getView().showFragment(mFragment);
        } else {
            getView().addFragment(mFragment);
        }
    }

    /**
     * 隐藏fragment
     *
     * @param mFragment
     */
    private void hideFragment(Fragment mFragment) {
        if(mFragment != null && mFragment.isVisible()){
            getView().hideFragment(mFragment);
        }
    }

    @Override
    public int getCurrentCheckedId() {
        return mCurrentCheckedId;
    }

    @Override
    public int getCurrentCheckedIndex() {
        return mCurrentFragmentIndex;
    }

    @Override
    public int getTopPosition() {
        return mTopPosition;
    }

    @Override
    public int getBottomPosition() {
        return mBottomPositon;
    }
}
