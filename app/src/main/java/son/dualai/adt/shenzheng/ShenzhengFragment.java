package son.dualai.adt.shenzheng;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import butterknife.BindView;
import son.dualai.R;
import son.dualai.adt.ViewInject;
import son.dualai.adt.base.BaseFragment;

/**
 * Created on 2019/3/7.
 */
@ViewInject(mainlayoutid = R.layout.fragment_others)
public class ShenzhengFragment extends BaseFragment {
    @BindView(R.id.tv_position)
    TextView tvPosition;

    @Override
    public void afterBindView() {
        tvPosition.setText("深圳");
    }
}
