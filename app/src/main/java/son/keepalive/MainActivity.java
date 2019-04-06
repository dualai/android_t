package son.keepalive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import son.keepalive.service.ForegroundService;

/**
 * Created on 2019/3/19.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //通过1像素activity进行提权
        KeepManager.getInstance().registerKeep(this);

        //前台服务
//        startService(new Intent(this,ForegroundService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepManager.getInstance().unRegisterKeep(this);
    }
}
