package son.dualai.mvp2.core;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import son.dualai.mvp2.bean.ResultBean;

/**
 * Created on 2019/3/8.
 */
public class INetwork {

    public interface OkHttpCallback {
        void onOkHttpCallback(boolean success, Call call, Response response, IOException ex);
    }

    public interface HttpCallback {
        void onHttpCallback(boolean success, String result, int resultCode);
    }

    public interface ApiCallback<T> {
        void onApiCallback(boolean success, ResultBean<T> resultBean);
    }

}
