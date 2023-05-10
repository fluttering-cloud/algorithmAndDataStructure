/**
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面
 * 的字符可以出现任意次（含0次）。在本题中，匹配是指字
 * 符串的所有字符匹配整个模式。例如，字符串"aaa"与
 * 模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"
 * 均不匹配。
 *
 *
 * 思路：
 *        字符串的匹配其实本质就是一个一个字符的匹配，
 *     但是在本题中，由于正则表达式里有两个特色的
 *     字符，因此匹配变得困难了一些
 *        首先，我们来分析一下：
 *        ‘.’ 可以和任何一个字符匹配
 *        ‘*’ 表示它前面的字符可以出现任意次
 *     其实，‘.’ 还好处理，是一个万能字符，和谁匹配都
 *     可以，但是 '*' 的话，和前面的字符有关联，因此遇
 *     到 ‘*’ 的话就需要查找前面的状态，根据这个性质，
 *     我们可能就需要用一个表或者其他的什么东西来把前面
 *     的状态存储起来。
 *
 */

public class Solution02 {

    public boolean isMatch(String s, String p) {

        int sLen = s.length() + 1;
        int pLen = p.length() + 1;
        boolean[][] matchTable = new boolean[sLen][pLen];
        matchTable[0][0] = true;

        // 初始化状态定义
        for (int i = 1; i < pLen; i++) {
            if (s.charAt(0) == p.charAt(i - 1) || p.charAt(i - 1) == '.' ) {
                matchTable[i] = matchTable[i - 1];
            }else if (p.charAt(i - 1) == '*') {
                matchTable[i] = matchTable[i - 2];
            }
        }
        for (int i = 1; i < sLen; i++) {
            for (int j = 1; j < pLen; j++) {
                if (p.charAt(j - 1) == '*') {
                    matchTable[i][j] =
                            matchTable[i][j - 2] || (matchTable[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2)));
                } else if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    matchTable[i][j] = matchTable[i - 1][j - 1];
                }
            }
        }
        return matchTable[sLen][pLen];
    }

}
