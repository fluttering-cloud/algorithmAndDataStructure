import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

    /*
    时间复杂度：2k
    空间复杂度 m + n + k
    时间复杂度O(n) 空间复杂度O(n)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int[] m = new int[Integer.valueOf(strs[0])];
        int[] n = new int[Integer.valueOf(strs[1])];
        int k = Integer.valueOf(strs[2]);
        Set<String> set = new HashSet<>();
        for (int i = 0; i < k; i++){
            String s =sc.nextLine();
            set.add(s);
            strs = s.split(" ");
            m[Integer.valueOf(strs[0]) - 1] += 1;
            n[Integer.valueOf(strs[1]) - 1] += 1;
        }

        int count = 0;
        int c = 0;
        for (String s : set) {
            strs = s.split( " ");
            c = m[Integer.valueOf(strs[0]) - 1] + n[Integer.valueOf(strs[1]) - 1] - 1;
            if (c > count) count = c;
        }
        System.out.println(count);
    }

}
