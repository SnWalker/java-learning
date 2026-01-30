# 反射 reflect

## 1. 什么是反射？

> Java 反射机制是指在**运行状态**中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性。这种**动态获取信息**以及**动态调用对象方法**的功能称为 Java 语言的反射机制。
>
> - **核心原理**：类加载后在内存堆区中产生的唯一 Class 对象。

## 2. 主要功能

- **运行时判断**：在运行时判断任意一个对象所属的类。
- **运行时构造**：在运行时构造任意一个类的对象（Constructor）。
- **运行时获取**：在运行时获取任意一个类所具有的成员变量和方法（Field, Method）。
- **运行时调用**：在运行时调用任意一个对象的方法（invoke）。
- **破坏封装**：通过 setAccessible(true) 访问私有成员（这是双刃剑）。

## 3. 应用场景

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

## 面试题

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



# 注解





# 类加载器

负责将.class文件（存储的物理文件）加载在到内存中。

类加载的过程: 加载、验证、准备、解析、初始化

## 1. 类加载的过程

+ 类加载时机

  简单理解：字节码文件什么时候会被加载到内存中？

  有以下的几种情况：

  + 创建类的实例（对象）
  + 调用类的类方法
  + 访问类或者接口的类变量，或者为该类变量赋值
  + 使用反射方式来强制创建某个类或接口对应的java.lang.Class对象
  + 初始化某个类的子类
  + 直接使用java.exe命令来运行某个主类

  总结而言：**用到了就加载，不用不加载**

+ 类加载过程

  1、加载

  + 通过包名 + 类名，获取这个类，准备用流进行传输
  + 在这个类加载到内存中
  + 加载完毕创建一个class对象

![02_类加载过程加载](assets/02_类加载过程加载.png)

2、链接

（1）验证

确保Class文件字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身安全(文件中的信息是否符合虚拟机规范有没有安全隐患)

![03_类加载过程验证](assets/03_类加载过程验证.png)

（2）准备

负责为类的类变量（被static修饰的变量）分配内存，并设置默认初始化值

(初始化静态变量)

![04_类加载过程准备](assets/04_类加载过程准备.png)

（3）解析

将类的二进制数据流中的符号引用替换为直接引用

(本类中如果用到了其他类，此时就需要找到对应的类)

![05_类加载过程解析](assets/05_类加载过程解析.png)

3、初始化

根据程序员通过程序制定的主观计划去初始化类变量和其他资源

(静态变量赋值以及初始化其他资源)

![06_类加载过程初始化](assets/06_类加载过程初始化.png)

## 2. 类加载的分类【理解】

+ 分类

  + Bootstrap class loader：虚拟机的内置类加载器，通常表示为null ，并且没有父null
  + Platform class loader：平台类加载器,负责加载JDK中一些特殊的模块
  + System class loader：系统类加载器,负责加载用户类路径上所指定的类库

+ 类加载器的继承关系

  + System的父加载器为Platform
  + Platform的父加载器为Bootstrap

+ 代码演示

  ```java
  public class ClassLoaderDemo1 {
      public static void main(String[] args) {
          //获取系统类加载器
          ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
  
          //获取系统类加载器的父加载器 --- 平台类加载器
          ClassLoader classLoader1 = systemClassLoader.getParent();
  
          //获取平台类加载器的父加载器 --- 启动类加载器
          ClassLoader classLoader2 = classLoader1.getParent();
  
          System.out.println("系统类加载器" + systemClassLoader);
          System.out.println("平台类加载器" + classLoader1);
          System.out.println("启动类加载器" + classLoader2);
  
      }
  }
  ```

## 3. 双亲委派模型【理解】

+ 介绍

  如果一个类加载器收到了类加载请求，它并不会自己先去加载，而是**把这个类加载请求委托给父类的加载器去执行**，如果父类加载器还存在其父类加载器，则进一步向上委托，依次递归，请求最终将到达顶层的启动类加载器，如果父类加载器可以完成类加载任务，就成功返回，倘若父类加载器无法完成此加载任务，子加载器才会尝试自己去加载，这就是双亲委派模式。

<img src="assets/07_双亲委派模型.png" alt="07_双亲委派模型" style="zoom:50%;" />

## 4. ClassLoader 中的两个方法【应用】

- 方法介绍

  | 方法名                                              | 说明               |
  | --------------------------------------------------- | ------------------ |
  | public static ClassLoader getSystemClassLoader()    | 获取系统类加载器   |
  | public InputStream getResourceAsStream(String name) | 加载某一个资源文件 |

- 示例代码

  ```java
  public class ClassLoaderDemo2 {
      public static void main(String[] args) throws IOException {
          //static ClassLoader getSystemClassLoader() 获取系统类加载器
          //InputStream getResourceAsStream(String name)  加载某一个资源文件
  
          //获取系统类加载器
          ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
  
          //利用加载器去加载一个指定的文件
          //参数：文件的路径（放在src的根目录下，默认去那里加载）
          //返回值：字节流。
          InputStream is = systemClassLoader.getResourceAsStream("prop.properties");
  
          Properties prop = new Properties();
          prop.load(is);
  
          System.out.println(prop);
  
          is.close();
      }
  }
  ```

# 单元测试

单元测试（Unit Testing）的核心原则可以概括为 **FIRST** 原则，这在企业级开发中是公认的标准：

1. **F - Fast (快速)** ⚡：测试必须运行极快，以便开发者在编写代码时能频繁运行。

2. **I - Independent (独立)** 🧩：测试用例之间不能有依赖。每个测试都应该能独立运行，且无论顺序如何，结果都一致。

3. **R - Repeatable (可重复)** 🔄：在任何环境下（你的电脑、同事的电脑、服务器）多次运行，结果必须保持相同。

4. **S - Self-Validating (自我验证)** ✅：测试应该有明确的“通过”或“失败”输出（断言），不需要人工检查日志来判断对错。

5. **T - Timely (及时)** 📅：测试应与功能代码同步编写（甚至在 TDD 中先于代码编写），确保每一行逻辑都被覆盖。

   

1. **实际开发中单元测试的使用方式**

需求：测试File中的delete方法，写的是否正确

开发中的测试原则：不污染原数据。

代码示例：

```java
public class JunitDemo3 {
    //在实际开发中，真正完整的单元测试该怎么写？
    //前提：
    //以后在工作的时候，测试代码不能污染原数据。（修改，篡改）
    //1.利用Before去对数据做一个初始化的动作
    //2.利用Test真正的去测试方法
    //3.利用After去还原数据
    
    //需求：测试File类中的delete方法是否书写正确？？？
    @Before
    public void beforemethod() throws IOException {
        //先备份
        File src = new File("C:\\Users\\moon\\Desktop\\a.txt");
        File dest = new File("C:\\Users\\moon\\Desktop\\copy.txt");

        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        int b;
        while((b = fis.read()) != -1){
            fos.write(b);
        }
        fos.close();
        fis.close();
    }

    //作为一个标准的测试人员，运行完单元测试之后，不能污染原数据
    //需要达到下面两个要求：
    //1.得到结果
    //2.a.txt还在而且其他的备份文件消失
    @Test
    public void method(){
        File file = new File("C:\\Users\\moon\\Desktop\\a.txt");
        boolean delete = file.delete();

        //检查a.txt是否存在
        boolean exists = file.exists();

        //只有同时满足了下面所有的断言，才表示delete方法编写正确
        Assert.assertEquals("delete方法出错了",delete,true);
        Assert.assertEquals("delete方法出错了",exists,false);
    }


    @After
    public void aftermethod() throws IOException {
        //还要对a.txt做一个还原
        File src = new File("C:\\Users\\moon\\Desktop\\copy.txt");
        File dest = new File("C:\\Users\\moon\\Desktop\\a.txt");

        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        int b;
        while((b = fis.read()) != -1){
            fos.write(b);
        }
        fos.close();
        fis.close();

        //备份数据要删除
        src.delete();

    }
}
```

总结：

1.Before  准备数据

2.Test  测试方法

3.After 还原



**扩展点**：

在单元测试中，相对路径是相对当前模块而言的。

```java
File file = new File("aweihaoshuai.txt");
file.createNewFile();
//此时是把aweihaoshuai.txt这个文件新建到模块中了。
```



