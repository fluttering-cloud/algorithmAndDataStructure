public class Solution01 {


    public int cuttingRope(int n) {
         int[] dp = new int[n  +  1];
         dp[1] = 1;
         for (int i = 2; i <= n; i++){
             for (int j = 1; j < i; j++ ){
                 dp[i] = Math.max(dp[i],dp[i - j] * j );
                 dp[i] = Math.max(dp[i],(i - j) * j );
             }
             dp[i] = dp[i] % 1000000007;
         }
         return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(1000000007);
    }

    public int majorityElement(int[] nums) {
        int x = 0;
        int count = 0;
        for (int i = 0; i  < nums.length; i++) {
            if (count == 0){
                x = nums[i];
                count++;
            }else {
                if (x != nums[i]){
                    count--;
                }else {
                    count++;
                }
            }
        }
        return x;
    }

}
