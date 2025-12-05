package variables;

public class StringDemo {
    public static void main(String[] args) {
        String str1 = new String("Hello");
        String str2 = new String("Hello");
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));

        String s1 = "World";
        String s2 = "World";
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        String s3 = new String("Good boy! Good Girl!");
        s3.replace("Good", "Hi");
        System.out.println(s3);
        System.out.println(s3.replace("Good", "Hi")); // 新创建一个字符串
        System.out.println(s3.substring(3));
        System.out.println(s3.indexOf("Good"));

        StringBuilder sb1 = new StringBuilder("Hi, there");
        sb1.reverse();
        System.out.println(sb1);
        sb1.reverse();
        sb1.replace(0, 2, "Hello"); // API通常是左闭右开
        System.out.println(sb1);

    }
}
