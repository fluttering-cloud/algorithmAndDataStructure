public class Solution02 {


    public boolean isStraight(int[] nums) {
       quick_sort(nums,0, nums.length - 1);
       int count = 0;
       for (int i = 0; i < nums.length - 1; i++){
           if (nums[i] == 0){
               count++;
           } else {
               if (nums[i] == nums[i + 1]) {
                   return false;
               }else if (nums[i + 1] - nums[i] == 1)  {
                   continue;
               }else {
                   int gap = nums[i + 1] - nums[i] - 1;
                   count = count - gap;
               }
           }
       }
       if (count < 0) return false;
       return true;
    }

    public void quick_sort(int[]array,int l, int r){
        if (l >= r) return;
        int mid = array[l + (r - l) / 2];
        int i = l;
        int j = r;
        while (i < j) {
            while (array[j] > mid) j--;
            while (array[i] <  mid) i++;
            if (i > j) break;
            swap(array,i,j);
            if (array[i] == mid) i++;
            if (array[j] == mid) j--;
        }
        if (i < r) quick_sort(array,i,r);
        if (j > l) quick_sort(array,l,j);
    }

    public void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {
        Solution02 solution02 = new Solution02();
        int[] array = {6,9,5,1,2,4,8};
        solution02. quick_sort(array,0,array.length - 1);
        for (int i : array) {
            System.out.println(i);
        }
    }
}
