package son.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import son.dualai.Util;

/**
 * Created on 2019/3/9.
 */
public class EpTextView2 extends AppCompatTextView {
    public EpTextView2(Context context) {
        super(context);
    }

    public EpTextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EpTextView2(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public void layout(int l, int t, int r, int b) {
        Log.d(Util.TAG, " " + getClass().getName() + " layout start");
        super.layout(l, t, r, b);
        Log.d(Util.TAG, " " + getClass().getName() + " layout end");
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        Log.d(Util.TAG, " " + getClass().getName() + " onDraw done");
//    }
}
