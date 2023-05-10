/**
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 */
public class Solution02 {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 && s == null) return 0;
        if (s.length() == 1) return 1;
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];
        dp[0] = 1;

        for (int i = 1; i < chars.length; i++) {
            int sbuLen = 0;
            for (int j = i - dp[i - 1]; j < i; j++ ) {
                if (chars[j] == chars[i]) {
                    sbuLen = 0;
                }else {
                    sbuLen++;
                }
            }
            dp[i] = sbuLen + 1;
        }


        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (max < dp[i]) max = dp[i];
        }
        return max;
    }

    public int lengthOfLongestSubstring1(String s) {

        if (s.length() == 0 && s == null) return 0;
        if (s.length() == 1) return 1;

        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            String substring = s.substring(i - dp[i - 1], i);
            int index = substring.indexOf(s.charAt(i));
            if (index == -1) {
                dp[i] = dp[i - 1] + 1;
            }else {
                dp[i] = dp[i - 1] - index;
            }
        }
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (max < dp[i]) max = dp[i];
        }
        return max;
    }

    public static void main(String[] args) {
        Solution02 solution02 = new Solution02();
        System.out.println(solution02.lengthOfLongestSubstring1("pwwkew"));
    }
}
