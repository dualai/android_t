package son.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import son.dualai.Util;

public class WebViewActivity extends Activity {


    private WebView mWebview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebview = new WebView(getApplicationContext());

        mWebview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
                Log.d(Util.TAG,"shouldOverrideUrlLoading...");
                view.loadUrl(url);
                return true;
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Log.d(Util.TAG, "shouldInterceptRequest...");
                return super.shouldInterceptRequest(view, request);
            }
        });


        mWebview.loadUrl("http://www.baidu.com");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((ViewGroup)(findViewById(android.R.id.content))).addView(mWebview);
            }
        },5000);


    }
}
