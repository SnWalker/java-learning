package control;
import java.util.Scanner;

public class ControlPractice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(("请输入需要判断的数字："));
        int x = sc.nextInt();

        Solution solution = new Solution();
        if (solution.isPalindrome(x)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}

// 时间复杂度 O（log10(n)） n为需要判断的整数； 空间复杂度 O（1）
class Solution {
    public boolean isPalindrome(int x) {
        // 临界情况
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            // 负数、除0之外个位数为0的数 都不是回文数
            return false;
        }

        // 将x后半部分反转得到revertedNumber，若前半部分等于反转后的后半部分，则是回文数
        int revertedNumber = 0;
        while (x > revertedNumber) { // x <= revertedNumber时，反转了一半数字
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        if (x == revertedNumber || x == revertedNumber / 10) { // x的位数为偶数、奇数
            return true;
        }
        return false;
    }
}