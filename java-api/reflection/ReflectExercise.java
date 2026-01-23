package reflection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

public class ReflectExercise {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, ClassNotFoundException, InstantiationException {
/*
        // 练习1 泛型擦除：集合中的泛型只存在于.java源文件，.class文件不存在泛型及其限制
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(7);
        ///*list.add("Hola"); // 编译报错，编译器只能通过泛型指定的变量类型*//*

        // 通过反射机制，获取ArrayList类的成员方法add()，可绕过编译器对泛型类型的限制
        // 1、获取内存中ArrayList类的字节码文件对象
        Class clazz = list.getClass();
        // 2、获取成员方法
        Method methodAdd = clazz.getMethod("add",Object.class);
        // 3、运行成员方法add()，添加非泛型规定类型的数据
        methodAdd.invoke(list, "Hi");
        methodAdd.invoke(list, "Bye");
        methodAdd.invoke(list, true);
        System.out.println(list);
*/

        // 练习2 利用反射根据文件中的不同类名和方法名，创建不同的对象并调用方法。
        // 1、读取配置文件，获取类名、方法名
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("java-api/reflection/prop.properties");
        prop.load(fis);
        fis.close();
        System.out.println(prop);

        String classname = prop.get("classname") + "";
        String methodname = prop.get("method") + "";
        //System.out.println(classname);
        //System.out.println(methodname);
        // 2、获取字节码文件对象
        Class clazz = Class.forName(classname);
        // 3、创建对象
        Constructor con = clazz.getDeclaredConstructor();
        con.setAccessible(true);
        Object obj = con.newInstance();
        System.out.println(obj);
        // 4、获取方法
        Method method = clazz.getDeclaredMethod(methodname);
        // 5、运行方法
        method.setAccessible(true);
        method.invoke(obj);


    }
}
