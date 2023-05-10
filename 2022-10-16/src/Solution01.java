import java.math.BigDecimal;

public class Solution01 {

    public String minNumber(int[] nums) {
        quick_sort(nums,0, nums.length - 1);
        String s = "";
        for (int i = 0; i < nums.length; i++) {
            s = s + nums[i];
        }
        return s;
    }


    public void quick_sort(int[] array, int l,int r) {

        while (l < r){
            int partition = partition(array, l, r);
            if (partition - l < r - partition){
                quick_sort(array,l,partition - 1);
                l = partition + 1;
            }else {
                quick_sort(array,partition + 1,r);
                r = partition - 1;
            }
        }
    }

    public int partition(int[] array, int l, int r){
        int i = l;
        int j = r;
        while (i < j) {
            while (i < j
                    && Long.valueOf("" + array[j] + array[l] ) >= Long.valueOf("" + array[l]+ array[j])) j--;
            while (i < j &&
                    Long.valueOf("" + array[i] + array[l] )  <= Long.valueOf("" + array[l] + array[i])) i++;
        swap(array,i,j);
        }
        swap(array,i,l);
        return i;
    }


    public void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {

    }
}
