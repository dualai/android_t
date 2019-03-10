package son.dualai;

import android.view.View;

public class Util {
    public final static String TAG = "test";

    public static String getMode(int mode) {
        switch (mode) {
            case View.MeasureSpec.EXACTLY:
                return "EXACTLY";
            case View.MeasureSpec.AT_MOST:
                return "AT_MOST";
            default:
                return "UN";
        }
    }
}
