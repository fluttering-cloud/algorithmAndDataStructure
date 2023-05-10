import java.util.*;

/**
 * 给一个字符串，去除字符串中重复的字母，并将出现过的字母按ascii大小排序输出
 */

public class Solution01 {

    public static void main(String[] args) {
        System.out.println();
    }



    /**
     * 给一个字符串，去除字符串中重复的字母，并将出现过的字母按ascii大小排序输出
     */
    public static void removeAndSort(String str){
        String[] s = str.split(" ");
        TreeSet<Character> set = new TreeSet<>();
        for (int i = 0; i < s.length; i++)  {
            char[] chars = s[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                set.add(chars[j]);
            }
        }
        Character[] characters = set.toArray(new Character[set.size()]);
        for (Character character : characters) {
            System.out.println(character);
        }
    }

    /**
     * 给一段字符串，统计字符串内每个字符出现的次数并输出
     */
    public static void count(String str) {
        char[] chars = str.toCharArray();
        TreeMap<Character,Integer> map = new TreeMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i],map.get(chars[i]) + 1);
            }else {
                map.put(chars[i],1);
            }
        }
        while (!map.isEmpty()) {
            Map.Entry<Character, Integer> firstEntry = map.pollFirstEntry();
            System.out.println(firstEntry.getKey()+ ":"+firstEntry.getValue());
        }

    }

    /**
     * 记忆化递归：fabbinic 数列
     */
    static List<Integer> fibanic;
    static {
        fibanic = new ArrayList<>();
        fibanic.add(1);
        fibanic.add(1);
    }
    public static int getNthFibnc(int x){
        if (x >= fibanic.size()) {
            int i = getNthFibnc(x - 1);
            int j = getNthFibnc(x - 2);
            fibanic.add(i + j);
            return  i + j;
        }else {
            return fibanic.get(x);
        }
    }

    /**
     * try-catch-finally 执行结果
     */
    public static int test() {
        int b = 10;
        try {
            b = b + 10;
           // return b;
            return 10;
        } finally {
            b = b + 10;
           // return b;
            return 20;
        }
    }



    public static double myPow1(double x, int n) {
        if(x == 0.0f) return 0.0d;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }

    public static double myPow(double x, int n) {
      if (x == 0.0d) return 0.0d;
      // 假如 n 是 int 能表示的最小的数，将其变为正数后会溢出
      long b = n;
      double res = 1.0d;
      if (n  < 0) {
          b = -b;
          x = 1 / x;
      }
      while (b > 0) {
          if ((b & 1) == 1) res = res * x;
          x *= x;
          b = b >>> 1;
      }
      return res;
    }

}
