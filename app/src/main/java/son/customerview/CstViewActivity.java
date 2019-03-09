package son.customerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import son.dualai.R;

/**
 * Created on 2019/3/9.
 */
public class CstViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏，notitle等
//        getWindow().requestFeature()

        setContentView(R.layout.activity_cst);

    }
}
