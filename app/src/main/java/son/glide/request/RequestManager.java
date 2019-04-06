package son.glide.request;

import android.util.Log;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import son.dualai.Util;


/**
 * Created on 2019/3/16.
 */
public class RequestManager {
    private static volatile RequestManager instance; //禁止重排序

    private RequestManager() {

    }

    public static RequestManager getInstance() {
        if (instance == null) {
            synchronized (RequestManager.class) {
                if (instance == null) {
                    instance = new RequestManager(); //可以分解为三步:1、分配内存 2、初始化对象 3、指针指向，可能会重排序为1、3、2
                }
            }
        }
        return instance;
    }

    //阻塞式队列，阻塞子线程,没有界限
    private LinkedBlockingQueue<BitmapRequest> requestQueue = new LinkedBlockingQueue<>();

    public void addBitmapRequest(BitmapRequest request){
        if(!requestQueue.contains(request)){
            requestQueue.add(request);
        }else{

        }
    }



}
