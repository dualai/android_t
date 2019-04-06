package son.dualai.mvp2.component;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created on 2019/3/8.
 */
public class EpTextView extends AppCompatTextView {
    public EpTextView(Context context) {
        this(context,null);
    }

    public EpTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EpTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initContext(context,attrs);
    }

    private void initContext(Context context,AttributeSet attrs){

    }
}
