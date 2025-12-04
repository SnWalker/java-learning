package arrays;

import java.util.Random;

public class ArrayPractice {
    public static void main(String[] args) {
//        // 静态初始化
//        int[] array1 = new int[]{1, 3, 5};
//        int[] array2 = {1, 2, 5};
//        // 动态初始化
//        int[] array3 = new int[3]; // 只指定大小，整数数组默认初始化为0
//
//        System.out.println(array1);
//        System.out.println(array2);
//        System.out.println(array3);

        // 生成10个1~100之间的随机数存入数组。
        int[] randmNum = new int[10];
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            randmNum[i] = rand.nextInt(100) + 1;
            System.out.print(randmNum[i]);
            System.out.print(" ");
        }
    }
}
