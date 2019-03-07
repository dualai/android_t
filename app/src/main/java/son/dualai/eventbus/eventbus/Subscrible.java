package son.dualai.eventbus.eventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，JVM在加载的时候可以通过反射获得
public @interface Subscrible {
    ThreadMode threadMode() default ThreadMode.MAIN;
}
