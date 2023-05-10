package code;

import java.util.HashSet;
import java.util.Set;

/**
 * 给出一个由a-z中若干字符组成的字符串数组，求这个数组里有多少类
 * 不同类的字符串：组成字符串的字符是不同的。
 * 如  abccc 与 abc 是同一类
 *     avc  与 abc 不是同一类
 * @return
 */
public class FindSubStrClass {

    public int count1(String[] array) {

        Set<String> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            char[] chars = array[i].toCharArray();
            boolean[] flag = new boolean[26];

            // 求出每个字符串中的字符摘要
            for (int j = 0; j < chars.length; j++) {
                flag[chars[j] - 'a'] = true;
            }

            StringBuffer buffer = new StringBuffer();

            // 将摘要组成字符串并放入set集合，set集合保证了唯一性
            for (int k = 0; k < flag.length; k++) {
                if (flag[k]) buffer.append((char) ('a' + k));
            }
            set.add(buffer.toString());
        }
        return set.size();
    }

    /**
     * count2 与 count1 思路是一样的,时间复杂度也是一样的，
     * 但是 count2 使用位运算来记录摘要的信息，因此比 count1 更快
     *
     * @param array
     * @return
     */
    public int count2(String[] array) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            char[] chars = array[i].toCharArray();
            boolean[] flag = new boolean[26];

            // 求出每个字符串中的字符摘要
            for (int j = 0; j < chars.length; j++) {
                flag[chars[j] - 'a'] = true;
            }

            int key = 0;

            // 将摘要用二进制的位表示，二进制中的每一个位的0、1 表示该位对应的字符是否存在
            for (int k = 0; k < flag.length; k++) {
                if (flag[k]) key |= 1 << (k + 1);
            }
            set.add(key);
        }
        return set.size();
    }
}