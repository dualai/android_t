package son.keepalive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.lang.ref.WeakReference;

import son.dualai.Util;

/**
 * Created on 2019/3/19.
 */
public class KeepManager {
    private static final KeepManager outInstance = new KeepManager();

    public static KeepManager getInstance() {
        return outInstance;
    }

    private KeepManager() {

    }

    private KeepReceiver keepReceiver;

    private WeakReference<Activity> mKeepActivity;

    /**
     * 注册关屏 开屏
     */
    public void registerKeep(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);

        keepReceiver = new KeepReceiver();
        context.registerReceiver(keepReceiver, filter);

        Log.d(Util.TAG,"registerKeep...");
    }

    public void unRegisterKeep(Context context) {
        if (null != keepReceiver) {
            context.unregisterReceiver(keepReceiver);
            keepReceiver = null;
            Log.d(Util.TAG,"unRegisterKeep...");
        }
    }

    /**
     * 开始一像素的activity
     */
    public void startKeep(Context con) {
        Intent intent = new Intent(con, KeepAliveActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        con.startActivity(intent);
        Log.d(Util.TAG,"startKeep...");
    }


    public void finishKeep() {
        if (null != mKeepActivity) {
            Activity activity = mKeepActivity.get();
            if (null != activity) {
                activity.finish();
                mKeepActivity = null;
            }
            Log.d(Util.TAG,"finishKeep...");
        }
    }

    public void setKeep(KeepAliveActivity keep){
        mKeepActivity = new WeakReference<>(keep);
    }

}
