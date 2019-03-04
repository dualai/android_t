package son.dualai.okhttp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class JSonHttpRequest implements IHttpRequest {

    private String url;
    private byte[] data;
    private CallbackListener mCallbackListener;
    private HttpURLConnection urlConnection;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public void setListener(CallbackListener callbackListener) {
        this.mCallbackListener = callbackListener;
    }

    @Override
    public void execute() {
        URL url = null;
        try {
            url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(6000);
            urlConnection.setUseCaches(false);
            urlConnection.setInstanceFollowRedirects(true); // 设置这个连接是否可以重定向
            urlConnection.setReadTimeout(3000); //相应的超时时间
            urlConnection.setDoInput(true); //设置这个链接是否可以写入数据
            urlConnection.setDoOutput(true); //设置这个链接是否可以输出数据
//            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            urlConnection.connect(); //连接，上述工作必须在connect之前完成

            //使用字节流发送数据
            OutputStream out = urlConnection.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out); //缓冲字节流包装字节流
            bos.write(data);
            bos.flush();
            bos.close();
            out.close();

            //字符流写入数据
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();
                mCallbackListener.onSuccess(in);
            } else {
                throw new RuntimeException("请求失败...");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("请求失败...");
        } finally {
            urlConnection.disconnect(); //关闭
        }

    }
}
