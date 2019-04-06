package son.keepalive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import son.dualai.Util;

/**
 * Created on 2019/3/19.
 */
public class KeepReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(Util.TAG, "action:" + action);
        if (TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)) {
            //关屏幕
            KeepManager.getInstance().startKeep(context);

        } else if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)) {
            //开屏幕
            KeepManager.getInstance().finishKeep();
        }
    }
}
