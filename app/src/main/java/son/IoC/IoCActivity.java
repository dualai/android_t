package son.IoC;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import son.dualai.R;

/**
 * Created on 2019/3/8.
 */
public class IoCActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioc);

        //getMethods() 当前类和父类所有public方法

        //getDeclaredMethods 只获取当前类，获取所有修饰符修饰的方法


    }
}
