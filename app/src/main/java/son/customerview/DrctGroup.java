package son.customerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created on 2019/3/10.
 */
public class DrctGroup extends ViewGroup {

    private final int singleLeftMargin = 100; //100px

    public DrctGroup(Context context) {
        super(context);
    }

    public DrctGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrctGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //挨个测量子控件
        int childCount = getChildCount();

        int mTmpWidth = 0;
        int mTmpHeight = 0;

        int leftMargin = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            final ViewGroup.LayoutParams params = child.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, 0, params.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, 0, params.height);
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            mTmpWidth = Math.max(mTmpWidth,leftMargin + childWidth);
            leftMargin += childWidth / 2;
            mTmpHeight += childHeight;
        }

        int mWidth = 0;
        int mHeight = 0;
        switch (widthMode) {
            case MeasureSpec.EXACTLY: {
                mWidth = widthSize;
                break;
            }
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                mWidth = mTmpWidth;
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY: {
                mHeight = heightSize;
                break;
            }
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                mHeight = mTmpHeight;
                break;
        }

        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int top = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            child.layout(left,top,left + childWidth,top + childHeight);
            left += childWidth / 2;
            top += childHeight;
        }

    }
}
