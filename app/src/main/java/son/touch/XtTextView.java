package son.touch;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import son.dualai.Util;

public class XtTextView extends AppCompatTextView {
    public XtTextView(Context context) {
        super(context);
    }

    public XtTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XtTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean flg = super.onTouchEvent(event);
        Log.d(Util.TAG,"2 flg:"+flg);
        return flg;
    }
}
