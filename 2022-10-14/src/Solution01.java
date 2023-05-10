public class Solution01 {

    int[] temp;
    int tempIndex;
    int count;
    public int reversePairs(int[] nums) {
        count = 0;
        merger(nums,0,nums.length - 1);
        return count;
    }

    public void merger(int[] nums, int l, int r){
        if (l >= r){
            return;
        }
        int leftR = l+(r - l) / 2;
        int rightL = leftR + 1;
        int i = l;
        int j = rightL;
        merger(nums,l,leftR);
        merger(nums,rightL,r);
        //sort
        temp = new int[r - l + 1];
        tempIndex = 0;
        while (i <= leftR && j <= r) {
            if (nums[i] <= nums[j]) {
                temp[tempIndex++] = nums[i++];
            }else {
                //在这里统计逆序
                temp[tempIndex++] = nums[j++];
                count = count + leftR - i + 1;
            }
        }
        if (i <= leftR){
            while (i <= leftR){
                temp[tempIndex++] = nums[i++];
            }
        }
        if (j <= r) {
            while (j <= r){
                temp[tempIndex++] = nums[j++];
            }
        }
        i = l;
        j = 0;
        while (i <= r){
            nums[i++] = temp[j++];
        }
    }



    public void quickSort(int[] nums, int l, int r){
        if (l >= r){
            return;
        }

        // 找哨兵，
        int i = partition(nums,l,r);

        // 对哨兵两边的数进行排序，通常哨兵一边的所有数都是大于（或者小于）哨兵的
        quickSort(nums,l, i - 1);
        quickSort(nums,i + 1, r);
    }


    public int partition(int[] nums, int l, int r){
        int i = l;
        int j = r;
        while (i < j){
            while (i < j && nums[l] <= nums[j]) j--;
            while (i < j && nums[i] <= nums[l]) i++;
            swap(nums,i,j);
        }
        swap(nums,i,l);
        return i;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
