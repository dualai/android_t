package son.screencomp;

import android.view.View;
import android.widget.LinearLayout;

public class ViewCalculateUtil {
    //获取调用层传入的值进行设置
    public static void setViewLinearLayoutParam(View view,int width,int height,int topMargin,int bottomMargin,int leftMagin,int rightMargin){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if(width != LinearLayout.LayoutParams.MATCH_PARENT  && width != LinearLayout.LayoutParams.WRAP_CONTENT){
            layoutParams.width = UIUtils.getInstance(JettApplication.getInstance()).getWidth(width);
        }else{
            layoutParams.width = width;
        }

        if(height != LinearLayout.LayoutParams.MATCH_PARENT  && height != LinearLayout.LayoutParams.WRAP_CONTENT){
            layoutParams.height = UIUtils.getInstance(JettApplication.getInstance()).getHeight(height);
        }else{
            layoutParams.height = height;
        }



    }
}
