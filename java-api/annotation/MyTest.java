package annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// 元注解：注解的注解
@Target(ElementType.METHOD) // 指定自定义注解的作用范围
@Retention(RetentionPolicy.RUNTIME) // 指定自定义注解的生命周期
public @interface MyTest {
    // 定义属性，传递信息
    String value() default "无描述信息";
    int priority() default 1;
}
