package son.dualai.adt;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import son.dualai.R;
import son.dualai.Util;
import son.dualai.adt.base.BaseActivity;

@ViewInject(mainlayoutid = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.Iview {
    IMainActivityContract.IPresenter mPresenter = new MainActivityPresenter(this);

    @BindView(R.id.fac_main_home)
    FloatingActionButton facMainHome;
    @BindView(R.id.rb_main_shanghai)
    RadioButton rbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    RadioButton rbMainHangzhou;
    @BindView(R.id.rg_main_top)
    RadioGroup rgMainTop;
    @BindView(R.id.fl_main_bottom)
    FrameLayout flMainBottom;
    @BindView(R.id.rb_main_nav_home_beijing)
    RadioButton rbMainNavHomeBeijing;
    @BindView(R.id.rb_main_nav_car_source_shenzhen)
    RadioButton rbMainNavCarSourceShenzhen;
    @BindView(R.id.rg_main_bottom)
    RadioGroup rgMainBottom;

    private boolean isChangeTopOrBottom;


    @Override
    public void afterBindView() {
        initHomeFragment();
        changeAnima(rgMainBottom, rgMainTop);
        initCheckListener();
    }

    private void initCheckListener() {
        rbMainShanghai.setChecked(true);
        rgMainTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mPresenter.getCurrentCheckedId()) {
                    return;
                }
                switch (checkedId) {
                    case R.id.rb_main_shanghai:
                        mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
                        break;
                    case R.id.rb_main_hangzhou:
                        mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
                        break;
                }
            }
        });

        rgMainBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mPresenter.getCurrentCheckedId()) {
                    return;
                }
                switch (checkedId) {
                    case R.id.rb_main_nav_home_beijing:
                        mPresenter.replaceFragment(MainConstantTool.BEIJING);
                        break;
                    case R.id.rb_main_nav_car_source_shenzhen:
                        mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
                        break;
                }
            }
        });
    }

    private void initHomeFragment() {
        mPresenter.initHomeFragment();
    }

    @OnClick(R.id.fac_main_home)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fac_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if (isChangeTopOrBottom) {
                    changeAnima(rgMainTop, rgMainBottom);
                    handleTopPosition();
                } else {
                    changeAnima(rgMainBottom, rgMainTop);
                    handleBottomPosition();
                }
                break;
        }
    }

//    //北京 深圳
//    private void handleBottomPosition() {
//        if(mPresenter.getCurrentCheckedIndex() > 1){
//            mPresenter.replaceFragment(0);
//            rbMainShanghai.setChecked(true);
//        } else {
//            mPresenter.replaceFragment(mPresenter.getCurrentCheckedIndex());
//        }
//    }
//
//    //shanghai 杭州
//    private void handleTopPosition() {
//        if (mPresenter.getCurrentCheckedIndex() < 2) {
//            mPresenter.replaceFragment(2);
//            rbMainNavHomeBeijing.setChecked(true);
//        } else {
//            mPresenter.replaceFragment(mPresenter.getCurrentCheckedIndex());
//        }
//    }

    //北京 深圳
    private void handleBottomPosition() {
        if (mPresenter.getTopPosition() != 1) {
            mPresenter.replaceFragment(0);
            rbMainShanghai.setChecked(true);
        }else {
            mPresenter.replaceFragment(1);
            rbMainHangzhou.setChecked(true);
        }
    }

    //shanghai 杭州
    private void handleTopPosition() {
        if (mPresenter.getBottomPosition() != 3) {
            mPresenter.replaceFragment(2);
            rbMainNavHomeBeijing.setChecked(true);
        }else {
            mPresenter.replaceFragment(3);
            rbMainNavCarSourceShenzhen.setChecked(true);
        }
    }

    private void changeAnima(RadioGroup gone, RadioGroup show) {
        //消失的动画
        gone.clearAnimation(); //清楚自身动画
        Animation animationGone = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);

        //展示的动画
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }


    @Override
    public void showFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
    }

    @Override
    public void addFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content, mFragment).commit();
    }

    @Override
    public void hideFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }
}
