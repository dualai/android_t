package son.dualai.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonCallbackListener<T> implements CallbackListener {

    private Class<T> responseClass;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private IJsonDataListener mJsonDataListener;

    public JsonCallbackListener(Class<T> responseClass, IJsonDataListener listener) {
        this.responseClass = responseClass;
        this.mJsonDataListener = listener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        String response = getContent(inputStream);
        final T resultObj = JSON.parseObject(response, responseClass); //这样子就把inputstream流直接转换成对象
        //转化到主线程
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mJsonDataListener.onSuccess(resultObj);
            }
        });
    }

    /**
     * input 流转换成string
     *
     * @param inputStream
     * @return
     */
    private String getContent(InputStream inputStream) {
        String content = null;
        try {
            //字符缓冲输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            content = sb.toString();
            KLog.d(content.getBytes().length);
        } catch (Exception e) {
            e.printStackTrace();
            content = null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return content;
    }

    @Override
    public void onFailure() {

    }
}
