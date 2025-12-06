package collections;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("One");
        System.out.println(list);
        list.add("Two");
        System.out.println(list);
        System.out.println(list.get(0));
        list.remove(1);
        System.out.println(list);
        list.add("Three");
        System.out.println(list);
        list.remove("Three");
        System.out.println(list);

        //ArrayList<int> list1 = new ArrayList<>(); // 必须使用对应的包装类，涉及到Java中泛型的底层原理（类型擦除等）
        ArrayList<Integer> list1 = new ArrayList<>(); // 懒加载机制：初始时容量为0
        /*ArrayList集合扩容部分源码
        * int newCapacity = ArraysSupport.newLength(oldCapacity,minCapacity - oldCapacity, // minimum growth
        * oldCapacity >> 1); // preferred growth
        * 其中 oldCapacity >> 1 利用位运算将旧容量除2，会向下取整。
        */
        list1.add(1); // 1.5倍 倍增机制，容量初始为10，后序容量不够时扩增为1.5倍。 例如，0 -> 10 -> 15 -> 22。 15 * 1.5 = 22.5，为什么是22呢？ 原因：源码
    }
}
