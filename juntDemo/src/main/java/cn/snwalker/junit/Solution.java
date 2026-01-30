package cn.snwalker.junit;

public class Solution {
    public int mySqrt(int x) {
        // 1. 健壮性检查：处理负数输入
        if (x < 0) {
            throw new IllegalArgumentException("输入参数不能为负数: " + x);
        }
        if (x == 0) return 0;
        if (x == 1) return 1;

        // 2. 确定搜索范围：算术平方根一定在 [1, x/2 + 1] 之间
        int right = x / 2 + 1;
        return binarySearch(1, right, x);
    }

    private int binarySearch(int left, int right, int target) {
        int l = left, r = right;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            // 使用 long 强制转换防止 mid * mid 溢出 int 范围
            if ((long)mid * mid <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l - 1;
    }
}
