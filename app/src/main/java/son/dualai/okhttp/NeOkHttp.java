package son.dualai.okhttp;

public class NeOkHttp {
    public static <T, M> void sendJsonRequest(T requestData, String url,
                                              Class<M> response, IJsonDataListener listener) {
        IHttpRequest httpRequest = new JSonHttpRequest();
        CallbackListener callbackListener = new JsonCallbackListener<>(response, listener);
        HttpTask httpTask = new HttpTask(requestData, url, httpRequest, callbackListener);
        //添加到队列中
        ThreadPoolManager.getInstance().addTask(httpTask);
    }
}
