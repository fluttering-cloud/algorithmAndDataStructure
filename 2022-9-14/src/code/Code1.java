package code;

/**
    请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。

        数值（按顺序）可以分成以下几个部分：

        若干空格
        一个 小数 或者 整数
        （可选）一个 'e' 或 'E' ，后面跟着一个 整数
        若干空格
        小数（按顺序）可以分成以下几个部分：

        （可选）一个符号字符（'+' 或 '-'）
        下述格式之一：
        至少一位数字，后面跟着一个点 '.'
        至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
        一个点 '.' ，后面跟着至少一位数字
        整数（按顺序）可以分成以下几个部分：

        （可选）一个符号字符（'+' 或 '-'）
        至少一位数字
        部分数值列举如下：

        ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
        部分非数值列举如下：

        ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 该题的核心就是构造辨识数值的状态转换机
 */
public class Code1 {

    /**
     * d:数字
     * s:符合
     * ' '：空格
     * '.':小数点
     * 'e': E/e
     */

    private static Map[] map = {
            new HashMap() {{ put(' ', 1); put('d', 3); put('s', 2); put('.', 4); }},  // 0
            new HashMap(){{ put(' ',1); put('s', 2); put('d',3); put('.', 4); }},     // 1
            new HashMap(){{ put('d',3); put('.', 4); }},                              // 2
            new HashMap(){{ put('d',3); put('.', 4); put('e',6); }},                  // 3
            new HashMap() {{ put('d',5); }},                                          // 4
            new HashMap() {{ put('d',5); put('e',6); }},                              // 5
            new HashMap() {{ put('d',8); put('s',7); }},                              // 6
            new HashMap() {{ put('d',8); }},                                          // 7
            new HashMap() {{ put('d',8); put('.',9); }},                              // 8
            new HashMap(){{ put('d',10); }},                                          // 9
            new HashMap(){{ put('d',10); put(' ',11);}},                              // 10
            new HashMap(){{ put(' ',11); }}                                           // 11
    };


    public static void main(String[] args) {
        System.out.println(isNumber("+123.5"));
    }


    public static boolean isNumber(String s) {
        char[] chars = s.toCharArray();
        Integer status = 0;
        for (int i = 0; i < chars.length; i++) {
            char next;
            if (chars[i] >= '0' && chars[i] <= '9') {
                next = 'd';
            } else if (chars[i] == '.')  {
                next = '.';
            } else if (chars[i] == '+' || chars[i] == '-') {
                next = 's';
            } else if (chars[i] == 'E' || chars[i] == 'e') {
                next = 'e';
            } else return false;
            status = (Integer) map[status].get(next);
            if (status == null) return false;
        }
        return status == 3 || status == 5 || status == 8 || status == 10 || status == 11;
    }
}
