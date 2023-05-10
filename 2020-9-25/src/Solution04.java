public class Solution04 {

    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMAXHeap(nums,heapSize);
        for (int i = nums.length - 1; i > nums.length - k + 1; --i) {

        }
        return 0;
    }


    private void buildMAXHeap(int[] array, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeapify(array,i,heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1;
        int r = i * 2 + 2;
        int largest = i;
        if (a[l] > a[largest]) {
            largest = l;
        }
        if (a[r] > l) {
            largest = r;
        }
        if (l != i) {
            swap(a, largest,i);
            maxHeapify(a,i,heapSize);
        }
    }

    public void swap(int[] a, int largest, int i) {

    }

    public static void main(String[] args) {
        int[] a = new int[0];
        int[] a1 = null;
        System.out.println();
    }

}
