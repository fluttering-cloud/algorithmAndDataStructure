/**
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，
 * 每个礼物都有一定的价值（价值大于 0）。你
 * 可以从棋盘的左上角开始拿格子里的礼物，并
 * 每次向右或者向下移动一格、直到到达棋盘的
 * 右下角。给定一个棋盘及其上面的礼物的价值，
 * 请计算你最多能拿到多少价值的礼物？
 *
 *
 * 思路：
 *    1.由题易得：棋盘 (0,0) 位置的最大价值就是 （0，0）位置的值
 *              棋盘 （0，n） 位置的最大价值就是 （0，n - 1） 位置的最大价值 + （0，n）位置的价值
 *              棋盘 （n，0） 位置的最大价值就是 （n - 1 ,0） 位置的最大价值 + （n，0）位置的价值
 *              棋盘 （x，y） 位置的最大价值就是棋盘（x,y） 的价值 + MAX((x - 1,y),(x,y -1))
 *
 *   本题有如下特征
 *      1.求最优解--最大价值
 *      2.每个状态都需要用到先前的状态---重叠子问题
 *      所以本题是一个动态规划
 *
 *   解题思路：
 *      1.状态定义：构建最优解模型，理清哪些是自变量
 *      2.确定初始状态
 *      3.确定状态转移方程
 *      4.返回值
 */

public class Solution03 {

    public static void main(String[] args) {
        //int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int[][] grid = {{1,2,5},{3,2,1}};
        Solution03 solution03 = new Solution03();
        System.out.println(solution03.maxValue(grid));
        //solution03.maxValue(grid);
    }

    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];
    }

}
