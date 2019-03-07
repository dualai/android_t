package son.dualai.adt;

import android.support.annotation.IntDef;

import static son.dualai.adt.MainConstantTool.BEIJING;
import static son.dualai.adt.MainConstantTool.HANGZHOU;
import static son.dualai.adt.MainConstantTool.SHANGHAI;
import static son.dualai.adt.MainConstantTool.SHENZHEN;

/**
 * Created by anson on 2018/11/18.
 */
@IntDef({SHANGHAI,HANGZHOU,BEIJING,SHENZHEN})
public @interface MainConstantTool {
    int SHANGHAI = 0;
    int HANGZHOU = 1;
    int BEIJING = 2;
    int SHENZHEN = 3;
}
