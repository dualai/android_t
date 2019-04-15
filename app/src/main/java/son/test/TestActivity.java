package son.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import son.dualai.R;
import son.dualai.Util;

public class TestActivity extends Activity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.btn_jump)
    Button btnJump;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        tvName.setText("activity");

        Log.d(Util.TAG,"onCreate....");
    }

    @OnClick(R.id.btn_jump)
    public void onViewClicked() {
        startActivity(new Intent(this,Test1Activity.class));
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(Util.TAG,"onNewIntent....");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Util.TAG,"onPause....");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Util.TAG,"onStop....");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Util.TAG,"onStart....");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Util.TAG,"onResume....");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Util.TAG,"onDestroy....");
    }
}
