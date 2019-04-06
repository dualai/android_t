package son.glide.request;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

import son.dualai.Util;
import son.glide.request.listener.RequestListener;

/**
 * Created on 2019/3/16.
 */
public class BitmapRequest {

    //缓存 key --url---特殊字符不能作为key，需要把url用md5转一下作为key,比如需要缓存到硬盘内存，根据key值，有特殊字符存不进去

    private String uri;
    private SoftReference<ImageView> softReference;
    private String uriMd5;
    private int loadingResId; //等在等待的图片

    private Context context;
    private RequestListener requestListener;


    public BitmapRequest(Context activity) {
        this.context = activity;
    }

    /**
     * 以下建造者模式
     * @param loadingResId
     * @return
     */
    public BitmapRequest loading(int loadingResId){
        this.loadingResId = loadingResId;
        return this;
    }

    public BitmapRequest listener(RequestListener requestListener){
        this.requestListener = requestListener;
        return this;
    }

    public BitmapRequest load(String url){
        this.uri = url;
        this.uriMd5 = Util.md5(url);
        return this;
    }

    /**
     * 建造者最后一个不用return
     * @param imageView
     */
    public void into(ImageView imageView){
        this.softReference = new SoftReference<>(imageView);
        imageView.setTag(uriMd5);

    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public ImageView getImageView() {
        return softReference.get();
    }

    public String getUriMd5() {
        return uriMd5;
    }

    public void setUriMd5(String uriMd5) {
        this.uriMd5 = uriMd5;
    }

    public void setLoadingResId(int loadingResId) {
        this.loadingResId = loadingResId;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public RequestListener getRequestListener() {
        return requestListener;
    }

    public void setRequestListener(RequestListener requestListener) {
        this.requestListener = requestListener;
    }

    public int getLoadingResId() {
        return loadingResId;
    }
}
