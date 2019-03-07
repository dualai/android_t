package son.dualai.touchevent;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import son.dualai.Util;

public class CustomerFrameLayout extends FrameLayout {
    public CustomerFrameLayout(Context context) {
        super(context);
    }

    public CustomerFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(Util.TAG,"CustomerFrameLayout onInterceptTouchEvent:"+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(Util.TAG,"CustomerFrameLayout dispatchTouchEvent:"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(Util.TAG,"CustomerFrameLayout onTouchEvent:"+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
