package son.dualai.adt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //运行时注解，性能没有CLASS好
@Target(ElementType.TYPE) //类、接口注解
public @interface ViewInject {
    int mainlayoutid() default -1;
}
