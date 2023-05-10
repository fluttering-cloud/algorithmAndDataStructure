/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 思路：
 *     由题可得：
 *          当 n = 1 时，有 1 种方法，记为dp[1]
 *          当 n = 2 时，可以有如下方法
 *              i.在dp[1]，的基础上跳一步
 *              ii.直接跳两步,
 *              所以的共有两 2 种方法，记为dp[2]
 *          当 n = 3 的时候，可以有如下方法
 *              i. 在 n = 2 的基础上，跳一步，此时前 2 步共有共有 dp[2]种跳法
 *              ii. 在 n = 1 的基础上跳 2 步，此时共有 dp[1] 种跳法，
 *              令 n = 3 时的跳法记为 dp[3],则 dp[3] = i + ii = dp[1] + dp[2]
 *          可得 dp[n] = dp[n - 1] + dp[n - 2]
 *
 *   在该题中，有如下特征
 *          当前的状态需要用之前的状态求解，及有重叠子问题，所以是一个记忆化递归的题目
 *
 */

public class Solution01 {


    public static void main(String[] args) {
        Solution01 solution01 = new Solution01();
        System.out.println(solution01.numWays(1));
    }


    public int numWays(int n) {
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = ( dp[i - 1] + dp[i - 2] ) % 1000000007;
        }
        return dp[n] ;
    }
}
