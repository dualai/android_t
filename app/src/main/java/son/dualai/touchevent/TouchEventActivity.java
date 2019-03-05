package son.dualai.touchevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import son.dualai.R;
import son.dualai.Util;


public class TouchEventActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    private CustomerFrameLayout root;
    private CustomerButton button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchevent);
        root = (CustomerFrameLayout) findViewById(R.id.root);
        button1 = (CustomerButton) findViewById(R.id.button1);

//        root.setOnTouchListener(this);
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(Util.TAG,v+ " onTouch code:"+event.getAction());
                return false;
            }
        });
        button1.setOnTouchListener(this);

        root.setOnClickListener(this);
        button1.setOnClickListener(this);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(Util.TAG,v+ " onTouch code:"+event.getAction());
        return false;
    }

    @Override
    public void onClick(View v) {
        Log.d(Util.TAG,v+ " onClick");
    }
}
