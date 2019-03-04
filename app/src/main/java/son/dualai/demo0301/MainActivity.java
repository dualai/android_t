package son.dualai.demo0301;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import son.dualai.Util;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    private FrameLayout root;
    private TextView button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = (FrameLayout) findViewById(R.id.root);
        button1 = (TextView) findViewById(R.id.button1);

        root.setOnTouchListener(this);
        button1.setOnTouchListener(this);

        root.setOnClickListener(this);
        button1.setOnClickListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(Util.TAG,v+ " onTouch code:"+event.getAction());
        return true;
    }

    @Override
    public void onClick(View v) {
        Log.d(Util.TAG,v+ " onClick");
    }
}
