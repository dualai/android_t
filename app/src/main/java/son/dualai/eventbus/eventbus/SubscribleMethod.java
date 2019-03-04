package son.dualai.eventbus.eventbus;

import java.lang.reflect.Method;

/**
 * 将方法进行封装，封装成一个对象放在EventBus进行管理
 */
public class SubscribleMethod {
    //回调方法
    private Method mMethod;

    //线程模式
    private ThreadMode mThreadMode;

    //回调方法中的参数 ?表示参数是不确定的java类型,所以用Class进行表示
    private Class<?> type;

    public Method getmMethod() {
        return mMethod;
    }

    public void setmMethod(Method mMethod) {
        this.mMethod = mMethod;
    }

    public ThreadMode getmThreadMode() {
        return mThreadMode;
    }

    public void setmThreadMode(ThreadMode mThreadMode) {
        this.mThreadMode = mThreadMode;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public SubscribleMethod(Method mMethod, ThreadMode mThreadMode, Class<?> type) {
        this.mMethod = mMethod;
        this.mThreadMode = mThreadMode;
        this.type = type;
    }
}
