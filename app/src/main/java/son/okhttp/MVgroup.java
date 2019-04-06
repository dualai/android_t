package son.okhttp;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import son.dualai.Util;

/**
 * Created on 2019/3/28.
 */
public class MVgroup extends LinearLayout {
    public MVgroup(Context context) {
        super(context);
    }

    public MVgroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MVgroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.d(Util.TAG," 0 dispatchDraw...");
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(Util.TAG,"0 onDraw...");
    }
}
