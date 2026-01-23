package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
/*
        // 获取字节码文件对象的三种方法
        // 1、Class.forName("全类名")
        Class clazz1 = Class.forName("reflection.Student");
        // 2、类名.class
        Class clazz2 = Student.class;
        // 3、对象.getClass()
        Student stu = new Student();
        Class clazz3 = stu.getClass();

        System.out.println(clazz1); // java.lang.Class 重写了toString()方法，打印出 class + 全类名
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);
        System.out.println("====================");
*/

/*        // 获取构造方法
        Class clazz = Class.forName("reflection.Student");
        // 获取public修饰的构造方法
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        // 获取包含private修饰的构造方法
        Constructor[] constructors2 = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors2) {
            System.out.println(constructor);
        }
        // 获取指定参数类型的构造方法
        Constructor constructor3 = clazz.getConstructor();
        System.out.println(constructor3);
        *//*Constructor constructor4 = clazz.getConstructor(int.class);*//*
        Constructor constructor4 = clazz.getDeclaredConstructor(int.class);
        System.out.println(constructor4);
        Constructor constructor5 = clazz.getDeclaredConstructor(int.class);
        System.out.println(constructor5 == constructor4); // 每次获取构造方法的对象，都会新new一个
        System.out.println("====================");*/

        // 获取构造方法，并创建对象
        /*// 1、获取字节码文件对象
        Class clazz = Class.forName("reflection.Student");
        // 2、获取空参构造函数
        Constructor con = clazz.getConstructor();
        // 3、创建对象
        Student stu = (Student) con.newInstance();
        System.out.println(stu);
        System.out.println("====================");*/

/*
        // 1、获取字节码文件对象
        Class clazz = Class.forName("reflection.Student");
        // 2、获取带参构造函数
        Constructor con = clazz.getDeclaredConstructor(String.class, int.class);
        // 3、临时修改带参构造函数的访问权限（暴力反射）
        con.setAccessible(true);
        // 4、创建对象
        Student student = (Student) con.newInstance("John", 19);
        System.out.println(student);
*/

      // 获取成员变量
/*
      // 1、获取整体（字节码文件对象）
      Class clazz = Class.forName("reflection.Student");
      // 2、获取public修饰的所有成员变量
        Field[] fileds1 = clazz.getFields();
        for (Field f : fileds1) {
            System.out.println(f.getName());
        }
        System.out.println("====================");
        // 获取所有成员变量
        Field[] fields2 = clazz.getDeclaredFields();
        for (Field f : fields2) {
            System.out.println(f.getName());
        }
        System.out.println("====================");
        // 获取指定的成员变量
        Field field3 = clazz.getDeclaredField("name");
        System.out.println(field3.getName());
        System.out.println("====================");
*/

        // 利用反射获取成员变量，并获取值和修改值
/*
        Student s1 = new Student();
        s1.setName("Bob");
        s1.setAge(22);
        Student s2 = new Student();
        s2.setName("Alice");
        s2.setAge(20);
        // 1、获取字节码文件对象
        Class clazz = Class.forName("reflection.Student");
        // 2、获取单个成员变量
        Field field = clazz.getDeclaredField("name");
        // 必须先临时修改访问权限，才可以获取或修改成员变量的值
        field.setAccessible(true);
        System.out.println(field.get(s1));
        field.set(s2, "Alex");
        System.out.println(field.get(s2));
*/

        // 获取成员方法并运行
        Student s = new Student();
        s.setName("小明");
        // 1、获取字节码文件对象
        Class clazz = Class.forName("reflection.Student");
        // 2、获取成员方法
        Method eatMethod = clazz.getDeclaredMethod("eat", String.class);
        // 临时修改访问权限
        eatMethod.setAccessible(true);
        // 运行获取的成员方法 参数1:对象 参数2:方法的参数
        String result = (String) eatMethod.invoke(s, "回锅肉炒面");
        System.out.println(result);

    }
}
