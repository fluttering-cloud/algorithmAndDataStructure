import java.util.Arrays;

public class Solution {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k > arr.length) return arr;
        return quick_sort(arr,0, arr.length - 1, k);
    }

    public int[] quick_sort(int[] array, int l, int r, int k){
        int i = l;
        int j = r;
        while (i < j){
            while (i < j && array[l] <= array[j]) j--;
            while (i < j && array[l] >= array[i]) i++;
            swap(array,i,j);
        }
        swap(array,i,l);
        if (i > k) {
            quick_sort(array,l,i - 1,k);
        } else if (i < k){
            quick_sort(array,i + 1, r,k);
        }
        return Arrays.copyOf(array,k);
    }

    public void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
