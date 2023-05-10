import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

// 两个线程交替输出小于10的奇数、偶数，要求线程1输出奇数、线程二输出偶数
public class Solution {

    static volatile int i;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor
                = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());


        ExecutorService executorService = Executors.newFixedThreadPool(5);
        
    }




    public int translateNum(int num) {
        if(num < 10) return 1;

        if (num < 100){
            if (num < 25) return 2;
            else return 1;
        }

        String str = num + "";
        char[] chars = str.toCharArray();
        // n 存储 i - 1 位置的方法种类
        // m 存储 i - 2 位置的方法种类
        int n = 1, m = 1;

        for (int i = 1; i < chars.length; i++) {
            int temp = Integer.valueOf(chars[i] + "" + chars[i - 1]);
            if (temp <= 25){
                n = n + m;
            } else {
                m = n;
            }
        }
        return  n;
    }

    // n个骰子，求每个点出现的概率
    public double[] dicesProbability(int n) {
        // 1个骰子，则每个都是1/6
        double[] dp = new double[6];
        Arrays.fill(dp,1.0/6.0);

        for (int i = 2; i <= n; i++) {
            double[] temp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++){
                for (int k = 0; k < 6; k++){
                    temp[j + k] = temp[j + k] + dp[j] / 6.0;
                }
            }
        }
        return dp;
    }


    public TreeNode mirrorTree(TreeNode root) {
      if (root == null) return null;
      TreeNode temp = root.left;
      root.left = mirrorTree(root.right);
      root.right = mirrorTree(temp);
      return root;
    }





}

class TreeNode{
    TreeNode left;
    TreeNode right;
}