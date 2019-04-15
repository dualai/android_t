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

public class Test1Activity extends Activity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.btn_jump)
    Button btnJump;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        tvName.setText("activity_1");

    }

    @OnClick(R.id.btn_jump)
    public void onViewClicked() {
        startActivity(new Intent(this,Test2Activity.class));
    }
}
