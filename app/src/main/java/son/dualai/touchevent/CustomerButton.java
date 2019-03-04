package son.dualai.touchevent;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import son.dualai.Util;

public class CustomerButton extends View {
    public CustomerButton(Context context) {
        super(context);
    }

    public CustomerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(Util.TAG,"CustomerButton dispatchTouchEvent:"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(Util.TAG,"CustomerButton onTouchEvent:"+event.getAction());
        return super.onTouchEvent(event);
    }
}
