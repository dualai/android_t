package son.keepalive;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import son.dualai.Util;

/**
 * Created on 2019/3/19.
 */
public class KeepAliveActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams attr = window.getAttributes();
        attr.width = 20;
        attr.height = 20;
        attr.x = 0;
        attr.y = 0;
        findViewById(android.R.id.content).setBackgroundColor(Color.parseColor("#ff0000"));
        window.setAttributes(attr);

        KeepManager.getInstance().setKeep(this);

        Log.d(Util.TAG,getClass().getName()+" onCreate");


    }
}
