package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyTestDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // 1、获取字节码文件对象
        Class clazz = Class.forName("annotation.MyTestMethod");
        // 2、获取所有方法
        Method[] methods = clazz.getDeclaredMethods();
        // 3、若方法带有@MyTest注解，就执行
        Object o = clazz.newInstance();
        for (Method method : methods) {
            method.setAccessible(true); // 临时修改访问权限
            if (method.isAnnotationPresent(MyTest.class)) {
                method.invoke(o);

                // 获取注解所传递的信息
                // 1、获取注解实例
                MyTest anno = method.getAnnotation(MyTest.class);
                // 2、获取注解的属性值
                String value = anno.value();
                int priority = anno.priority();
                System.out.println("方法: " + method.getName() + " | 描述: " + value + " | 优先级: " + priority);
            }
        }
    }
}
