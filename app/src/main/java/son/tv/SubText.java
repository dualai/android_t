package son.tv;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import son.dualai.Util;

/**
 * Created on 2019/3/13.
 */
public class SubText extends AppCompatTextView {
    public SubText(Context context) {
        super(context);
    }

    public SubText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SubText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        return true;
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        return super.requestFocus(direction, previouslyFocusedRect);
    }
}
