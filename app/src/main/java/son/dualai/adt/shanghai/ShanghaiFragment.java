package son.dualai.adt.shanghai;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import son.dualai.R;
import son.dualai.Util;
import son.dualai.adt.ViewInject;
import son.dualai.adt.base.BaseFragment;

/**
 * Created on 2019/3/7.
 */
@ViewInject(mainlayoutid = R.layout.fragment_shanghai)
public class ShanghaiFragment extends BaseFragment {
    @BindView(R.id.tv_position)
    TextView tvPosition;

    @Override
    public void afterBindView() {
        Log.d(Util.TAG,"上海...");
    }
}
