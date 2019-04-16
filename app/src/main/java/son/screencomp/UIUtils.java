package son.screencomp;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class UIUtils {

    private static UIUtils instance;

    private UIUtils(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displayMetricsWidth == 0.0F || displayMetricsHeight == 0.0F) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int systemBarHeight = getSystemBarHeight(context);

            //处理真是宽高的情况
            if(displayMetrics.widthPixels > displayMetrics.heightPixels) {// 横屏
                this.displayMetricsWidth = (float)displayMetrics.heightPixels;
                this.displayMetricsHeight = (float)(displayMetrics.widthPixels - systemBarHeight);
            }else { //竖屏
                this.displayMetricsWidth = (float)displayMetrics.widthPixels;
                this.displayMetricsHeight = (float)(displayMetrics.heightPixels - systemBarHeight);
            }
        }
    }

    private int getSystemBarHeight(Context context) {
        return getValue(context,DIMEN_CLASS,"system_bar_height",48);
    }

    //开始获取缩放以后的结果
    public float getWidth(float width){
        return width * this.displayMetricsWidth / 1080.0F;
    }

    public float getHeight(float height){
        return height * this.displayMetricsHeight / 1872.0F;
    }

    public int getWidth(int width){
        return (int) (width * this.displayMetricsWidth / 1080.0F);
    }

    public int getHeight(int height){
        return (int) (height * this.displayMetricsHeight / 1872.0F);
    }

    /**
     *
     * @param context
     * @param attrGroupClass 按住源码中找到的存放维度的类
     * @param attrName 状态款信息
     * @param defaultValue 默认
     * @return
     */
    private int getValue(Context context, String attrGroupClass, String attrName, int defaultValue) {
        try {
            Class e = Class.forName(attrGroupClass);
            Object obj = e.newInstance();
            Field field = e.getField(attrName);
            int x = Integer.parseInt(field.get(obj).toString()); //获取到的是一个ID
            return context.getResources().getDimensionPixelOffset(x);
        } catch (Exception e1) {
            e1.printStackTrace();
            return defaultValue;
        }
    }

    public static UIUtils getInstance() {
        return instance;
    }

    public static UIUtils getInstance(Context context) {
        if(instance == null){
            instance = new UIUtils(context);
        }
        return instance;
    }

    /**
     * 按照美工的基础值生成真实设备上需要的宽高值
     */
    //基准宽高
    public static final int Standard_width = 1080;
    public static final int Standard_height = 1872; //1920 - 48

    private static final String DIMEN_CLASS = "com.android.internal.R$dimen";

    //实际设备的分辨率
    public float displayMetricsWidth;
    public float displayMetricsHeight;


}
