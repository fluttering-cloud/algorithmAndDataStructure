/**
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 *
 * 思路：
 *    由题易得：
 *      1.当 n = 1 时，1 <= s <= 6,且 P1(s) = 1/6
 *      2.因为色子之间的点数都是相互独立的，即上一个色子的点数
 *        与下一个的无关，所以：
 *          n = 2,时 2 <= s <= 12,且：
 *              2点可以看作两个 1点 相加 所以 P[2](2) = P1(1) * P1(1)
 *              3点的概率为：P2(3) = P1(1) *  P1(2) + P1(2) * P1(1)
 *      3.由上可得状态转移公式为：
 *          P[N](n) = P[N-1](n - 1) * P1(1) +  P[N-1](n - 2) * P1(2) + ... P[N-1](n - 6) * P1(6)
 *
 */
public class Solution01 {

    public double[] dicesProbability(int n) {

        double[] dp = new double[6];
        for (int i = 0; i < 6; i++) {
            dp[i] = 1.0/6.0;
        }


        for (int i = 2; i < n; i++) {
            double tem[] = new double[5 * i - 1];
            for (int j = 0; j <= dp.length; j++) {
                for (int k = 1; k <= 6; k++) {
                    // 使用加的话，可以防止出现2 - 6之类的情况
                    tem[j + k] = dp[j + k] + dp[j] /6.0;
                }
            }
            dp = tem;
        }
        return dp;
    }

}
