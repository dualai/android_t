package son.dualai.okhttp;

import android.util.Log;
import android.util.TimeUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import son.dualai.Util;

public class ThreadPoolManager {

    //1、创建队列，用来保存异步请求任务，
    // 先进先出，
    //LinkedBlockingDeque 双向队列  LinkedBlockingQueue 单向队列
//    private LinkedBlockingDeque<Runnable> mQueue = new LinkedBlockingDeque<>();
    private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();

    //2、添加异步任务到队列中
    public void addTask(Runnable runnable) {
        if (runnable != null) {
            try {
                mQueue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 创建延迟队列,DelayQueue 无边界的
    private DelayQueue<HttpTask> mDelayQueue = new DelayQueue<>();

    public void addDelayTask(HttpTask task) {
        if (task != null) {
            task.setDelayTime(3000);
            mDelayQueue.put(task); //这里调用offer和调用put是一回事，因为DelayQueue是无界的
        }
    }

    public Runnable delayThread = new Runnable() {
        @Override
        public void run() {
            HttpTask task = null;
            while (true) {
                try {
                    task = mDelayQueue.take();
                    if (task.getRetryCount() < 3) {
                        mThreadPoolExecutor.execute(task);
                        task.setRetryCount(task.getRetryCount() + 1);
                        Log.d(Util.TAG, "重试机制 " + task.getRetryCount());
                    } else {
                        Log.d(Util.TAG, "执行次数超限,放弃");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    //3、创建线程池
    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {
        mThreadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //比如线程池突然间挂了，任务会被作为异常抛出来；拒绝就添加，重新排队
                addTask(r);
            }
        }
        );

        //直接执行 "创建队列与线程池的"交互"线程",可以直接交给线程池来执行
        mThreadPoolExecutor.execute(communicateThread);

        //
        mThreadPoolExecutor.execute(delayThread);
    }

    // 创建队列与线程池的"交互"线程
    public Runnable communicateThread = new Runnable() {
        @Override
        public void run() {
            //不断的获取
            Runnable mRunnable = null;
            while (true) {
                try {
                    mRunnable = mQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mThreadPoolExecutor.execute(mRunnable);
            }
        }
    };

    private static ThreadPoolManager instance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return instance;
    }

}
