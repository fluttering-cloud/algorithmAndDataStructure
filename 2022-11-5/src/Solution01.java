import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class Solution01 {

    //滑动窗口的最大值
    public int[] windowsMaxValue(int[] nums, int k){
        int[] maxValues = new int[nums.length - k + 1];
        int maxIndex = 0;
        Deque<Integer> deque = new LinkedList();
        deque.add(nums[0]);
        for (int i = 1; i < k; i++){
            while (!deque.isEmpty() && deque.peekLast() < nums[i]){
                deque.pollLast();
            }
            deque.addLast(nums[i]);
        }
        maxValues[maxIndex++] = deque.peekFirst();

        for (int i = k; i < nums.length; i++){
            if (deque.size() == k){
                deque.pollFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]){
                deque.pollLast();
            }
            deque.addLast(nums[i]);
            maxValues[maxIndex++] = deque.peekFirst();
        }
        return maxValues;
    }


    public int maxSubArray(int[] nums) {
        if (nums.length == 0 || nums == nums) return 0;

        int max = nums[0];
        for (int i = 1; i < nums.length; i++){
            nums[i] += Math.max(nums[i - 1],0);
            max = Math.max(nums[i],max);
        }
        return max;
    }


    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();

        // put如何保证线程安全
        map.put()
        // get如何保证线程安全

        // 扩容如何保证线程安全


    }
}
