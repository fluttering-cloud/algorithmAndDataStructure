package code;

import java.util.Set;
import java.util.TreeSet;

/**
 * 给定一个数组 array （array内只有正数） 和一个正数 m
 * 返回 array 的所有子序列中累加和 %m 之后的最大值
 *      什么是子序列：
 *              即从 array中选取任意 n个元素组成的序列， 0<=n < array.length
 *      思考： 1. 如果 array 中每个数字不大，怎么写这道题
 *            2. 如果 array 中 m 的值很小，怎么做这道题
 *            3. 如果 array 中的长度很小，但是array中的数字比较大且比 m 大如何解决呢
 *            4. 若 array 的和到达 10^9，m也可以到达 10^9、array.length比较小(比如35)该如何解呢
 */

public class Dp {

    /**
     * 暴力算法
     * 时间复杂度  2^N
     */
    public int solution1(int[] array, int m) {
        Set<Integer> set = new TreeSet<>();
        int sum = 0;
        int index = 0;
        int max = 0;
        add(array,index,sum,set);
        for (Integer integer : set) {
            if ( (integer % m) > max)
                max = integer % m;
        }
        return max;
    }

    public void add(int[] array, int index, int sum, Set<Integer> set) {
        if (index == array.length) {
            set.add(sum);
        } else {
            add(array,index + 1, sum, set);
            add(array,index + 1, sum + array[index], set);
        }
    }



    /**
     * 动态规划
     */

    /***
     * 状态表法
     * 因为 array 内只有 正数，所以 array 的子序列的最大值为 maxSum = array[0] + ......+ array[length - 2 ] + array[length - 1 ]
     * 因此我们可以构造如下状态表: dp[][]
     *          0 1 2 3 4 5 .。。。。。 maxSum
     *      0
     *      1
     *      2
     *      3
     *      ...
     *      array.length - 1
     * 其中 dp[m][n] 表示  array[0] 到 array[m] 中有子序列的和为 m,若有这个序列，则 dp[m][n] 为 true, 否则为 false
     *      由此易得:
     *             1. dp[m][0] = true, 0 <= m < array.length  (因为只有  array[0]时，我可以不使用 array[0])
     *             2. dp[0][array[0]] = true. (因为 array[0] 肯定在 0 到 array内所有元素的和 之间)
     *
     * 又因为 dp[i][m] 可以根据以下规则求取
     *          1. dp[i][m] = dp[i - 1][m]  (即使用 a[0] 到 a[i - 1] 求 m)
     *          2. dp[i][m] = dp[i - 1][m - array[i]] (即使用 a[0] 到 a[i - 1] 求 m - array[i])
     *
     * 使用以上规则，我们就可以求出  0 ~ maxSum 中的哪些数是可以被 array 内的子序列相加得到的
     * 然后用这些数 %m 就可以求得 目标数
     *
     * 时间复制度 O(N^2)
     * 适用于 array内所有的数的和小于 10^8
     */
    public int solution2(int[] array, int m) {

        int N = array.length;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        boolean[][] dp = new boolean[N][sum + 1];

        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][array[0]] = true;

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {

                // 不使用 array[i]
                dp[i][j] = dp[i - 1][j];

                // 使用 array[i]
                if (j - array[i] >= 0) {
                    dp[i][j] = dp[i][j] | dp[i][j - array[i]];
                }
            }
        }

        int max = 0;
        for (int i = 0; i <= sum; i++) {
            // 选用最后一行是因为最后一行表示 array[0] 到 array[length - 1 ] 中的若干数的和
            if (dp[N - 1][i]) {
                max = Math.max(max, i % m);
            }
        }

        return max;
    }

    /**
     *  该方法与 solution2 的思路一样，但是把 dp 的列换成了 1 到 m-1
     *  该解法适用于 m 不是很大，而 array 的子序列和很大的情况
     */
    public int solution3(int[] array, int m) {

        int N = array.length;
        boolean[][] dp = new boolean[N][m];

        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][array[0] % m] = true;

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < m; j++) {
                int cur = array[i] % m;
                dp[i][j] = dp[i - 1][j];
                if (cur <= j) {   // cur + x = j  x <= j < m
                    dp[i][j] = dp[i - 1][j - cur] | dp[i][j];
                } else {
                    dp[i][j] = dp[i - 1][j + m - cur] | dp[i][j];
                }
            }
        }

        int max = 0;
        for (int i = 0; i < m; i++) {
            if (dp[N-1][i]) max = Math.max(max,i);
        }
        return max;
    }

    /**
     * 当 m 和 array 的和都达到 10^9次方， array.length为 35时的解法
     * 思路：
     *      当 m 和 array 的和都达到 10^9次方的时候，显然无论使用 solution2 或者 solution3 都差不多
     *    而这里又提示array.length为 35，那么会不会利用 array.length来解题呢。
     *      先来估算一下，在这种情况下的暴力解法的数据规模为多少。既然 array.length 为 35，那么采用暴力解法
     *    的数据规模为 2^35次方，约为 10^10次方。好家伙，以为看到了希望，没想到凉凉了
     *      既然以上提及到的方法都有着大数据集的问题，那么我们应该想想怎么将数据集减少。首先，m 和  array的和都
     *    减小不了，这是客观的。那么我们应该从 array.length 入手。 由加法易得
     *     (a + b + c + d) % m = { (a + b) % m + (c + d) % m } % m，
     *    由此，我们将 array 一分为二后 %m 再进行筛选，这样数据集就变成了 2^17 + 2^18  << 2^35
     */
    public int solution4(int[] array, int m) {
        if (array.length == 1) {
            return array[0] % m;
        }
        int mid = (0 + array.length) / 2;
        TreeSet<Integer> sortSet1 = new TreeSet();
        TreeSet<Integer> sortSet2 = new TreeSet();
        getMod(array, m,0,0,mid - 1,sortSet1);
        getMod(array,m,0,mid,array.length - 1, sortSet2);

        // 这里的时间复杂度为 log2( N )
        int max = 0;
        for (Integer left : sortSet1) {
            max = Math.max(max, left + sortSet2.floor(m - 1 - left));
        }
        return max;
    }

    private void getMod(int[] array,int m, int sum, int index, int end,Set sortSet) {

        if (index >= array.length) return;

        if (index == array.length - 1) {
            sortSet.add(sum % m);
        }
        getMod(array,m,sum,index + 1, end, sortSet);
        getMod(array, m, sum + array[index],index + 1, end, sortSet);
    }

}
