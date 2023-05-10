/**
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 *  
 *
 * 示例:
 *
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 *
 * 1是丑数。
 * n超过1690。
 *
 *
 * 思路：
 *     首先，第 n 个凑数是一定大于 前面 n - 1 个丑数中的任意一个
 *     其次，第 n
 */

public class Solution01 {

    public int nthUglyNumber(int n) {

        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;

        for (int i = 1; i < dp.length; i++) {
            if (a - 1 > 0 && dp[a] * 2 > dp[i] && dp[a -1] <= dp[i]) {

            }
        }

        return dp[n];
    }
}
