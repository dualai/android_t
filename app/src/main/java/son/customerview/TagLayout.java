package son.customerview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import son.dualai.Util;

/**
 * Created on 2019/3/10.
 */
public class TagLayout extends ViewGroup {
    public TagLayout(Context context) {
        super(context);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = 800;
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(800, MeasureSpec.EXACTLY);
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //挨个测量子控件
        int childCount = getChildCount();

        int mPaddingLeft = getPaddingLeft();
        int mPaddingRight = getPaddingRight();
        int mPaddingTop = getPaddingTop();
        int mPaddingBottom = getPaddingBottom();

        int tmpRowLeft = mPaddingLeft;
        int tmpRowTop = mPaddingTop;
        int rowMaxHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            final MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            if (params.width == LayoutParams.MATCH_PARENT || params.height == LayoutParams.MATCH_PARENT) {
                throw new RuntimeException("child layoutParams can't be MATCH_PARENT");
            }

//            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, 0, params.width);
//            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, 0, params.height);
//            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            Log.d(Util.TAG, "No." + i + " childWidth:" + child.getMeasuredWidth() + " childHeight:" + child.getMeasuredHeight());

            int childWidthSpace = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeightSpace = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;

            Log.d(Util.TAG, "tmpRowLeft + childWidthSpace:" + (tmpRowLeft + childWidthSpace));

            if (tmpRowLeft + childWidthSpace > widthSize - mPaddingRight) { //换行
                tmpRowLeft = mPaddingLeft;
                tmpRowTop += rowMaxHeight;
                rowMaxHeight = 0;
            }

            tmpRowLeft += childWidthSpace;
            rowMaxHeight = Math.max(rowMaxHeight, childHeightSpace);
        }

        int tmpHeightSize = tmpRowTop + rowMaxHeight + mPaddingBottom;

        int mWidth = widthSize;
        int mHeight = 0;

        switch (heightMode) {
            case MeasureSpec.EXACTLY: {
                mHeight = heightSize;
                break;
            }
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                mHeight = tmpHeightSize;
                break;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int widthSize = getMeasuredWidth();
        int childCount = getChildCount();

        int mPaddingLeft = getPaddingLeft();
        int mPaddingRight = getPaddingRight();
        int mPaddingTop = getPaddingTop();
        int mPaddingBottom = getPaddingBottom();

        int tmpRowLeft = mPaddingLeft;
        int tmpRowTop = mPaddingTop;
        int rowMaxHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            final MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            int childWidthSpace = childWidth + params.leftMargin + params.rightMargin;
            int childHeightSpace = childHeight + params.topMargin + params.bottomMargin;

            if (tmpRowLeft + childWidthSpace > widthSize - mPaddingRight) {
                tmpRowLeft = mPaddingLeft;
                tmpRowTop += rowMaxHeight;
                rowMaxHeight = 0;
            }

            child.layout(tmpRowLeft + params.leftMargin,
                    tmpRowTop + params.topMargin,
                    tmpRowLeft + params.leftMargin + childWidth,
                    tmpRowTop + params.topMargin + childHeight);

            tmpRowLeft += childWidthSpace;
            rowMaxHeight = Math.max(rowMaxHeight, childHeightSpace);
        }
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return super.generateLayoutParams(p);
    }
}
