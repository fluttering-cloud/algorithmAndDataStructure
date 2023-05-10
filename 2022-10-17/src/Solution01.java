import java.util.HashSet;
import java.util.Set;

public class Solution01 {

    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            if (set.contains(nums[i])) {
                return nums[i];
            } else {
                set.add(nums[i]);
            }
        }
        return 0;
    }

    public int findRepeatNumber1(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (i == nums[i]){
                i++;
                continue;
            }
            if (nums[i] == nums[nums[i]]) return nums[i];
            int temp = nums[i];
            nums[i] = nums[temp];
            nums[temp] = temp;
        }
        return -1;
    }



    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;
        while (i < matrix.length && j > 0){
            if (matrix[i][j] == target) return true;
            if (target < matrix[i][j]){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }

    public int minArray(int[] numbers) {
        int i = 0;
        int j = numbers.length -1;
        while (i != j){
            int m = i + (j  - i) / 2;
            if (numbers[m] > numbers[j]) {
                i = m + 1;
            }else if (numbers[m] < numbers[j]){
                j = m;
            }else j--;
        }
        return numbers[i];
    }

}
