package son.dualai.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created on 2019/3/7.
 */
public class LifeCircleMvpActivity extends AppCompatActivity implements IMvpView{
    private MvpController mvpController; //持有P层的静态代理

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        if (intent == null) {
            intent = new Intent();
        }
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onCreate(savedInstanceState,intent,null);
            mvpControler.onActivityCreated(savedInstanceState,intent,null);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onNewIntent(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        MvpController mvpControler = this.getMvpController();
        if (mvpControler != null) {
            mvpControler.onStop();
        }
    }

    @Override
    protected void onDestroy() {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
