package son.dualai.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import son.dualai.R;
import son.dualai.Util;

import son.dualai.eventbus.eventbus.EventBean;
import son.dualai.eventbus.eventbus.EventBus;
import son.dualai.eventbus.eventbus.Subscrible;
import son.dualai.eventbus.eventbus.ThreadMode;

public class EventBusActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

        Intent intent = new Intent(this, SecendActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().ungister(this);
        super.onStop();
    }

    @Subscrible(threadMode = ThreadMode.BACKGROUND)
    public void getMessage(EventBean bean) {
        Log.d(Util.TAG, "haha " + bean.toString() + " thread:"+Thread.currentThread().toString());
        try {
        }catch (Exception ex){

        }
    }

    //方法本身 参数 线程模式
//    @Subscrible
//    public void test(EventBean bean){
//        Log.d(Util.TAG,"test "+bean.toString());
//    }
}
