package son.dualai.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.socks.library.KLog;

import son.dualai.Util;
import son.dualai.R;

public class OkHttpActivity extends Activity {
    private Button testBtn;
    //    private String url = "http://v.juhe.cn/historyWeather/citys?province_id=2&key=bb52107206585ab074f5e59a8c73875b";
//    private String url = "xxxx";
//    private String url = "http://apis.juhe.cn/lottery/types/?key=390105a8f6109110bb176a4efd07c923";
    private String url = "http://v.juhe.cn/toutiao/index?type=top&key=267c5c42c6e6a800d61e4fce0df43205";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        testBtn = findViewById(R.id.testBtn);

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }


    private void test() {
//        NeOkHttp.sendJsonRequest(null, url, ResponseBean.class, new IJsonDataListener<ResponseBean>() {
//            @Override
//            public void onSuccess(ResponseBean result) {
//                Log.d(Util.TAG, result.toString());
//            }
//
//            @Override
//            public void onFailure() {
//
//            }
//        });

        NeOkHttp.sendJsonRequest(null, url, Object.class, new IJsonDataListener<Object>() {
            @Override
            public void onSuccess(Object result) {
//                Log.d(Util.TAG, result.toString());
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
