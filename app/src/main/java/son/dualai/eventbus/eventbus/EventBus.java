package son.dualai.eventbus.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import son.dualai.Util;
import son.dualai.eventbus.EventBusActivity;

public class EventBus {

//    /**静态内部类
//     *
//     */
//    private EventBus() {

//    }
//
//    private static class SingletonHolder {
//        private static EventBus instance = new EventBus();
//    }
//
//    public static EventBus getDefault() {
//        return SingletonHolder.instance;
//    }

    /**
     * 双重检查
     */
    private static volatile EventBus instance; //A线程修改了变量后立即刷到主线程，令B线程的该变量无效，重新从主存获取，但是不能保证原子操作

    private Map<Object, List<SubscribleMethod>> cacheMap;

    private Handler mHandler;

    private ExecutorService mExecutorService;
    private EventBus() {
        cacheMap = new HashMap<>();
        mHandler = new Handler(Looper.getMainLooper());
        mExecutorService = Executors.newCachedThreadPool();
    }

    /**
     * 将相关对象上的方法，交给EventBus中来管理，进而在不通的页面，用EventBus来Run Obj.method, 目前是直接把相关对象的getMessage方法交给EventBus来管理
     * 根据注解（Subscrible注解）来辨别把哪些方法放进去管理。凡是加了Subscrible注解的方法都可以交到EventBus来管理
     *
     * @param obj
     */
    public void register(Object obj) {
        List<SubscribleMethod> list = cacheMap.get(obj);
        if (list == null) {
            list = findSubscribleMethods(obj);
            if (list.size() > 0) {
                cacheMap.put(obj, list);
            }
        }
    }

    private List<SubscribleMethod> findSubscribleMethods(Object obj) {
        List<SubscribleMethod> list = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        while (clazz != null) {

            //判断当前是否是系统类，如果是的话，就退出循环
            String name = clazz.getName();
            if (name.startsWith("java.") || name.startsWith("javax") || name.startsWith("android.")) {
                break;
            }

            //得到Obj里头的所有方法
            Method[] methods = clazz.getMethods();
            for (Method method : methods) { //Object的父级为空
                //通过注解找到我们需要注册到EventBus中的方法
                Subscrible subscrible = method.getAnnotation(Subscrible.class);
                if (subscrible == null) {
                    continue;
                }

                // 获取方法中的参数，并判断是否唯一
                Class<?>[] types = method.getParameterTypes();
                if (types.length != 1) {
                    Log.e(Util.TAG, "EventBus 只能接收一个参数...");
                }


                //获取线程模式
                ThreadMode threadMode = subscrible.threadMode();
                SubscribleMethod scbMethod = new SubscribleMethod(method, threadMode, types[0]);
                list.add(scbMethod);
            }
            clazz = clazz.getSuperclass();//寻找父类中是否有
        }

        return list;
    }

    public void post(final Object type) {
        Set<Object> set = cacheMap.keySet();
        Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()) {
            final Object obj = iterator.next();
            List<SubscribleMethod> list = cacheMap.get(obj);
            for (final SubscribleMethod subscribleMethod : list) {
                //a(subscribleMethod.getType())对象所对应的类信息，是否是b(type.getClass())对象所对应的类信息的父类或者父接口
                if (subscribleMethod.getType().isAssignableFrom(type.getClass())) {//对比两个类是否一致
                    switch (subscribleMethod.getmThreadMode()) {
                        //不管你post的时候是在主线程还是在子线程，我都在主线程接收
                        case MAIN:
                            //主线程到主线程
                            if(Looper.myLooper() == Looper.getMainLooper()){
                                invoke(subscribleMethod, obj, type);
                            }else{ //子线程到主线程
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribleMethod, obj, type);
                                    }
                                });
                            }
                            //子线程-主线程
                            break;
                        //反过来...
                        case BACKGROUND:
                            // 主-子
                            if(Looper.myLooper() == Looper.getMainLooper()){
                                mExecutorService.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribleMethod, obj, type);
                                    }
                                });
                            }else {  // 子-子
                                invoke(subscribleMethod, obj, type);
                            }
                            break;
                    }

                }
            }
        }
    }

    private void invoke(SubscribleMethod subscribleMethod, Object obj, Object type) {
        Method method = subscribleMethod.getmMethod();
        try {
            method.invoke(obj, type);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static EventBus getDefault() {
        if (instance == null) {
            //执行到这里被其他线程抢去了，所有需要二次检查
            synchronized (EventBus.class) {
                if (instance == null) {
                    instance = new EventBus();
                }
            }
        }
        return instance;
    }

    public void ungister(Object obj) {
        cacheMap.remove(obj);
    }
}
