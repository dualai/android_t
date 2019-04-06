package son.okhttp;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import son.dualai.Util;

/**
 * Created on 2019/3/28.
 */
public class MView extends View {
    public MView(Context context) {
        super(context);
    }

    public MView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(Util.TAG,"view onDraw...");
    }
}
