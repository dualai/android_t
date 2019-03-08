package son.dualai.mvp2.core;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.socks.library.KLog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import son.dualai.Util;
import son.dualai.mvp2.bean.ResultBean;
import son.dualai.mvp2.util.OkHttpUtil;

/**
 * Created on 2019/3/8.
 * <p>
 * test123321
 */
public class ApiServer {
    public void apiLogin(String username, String password, final INetwork.HttpCallback httpCallback) {
        RequestBody body = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("username", username);
//        jsonObject.put("password", password);
//        KLog.json(jsonObject.toJSONString());
//
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, jsonObject.toJSONString());

        OkHttpUtil.post("http://www.wanandroid.com/user/login", body, new INetwork.OkHttpCallback() {
            @Override
            public void onOkHttpCallback(boolean success, Call call, Response response, IOException ex) {
                try {
                    if (!success) {
                        KLog.e("okhttp error...");
                    } else {
                        ResponseBody responseBody = response.body();
                        httpCallback.onHttpCallback(true, responseBody != null ? responseBody.string() : null, response.code());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
