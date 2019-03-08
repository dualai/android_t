package son.dualai.mvp2.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.socks.library.KLog;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import son.dualai.Util;
import son.dualai.mvp2.core.ApiServer;
import son.dualai.mvp2.core.INetwork;

/**
 * Created on 2019/3/8.
 */
public class OkHttpUtil {
//    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private static final Handler mHandler = new Handler(Looper.getMainLooper());
    public static void post(String url, RequestBody requestBody, final INetwork.OkHttpCallback onOkHttpCallback) {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onOkHttpCallback.onOkHttpCallback(false, call, null, e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onOkHttpCallback.onOkHttpCallback(true, call, response, null);
                    }
                });
            }
        });
    }

}