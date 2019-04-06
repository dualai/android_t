package son.glide.request.listener;

import android.graphics.Bitmap;

/**
 * Created on 2019/3/16.
 */
public interface RequestListener {
    public boolean onException();

    public boolean onResourceReady(Bitmap resource);
}
