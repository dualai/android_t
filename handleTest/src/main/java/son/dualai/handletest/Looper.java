package son.dualai.handletest;

public class Looper {

    public MessageQueue mQueue;
    static ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    private Looper(){
        //MessageQueue创建在和Looper的同一个线程
        mQueue = new MessageQueue();
    }

    /**
     * 初始化looper，并且将其保存到ThreadLocal中
     */
    public static void prepare(){
        if(sThreadLocal.get()!= null){
            throw new RuntimeException("一个线程仅能拥有一个Looper");
        }
        sThreadLocal.set(new Looper());
    }

    /**
     * 获取当前线程的looper对象
     * @return
     */
    public static Looper myLooper(){
        return sThreadLocal.get(); //保证返回当前线程的looper
    }

    public static void looper(){
        Looper me = myLooper();
        if(me == null){
            throw new RuntimeException("当前线程looper为空，请调用prepare...");
        }
        //不能直接拿mQueue，因为在静态方法中
        MessageQueue queue = me.mQueue;
        for(;;){
            Message msg = queue.next();
            if(msg != null){
                msg.target.dispatchMessage(msg);
            }
        }

    }
}
