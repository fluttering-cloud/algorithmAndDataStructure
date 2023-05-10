/**
 * 输入一个数组，使用双向快排进行排序，使其非降序排列
 */

public class Solution01 {
    public void quickSort(int[] array, int left, int right) {
        int l = left;
        int r = right;
        int midNum = array[(left + right) / 2];

        while (l < r) {

            while (array[l] < midNum) {
                l++;
            }
            while (array[r] > midNum) {
                r--;
            }

            if (l > r) break; // 当 l 过中点后而 r还没有过中点时有可能发生

            int temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            if (array[l] == midNum) l++;
            if (array[r] == midNum) r--;

        }

    }
}
