package son.touch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import son.dualai.Util;

public class XtFrameLayout extends FrameLayout {
    public XtFrameLayout(@NonNull Context context) {
        super(context);
    }

    public XtFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XtFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean flg = super.onTouchEvent(event);
        Log.d(Util.TAG,"flg:"+flg);
        return flg;
    }
}
