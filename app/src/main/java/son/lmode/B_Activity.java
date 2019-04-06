package son.lmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import son.dualai.R;
import son.dualai.Util;

/**
 * Created on 2019/3/13.
 */
public class B_Activity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        View rootContent = findViewById(android.R.id.content);
        rootContent.setOnClickListener(this);
        Log.d(Util.TAG,getClass().getName()+" onCreate...");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RootActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        Log.d(Util.TAG,getClass().getName()+" onDestroy...");
        super.onDestroy();
    }
}
