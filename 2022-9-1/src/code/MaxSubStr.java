package code;

/**
 * 要求：输入一个字符串，求该字符串中的最长无重复字符的子字符串
 * 思路：因为要获得字符串中最长无重复字符的子字符串，所有我们必须要知道 str
 *      的每个位置的最长无重复字符的子字符串，设 i - 1 位置的最长无重复字
 *      符的子字符串为 str1 , 则 i 位置的最长无重复字符的子字符串有两种
 *      可能性：
 *          1. str 中不包含 字符串位置 i 中的字符，此时 i 位置的最长
 *             无重复字符的子字符串为 str1 + str 的 i 位置的字符
 *          2. str 中包含 字符串位置 i 中的字符，此时 i 位置的最长
 *      *      无重复字符的子字符串为 str1 的重复字符的后面的字符串 + str 的 i 位置的字符
 */
public class MaxSubStr {

    public String MaxSbuStr(String str) {

        char[] chars = str.toCharArray();
        String[] subStr = new String[chars.length];
        subStr[0] = chars[0] + "";

        for (int i = 1; i < chars.length; i++) {
            // preStr 是 str i - 1 位置的最长子字符串
            String preStr = subStr[i - 1];
            if (preStr.contains(chars[i] + ""))  { // 如果在preStr中包含 str 的 i 位置的字符
                int charIndex = preStr.indexOf(chars[i]);
                preStr = preStr.substring(charIndex, subStr[i - 1].length());
            }
            subStr[i] = preStr + chars[i];
        }

        int index = 0;
        int maxlength = 0;
        for (int i = 0; i < subStr.length; i++) {
            if (subStr[i].length() > maxlength) {
                maxlength = subStr[i].length();
                index = i;
            }
        }
        return subStr[index];
    }
}
