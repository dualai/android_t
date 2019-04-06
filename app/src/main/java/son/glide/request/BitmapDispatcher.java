package son.glide.request;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import java.util.concurrent.BlockingQueue;

/**
 * Created on 2019/3/16.
 */
public class BitmapDispatcher extends Thread {
    private final BlockingQueue<BitmapRequest> requestQueue;

    private Handler handler = new Handler(Looper.myLooper());

    public BitmapDispatcher(BlockingQueue<BitmapRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                //没有任务一直阻塞这里
                BitmapRequest request = requestQueue.take();
                //先展示loading画面
                showLoadingImage(request);

                //加载图片
                Bitmap bitmap = findBitmap(request);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap findBitmap(BitmapRequest request) {
        //先从内存


        //再从磁盘


        //再从网络

        return null;
    }


    private void showLoadingImage(BitmapRequest request) {
        if (request.getLoadingResId() > 0) {
            final ImageView imageView = request.getImageView();
            final int resId = request.getLoadingResId();
            if (imageView != null) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(resId);
                    }
                });
            }
        }
    }
}
