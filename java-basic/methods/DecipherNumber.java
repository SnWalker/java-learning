package methods;

import java.util.Scanner;
/*某系统的数字密码（大于0）。比如1983，采用加密方式进行传输，
        规则如下：
            每位数加上5
            再对10求余，
            最后将所有数字反转，
            得到一串新数。
            按照以上规则进行解密：
            比如1983加密之后变成8346，解密之后变成1983
*/
public class DecipherNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("输入需要解密的数字: ");
        int number = sc.nextInt();

        // Command + Option + M 自动抽取为方法
        dicipherNumber(number);
//        // 边界处理
//        if (number < 0) {
//            System.out.println("输入数字非法！");
//        }
//        // 计算数字的位数
//        int count = 0;
//        int tmp = number;
//        while (tmp != 0) {
//            tmp /= 10;
//            count++;
//        }
//        // 存储解密后的数字
//        int[] arr = new int[count];
//        // 反转位置
//        for (int i = 0; i < count; i++) {
//            arr[i] = number % 10;
//            number /= 10;
//        }
//        // 逆向“求余数”操作，1～4 先加10再减5（直接加5），5～9、0直接减5
//        for (int i = 0; i < count; i++) {
//            if (arr[i] >= 1 && arr[i] <= 4) {
//                arr[i] = arr[i] + 5;
//            } else {
//                arr[i] = arr[i] - 5;
//            }
//        }
//        System.out.print("解密结果：");
//        for (int x : arr) System.out.print(x);
    }

    private static void dicipherNumber(int number) {
        // 边界处理
        if (number < 0) {
            System.out.println("输入数字非法！");
            return;
        }
        // 计算数字的位数
        int count = 0;
        int tmp = number;
        while (tmp != 0) {
            tmp /= 10;
            count++;
        }
        // 存储解密后的数字
        int[] arr = new int[count];
        // 反转位置
        for (int i = 0; i < count; i++) {
            arr[i] = number % 10;
            number /= 10;
        }
        // 逆向“求余数”操作，1～4 先加10再减5（直接加5），5～9、0直接减5
        for (int i = 0; i < count; i++) {
            if (arr[i] >= 1 && arr[i] <= 4) {
                arr[i] = arr[i] + 5;
            } else {
                arr[i] = arr[i] - 5;
            }
        }
        System.out.print("解密结果：");
        for (int x : arr) System.out.print(x);
    }
}
