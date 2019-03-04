package son.dualai.okhttp;

//封装请求接口
//请求的时候会有很多种情况，所以用接口
public interface IHttpRequest {
    void setUrl(String url);
    void setData(byte[] data);
    void setListener(CallbackListener callbackListener);
    void execute();
}
