/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，
 * ……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，
 * 用来计算一个数字有多少种不同的翻译方法。
 *
 * 示例 1:
 *      输入: 12258
 *      输出: 5
 *      解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 *     思路：
 *          当数字小于 10 的时候，只有一种翻译方法
 *          单数字 大于等于10 并 小于 26的时候，有 2 种翻译方法，否则只有一种方法
 *          当数字是3位数时，如 123，此时有如下两种选择：
 *              i：看成在 12 的基础上加一个3 此时的翻译方法为数字12的翻译方法
 *              ii: 看成 1 的基础上加一个 23 此时翻译方法为 1的翻译方法，
 *              所以翻译方法为 i + ii
 *          推的数字长度为 n 的时候，翻译方法为 n-1 的翻译方法 + n-2 的翻译方法的和，
 *          但是 数字的最后两位必须小于25
 */
public class Solution01 {

    public int translateNum(int num) {
        if (num < 10) return 1;

        if (num < 100) {
            if (num <= 25) return 2;
            else return 1;
        }

        String s = num + "";
        char[] chars = s.toCharArray();
        int n1 = 1;
        int n2 =  ( Integer.valueOf("" + chars[0] + chars[1]) > 25 ) ? 1:2;
        for (int i = 2; i < chars.length; i++) {
            int temp = ( Integer.valueOf("" + chars[i - 1] + chars[i]) <= 25 && chars[i - 1] != '0' ) ? n1 + n2 : n2;
            n1 = n2;
            n2 = temp;
        }
        return n2;
    }

    public static void main(String[] args) {
        Solution01 solution01 = new Solution01();
        //solution01.translateNum(12258);
        System.out.println(solution01.translateNum(506));
    }
}
