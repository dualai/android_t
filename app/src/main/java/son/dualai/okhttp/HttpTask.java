package son.dualai.okhttp;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class HttpTask<T> implements Runnable, Delayed {
    private IHttpRequest mIHttpRequest;

    /**
     * 异步任务，把单次的请求和响应封装成一个对象
     */
    public HttpTask(T requestData, String url, IHttpRequest httpRequest, CallbackListener callbackListener) {
        this.mIHttpRequest = httpRequest;
        mIHttpRequest.setUrl(url);
        mIHttpRequest.setListener(callbackListener);

        String content = JSON.toJSONString(requestData);
        try {
            mIHttpRequest.setData(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            mIHttpRequest.execute();
        } catch (Exception e) {
            e.printStackTrace();
            //将失败的任务添加到重试队列中
            ThreadPoolManager.getInstance().addDelayTask(this);
        }
    }

    private long delayTime;// 比如间隔三秒重试一次
    private int retryCount;// 重试次数

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime + System.currentTimeMillis();
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
