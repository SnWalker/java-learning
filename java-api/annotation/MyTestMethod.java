package annotation;

public class MyTestMethod {
    @MyTest
    public void test1() {
        System.out.println("执行test1");
    }

    public void test2() {
        System.out.println("执行test2");
    }
    @MyTest(value = "test3()测试很重要", priority = 10)
    private void test3() {
        System.out.println("执行test3");
    }

}
