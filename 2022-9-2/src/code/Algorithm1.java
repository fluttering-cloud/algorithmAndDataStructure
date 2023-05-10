package code;

import java.util.*;

/**
 *      给一个数组array，再给一个查询列表，列表的前
 *  两位数代表array的下标，分别为 startIndex、endIndex;
 *  列表的第三位数是查询目标target，请你返回 target 在 array
 *  的 startIndex ~ endIndex中出现的次数 count
 *      例如：
 *          array= {1,2,2,3} 列表为 (0,3,2),所以输出为 2
 */
public class Algorithm1 {

    private static Map<Integer, TreeSet<Integer>> map = new HashMap<>();
    private Integer[] array;

    private void initMap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            TreeSet<Integer> set;
            if (!map.containsKey(array[i])) {
                set = new TreeSet<>();
            } else{
                set = map.get(array[i]);
            }
            set.add(i);
            map.put(array[i],set);
        }
    }

    public int count(int[] array, int startIndex, int endIndex, int countNum) {
        TreeSet<Integer> set = map.get(countNum);
        if (set.isEmpty() || set == null) {
            initMap(array);
        }
        set = map.get(countNum);
        Integer[] integers = set.toArray(new Integer[set.size()]);
        int count = 0;
        for (Integer integer : integers) {
            if (integer >= startIndex && integer <= endIndex) {
                count++;
            }
        }
        return count;
    }
}
