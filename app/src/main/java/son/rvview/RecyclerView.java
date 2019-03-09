package son.rvview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import son.dualai.R;
import son.dualai.Util;

/**
 * Created on 2019/3/9.
 */
public class RecyclerView extends ViewGroup {

    //y偏移量 内容偏移量 第一个item的上边界距离屏幕上边界
    private int scrollY;

    //当前显示的view
    private List<View> viewList;

    //当前滑动的y值
    private int currentY;

    //行数
    private int rowCount;

    //初始化 第一屏最慢 onLayout() 一次
    private boolean needRelayout = true;

    //宽度
    private int width;

    private int height;

    private int[] heights; //item 高度

    Recycler recycler;

    //view 的第一行 是占内容的几行
    private int firstRow;

    //最小滑动距离
    private int touchSlop;

    Adapter adapter;

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        if (adapter != null) {
            recycler = new Recycler(adapter.getViewTypeCount());
        }
        scrollY = 0;
        firstRow = 0;
        needRelayout = true;
        requestLayout(); //1 onMeasure 2 onLayout
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public RecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.viewList = new ArrayList<>();
        this.needRelayout = true;
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        // 点击 28 - 48 滑动,最小滑动距离
        this.touchSlop = configuration.getScaledTouchSlop();
    }

    /**
     * 只拦截滑动，不拦截点击
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                currentY = (int) ev.getRawY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int y2 = Math.abs(currentY - (int) ev.getRawY());
                if (y2 > touchSlop) {
                    intercept = true;
                }
                break;
            }
        }
        return intercept;
    }

    //消费
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                int y2 = (int) event.getRawY();
                //正负号,如果向上滑动+，向下滑 -
                int diff = currentY - y2; //和手指按下去的那个值相减

                //子控件移动
                scrollBy(0, diff); // scrollBy，在原来基础之上再滑动diff；  scrollTo 绝对值
                //scrollBy 滑动当前画布
            }
        }
        return super.onTouchEvent(event);
    }

    private int scrollBounds(int scrollY, int firstRow, int sizes[], int viewSize) {
        if (scrollY > 0) {
            scrollY = Math.min(scrollY, sumArray(sizes, firstRow, sizes.length - firstRow) - viewSize); //看不见了
        } else {
            scrollY = Math.max(scrollY, -sumArray(sizes, 0, firstRow));
        }
        return scrollY;
    }

    @Override
    public void scrollBy(int x, int y) {

        scrollY += y; //计算偏移量,累计滑动距离

        //修正
        scrollY = scrollBounds(scrollY,firstRow,heights,height);

        if (scrollY > 0) {
            //快速滑动，可能需要连续判断，一次性划出了两个view，
            // 因为scrollBy是系统隔时间调用,有可能划出去一个的时候，还没有调用，划出去第二个才调用,用if只能判断一次
            while (heights[firstRow] < scrollY) {
//            if (heights[firstRow] < scrollY) { //代表划出去了
                //第一个元素划出去，scrollY恢复0
                if (!viewList.isEmpty()) {
                    removeView(viewList.remove(0));
                }
                scrollY -= heights[firstRow]; //也就是scrollY = 0;// 快速滑动过程，如果直接赋值0，可能有问题
                firstRow++;
            }

            while (getFilledHeight() < height) {
                int addLast = firstRow + viewList.size();
                View view = obtainView(addLast, width, heights[addLast]);
                viewList.add(viewList.size(), view);
            }

        } else if (scrollY < 0) {
//            while (!viewList.isEmpty() && getFilledHeight() - heights[firstRow + viewList.size()]){
//                removeView(viewList.remove(viewList.size() - 1));
//            }

//            while (0 > scrollY) {
//                int firstAddRow = firstRow - 1;
//                View view = obtainView(firstAddRow, width, heights[firstAddRow]);
//                viewList.add(0, view);
//                firstRow--;
//                scrollY += heights[firstAddRow + 1];
//            }
        }

        //目前为止还不会动
        //重新摆放
        repositionViews();
        awakenScrollBars();
    }

    @Override
    public void removeView(View view) {
        super.removeView(view);
        int typeView = (int) view.getTag(R.id.tag_type_view);
        recycler.put(view, typeView);
    }

    private void repositionViews() {
        int left, top, right, bottom, i;
        top = -scrollY;
        i = firstRow;
        for (View view : viewList) {
            bottom = top + heights[i++];
            view.layout(0, top, width, bottom);
            top = bottom;
        }
    }


    private int getFilledHeight() {
        return sumArray(heights, firstRow, viewList.size()) - scrollY;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        // 高度 item
        int h = 0;
        if (adapter != null) {
            this.rowCount = adapter.getCount();
            heights = new int[rowCount];
            for (int i = 0; i < heights.length; i++) {
                heights[i] = adapter.getHeight(i);
            }
        }

        //recycler的高度
        //1 假设item数量少 2 item数量多 1000
        int tmpH = sumArray(heights, 0, heights.length);
        h = Math.min(heightSize, tmpH);
        setMeasuredDimension(widthSize, h);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private int sumArray(int[] heights, int firstIndex, int count) {
        int sum = 0;
        count += firstIndex;
        for (int i = firstIndex; i < count; i++) {
            sum += heights[i];
        }
        return sum;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (needRelayout || changed) {
            needRelayout = false;
            viewList.clear();
            removeAllViews();
            if (adapter != null) {
                width = r - l;
                height = b - t;
                int left, top = 0, right, bottom;
                //rowcount  总个数 一屏幕的item
                for (int i = 0; i < rowCount && top < height; i++) {
                    bottom = top + heights[i];
                    View view = makeAndStep(i, 0, top, width, bottom); // addView
                    viewList.add(view);
                    top = bottom; //循环摆放
                }
            }
        }
    }

    private View makeAndStep(int row, int left, int top, int right, int bottom) {
        // 生成View
        View viewobtain = obtainView(row, right - left, bottom - top);

        // 摆放View
        viewobtain.layout(left, top, right, bottom);
        return viewobtain;
    }

    private View obtainView(int row, int width, int height) {
        // 去回收池找
        int itemType = adapter.getItemViewType(row);
        View itemView = recycler.get(itemType);
        //itemView = null

        View view = null;
        if (itemView == null) {
            view = adapter.onCreateViewHolder(row, itemView, this);
            if (view == null) {
                throw new RuntimeException("onCreateViewHolder view can't be null ");
            }
        } else {
            view = adapter.onBinderViewHolder(row, itemView, this);
            //view 最后 等于itemView ，即是从回收池拿出来的
        }

        // view -- type
        view.setTag(R.id.tag_type_view, itemType);
        view.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        addView(view, 0);
        return view;
    }


    //适配器
    interface Adapter {
        //种类
        int getItemViewType(int row);

        //类型总数
        int getViewTypeCount();

        View onCreateViewHolder(int position, View convertView, ViewGroup parent);

        View onBinderViewHolder(int position, View convertView, ViewGroup parent);

        int getHeight(int index);

        int getCount();
    }

}
