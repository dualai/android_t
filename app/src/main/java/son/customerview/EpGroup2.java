package son.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import son.dualai.Util;

/**
 * Created on 2019/3/9.
 */
public class EpGroup2 extends LinearLayout {
    public EpGroup2(Context context) {
        super(context);
    }

    public EpGroup2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EpGroup2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        Log.d(Util.TAG, "**********mode:" + getClass().getName()+" mode:" + Util.getMode(mode) + " size:"+MeasureSpec.getSize(widthMeasureSpec));
        Log.d(Util.TAG, " " + getClass().getName() + " onMeasure start");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(Util.TAG, " " + getClass().getName() + " onMeasure done");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(Util.TAG, " " + getClass().getName() + " onLayout start");
        super.onLayout(changed, l, t, r, b);
        Log.d(Util.TAG, " " + getClass().getName() + " onLayout done");
    }

//    @Override
//    protected void dispatchDraw(Canvas canvas) {
//        super.dispatchDraw(canvas);
//        Log.d(Util.TAG, " " + getClass().getName() + " dispatchDraw done");
//    }
}
