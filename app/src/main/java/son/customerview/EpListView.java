package son.customerview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created on 2019/3/10.
 */
public class EpListView extends ListView {
    public EpListView(Context context) {
        super(context);
    }

    public EpListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EpListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
