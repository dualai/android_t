package son.dualai.handletest;

import java.util.UUID;

public class HandlerTest {
    public static void main(String[] args) {

        // 主线程和子线程相对，加入这个方法执行在主线程

        //Looper必须创建在主线程（相对来说，是当前线程）
        Looper.prepare();

        //Handler也必须创建在主线程
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                System.out.println("handler = "+ Thread.currentThread().getName() + " ,msg = "+msg.toString());
            }
        };


        //假设把消息创建在子线程，子线程发送...
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        Message msg = new Message();
                        synchronized (UUID.class) {
                            msg.obj = UUID.randomUUID().toString();
                        }

                        System.out.println("send = "+ Thread.currentThread().getName() + " ,msg = "+msg.toString());
                        handler.sendMessage(msg);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        Looper.looper();
    }
}
