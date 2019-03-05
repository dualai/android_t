package son.dualai.handletest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueue {

    /**
     * 存储数组结构
     */
    private Message[] items;


    /**
     * 入队和出队的索引位置
     */
    private int putIndex;
    private int takeIndex;

    //计数器
    private int count = 0;

    //等待
    private Lock lock;

    //条件变量
    private Condition notFull;
    private Condition notEmpty;

    public MessageQueue() {
        items = new Message[50];
        lock = new ReentrantLock();//可重入锁
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();

    }

    /**
     * 将消息加入到队列，生产者
     *
     * @param msg
     */
    public void enqueueMessage(Message msg) {

        //消息队列满了，等待消费
        try {
            lock.lock();
            while (count == 50) {
                notFull.await();
            }
            items[putIndex] = msg;
            putIndex = (++putIndex == items.length) ? 0 : putIndex;
            count++;

            //已经生产了，可以消费了
            notEmpty.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    /**
     * 取出队列中的消息，消费者
     */
    public Message next() {
        Message msg = null;

        try {
            lock.lock();
            //消息队列空了,等待生产
            while (count == 0) {
                notEmpty.await();
            }

            msg = items[takeIndex];
            items[takeIndex] = null;
            takeIndex = (++takeIndex == items.length) ? 0 : takeIndex;
            count--;

            //已经消费了，可以继续生产了
            notFull.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


        return msg;
    }

}
