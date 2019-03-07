package son.dualai.handletest;

public class Handler {
    private MessageQueue mQueue;

    public Handler() {
        Looper mLooper = Looper.myLooper();//有消息循环，没消息堵塞
        mQueue = mLooper.mQueue;
    }

    /**
     * 发送消息，压入队列，可能在多线程环境下
     * @param msg
     */
    public void sendMessage(Message msg) {
        msg.target = this;
        mQueue.enqueueMessage(msg);
    }

    /**
     * 分发消息
     * @param msg
     */
    public void dispatchMessage(Message msg){
        handleMessage(msg);
    }

    /**
     * 处理消息
     * @param msg
     */
    public void handleMessage(Message msg){

    }




}
