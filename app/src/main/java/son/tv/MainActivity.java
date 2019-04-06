package son.tv;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;

import son.dualai.R;
import son.dualai.Util;

/**
 * Created on 2019/3/13.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_main);


    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.d(Util.TAG, getClass().getName() + " dispatchKeyEvent keyEvent:" + event.getKeyCode());
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(Util.TAG,getClass().getName()+" onKeyDown keyEvent:"+event.getKeyCode());
        return super.onKeyDown(keyCode, event);
    }
}
