package son.glide2;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;

import son.dualai.R;
import son.dualai.Util;

/**
 * Created on 2019/3/29.
 */
public class GlideActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide2);

        Handler handler;

        WindowManager wm = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Log.d(Util.TAG,wm+" abc ");


    }
}
