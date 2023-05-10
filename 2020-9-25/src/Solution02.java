import java.util.LinkedList;

public class Solution02 {

    public int findKthLargest(int[] nums, int k) {
        quickSort(nums,0,nums.length - 1);
        return nums[k];
    }


    private void sort1(int[] nums) {

        for (int gap = nums.length / 2; gap > 0; gap = gap / 2) {

            for (int i = gap; i < nums.length; i++) {
                int j = i;
                int temp = nums[j];
                if (nums[j] > nums[j - gap]) {
                    while (j - gap >= 0 && temp > nums[j - gap]) {
                        nums[j] = nums[j - gap];
                        j = j - gap;
                    }
                    nums[j] = temp;
                }
            }
        }
    }


    private void sort2(int[] nums) {
        quickSort(nums,0,nums.length - 1);
    }

    private void quickSort(int[] nums, int left, int right) {
        int l = left;
        int r = right;
        int mid = nums[l + (r - l) /2];
        while (l < r) {
            while (nums[l] > mid) {
                l ++;
            }
            while (nums[r] < mid) {
                r --;
            }
            if (l > r) break;

            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;

            if (nums[l] == mid) l++;
            if (nums[r] == mid) r--;
        }
        if (l < right) {
            quickSort(nums,l,right);
        }
        if (r > left) {
            quickSort(nums,left,r);
        }
    }



    private void mergerSort(int[] nums,int[] temp, int left, int right){
        int mid = left + (right - left) / 2;
        int l = left;
        int r = mid + 1;
        int count = 0;
        while (l <= mid && r <= right) {
            if (nums[l] > nums[r]) {
                temp[count++] = nums[l++];
            }else {
                temp[count++] = nums[r++];
            }
        }
        if (l < mid) {
            temp[count++] = nums[l++];
        }
        if (r < right) {
            temp[count++] = nums[r++];
        }

        l = left;
        count = 0;
        while (l < right) {
            nums[l] = temp[count++];
        }
    }

    public static void main(String[] args) {



    }
}
