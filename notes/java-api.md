# 反射 reflect

#### 1. 什么是反射？

> Java 反射机制是指在**运行状态**中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性。这种**动态获取信息**以及**动态调用对象方法**的功能称为 Java 语言的反射机制。
>
> - **核心原理**：类加载后在内存堆区中产生的唯一 Class 对象。

#### 2. 主要功能

- **运行时判断**：在运行时判断任意一个对象所属的类。
- **运行时构造**：在运行时构造任意一个类的对象（Constructor）。
- **运行时获取**：在运行时获取任意一个类所具有的成员变量和方法（Field, Method）。
- **运行时调用**：在运行时调用任意一个对象的方法（invoke）。
- **破坏封装**：通过 setAccessible(true) 访问私有成员（这是双刃剑）。

#### 3. 应用场景

- **框架设计的灵魂**：Spring（依赖注入 DI/控制反转 IoC）、MyBatis、JUnit 等框架底层都大量使用了反射。它们通过读取配置（XML/注解），利用反射动态生成对象和调用方法，实现**解耦**。
- **动态代理**：Java 动态代理（Proxy）是基于反射实现的。
- **开发工具**：IDEA 的自动提示功能（输入对象点一下，跳出方法列表）就是利用反射分析类结构实现的。

#### 4. 优缺点

- **优点**：
  - **灵活性极高**：可以在程序运行期间动态加载类，提高代码的通用性。
  - **解耦**：避免将代码写死（Hard Code），便于通过配置文件维护。
- **缺点**：
  - **性能开销**：反射涉及动态解析，JVM 无法对这些代码进行优化（如内联），速度比直接调用慢（虽然现代 JDK 优化了很多，但依然有差距）。
  - **安全隐患**：可以访问私有成员，破坏了面向对象的封装性，可能导致内部逻辑被意外修改。

------

#### 面试题

谈谈你对反射的理解？

反射本质上是 Java 的一种**运行时**机制。它允许程序在运行期间，通过 Class 对象来动态获取类的元数据（比如方法、字段、构造器）。

它的作用主要体现在两方面：

1. **动态性**：比如我们可以不通过 new 关键字，而是读取配置文件里的类名字符串来创建对象，这是 Spring 等框架实现 IoC 的基础，大大提高了系统的解耦能力。
2. **能力增强**：它能让我们访问类的私有成员，虽然这破坏了封装性，但在测试框架或底层工具中非常有用。

不过反射也有代价，它会带来一定的**性能损耗**，同时也可能带来安全风险，所以业务代码中一般少用，主要用于框架开发。

```java
        // 获取字节码文件对象的三种方法
        // （1）Class.forName("全类名")
        Class clazz1 = Class.forName("reflection.Student");
        // （2）类名.class
        Class clazz2 = Student.class;
        // （3）对象.getClass()
        Student stu = new Student();
        Class clazz3 = stu.getClass();

        System.out.println(clazz1); // java.lang.Class 重写了toString()方法，打印出 class + 全类名
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);

        // 获取构造方法，并创建对象
        // 1、获取字节码文件对象
        Class clazz = Class.forName("reflection.Student");
        // 2、获取空参构造函数
        Constructor con = clazz.getConstructor();
        // 3、创建对象
        Student stu = (Student) con.newInstance();
        System.out.println(stu);

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
```

