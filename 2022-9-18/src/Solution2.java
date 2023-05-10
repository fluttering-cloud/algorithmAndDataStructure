/**
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 */

public class Solution2 {

    public static void main(String[] args) {
        Solution2 solution02 = new Solution2();
        int nums[] = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution02.maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[1];
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i],dp[i - 1] + nums[i]);
        }
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] > max) max = dp[i];
        }
        return max;
    }
}
