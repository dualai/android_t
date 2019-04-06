package son.glide.request.core;

import android.app.Activity;

import son.glide.request.BitmapRequest;

/**
 * Created on 2019/3/16.
 */
public class Glide {
    public static BitmapRequest with(Activity activity){
        return new BitmapRequest(activity);
    }
}
