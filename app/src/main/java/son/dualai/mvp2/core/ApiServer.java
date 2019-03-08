package son.dualai.mvp2.core;

import son.dualai.mvp2.bean.ResultBean;

/**
 * Created on 2019/3/8.
 */
public class ApiServer {

    public interface HttpCallback {
        void onHttpCallback(boolean success, String result, int resultCode);
    }

    public interface ApiCallback {
        void onApiCallback(boolean success, ResultBean resultBean);
    }

    public void apiLogin(String username, String password, HttpCallback httpCallback) {
        httpCallback.onHttpCallback(false, null, 404);
    }

    private ApiServer() {
    }

    private static class SingletonHandler {
        private static ApiServer singleton = new ApiServer();
    }

    public static ApiServer getInstance() {
        return SingletonHandler.singleton;
    }
}
