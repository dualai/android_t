package son.dualai.okhttp;

public interface IJsonDataListener<T> {
    void onSuccess(T result);
    void onFailure();
}
