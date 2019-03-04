package son.dualai.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import son.dualai.demo0301.R;
import son.dualai.eventbus.eventbus.EventBean;
import son.dualai.eventbus.eventbus.EventBus;

public class SecendActivity extends Activity implements View.OnClickListener {
    private Button testBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secend);

        testBtn = (Button)findViewById(R.id.testBtn);
        testBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new EventBean("Netease","android"));
    }
}
