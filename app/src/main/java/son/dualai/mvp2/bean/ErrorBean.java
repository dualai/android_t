package son.dualai.mvp2.bean;

/**
 * Created on 2019/3/8.
 */
public class ErrorBean {
    public final static int ErrorTypeHttp = 0; // 错误类型：http
    public final static int ErrorTypeEncrypt = 1; // 解密
    public final static int ErrorTypeApi = 2; /// api
    public final static int ErrorTypeOther = 3; /// 其他
    public int errorType = ErrorTypeOther;
    public int errorCode;
    public String errorMsg;
}
