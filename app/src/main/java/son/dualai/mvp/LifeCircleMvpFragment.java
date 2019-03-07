package son.dualai.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created on 2019/3/7.
 */
public class LifeCircleMvpFragment extends Fragment implements IMvpView{
    private MvpController mvpController; //持有P层的静态代理

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if(bundle == null){
            bundle = new Bundle();
        }

        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onCreate(savedInstanceState,null,bundle);
            mvpControler.onActivityCreated(savedInstanceState,null,bundle);
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle == null){
            bundle = new Bundle();
        }

        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onActivityCreated(savedInstanceState,null,bundle);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onViewDestroy();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onActivityResult(requestCode,resultCode,data);
        }
    }


    @Override
    public MvpController getMvpController() {
        if(this.mvpController == null){
            this.mvpController = new MvpController();
        }
        return this.mvpController;
    }

}
