import java.util.HashMap;
import java.util.Map;

class ListNode {
    int val;
    ListNode next;
}

public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode head;
        if (cur1.val <= cur2.val){
            head = cur1;
            cur1 = cur1.next;
        }else {
            head = cur2;
            cur2 = cur2.next;
        }
        ListNode h = head;
        while (cur1 != null && cur2 != null){
            if (cur1.val <= cur2.val){
                head.next = cur1;
                cur1 = cur1.next;
            }else {
                head.next = cur2;
                cur2 = cur2.next;
            }
            head = head.next;
        }
        if (cur1 == null){
           head.next = cur2;
        }else {
            head.next = cur1;
        }
        return h;
    }


    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        while (A != B){
            A = A == null ? B : A.next;
            B = B == null ? A : B.next;
        }
        return A;
    }

    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        int[] res = new int[2];
       while (i != j){
           if (nums[i] + nums[j] == target){
               res[0] = nums[i];
               res[1] = nums[j];
           } else if (nums[i] + nums[j] > target) {
               j--;
           }else {
               i++;
           }
       }
       return null;
    }

    public String reverseWords(String s) {
        String trim = s.trim();
        char[] chars = trim.toCharArray();
        int i = chars.length - 1;
        int j ;
        int k;
        StringBuilder stringBuilder = new StringBuilder();
        while (i >= 0){
            if (chars[i] ==' '){
                i--;
                continue;
            }
            j = i;
            while (j >= 0 && chars[j] != ' '){
                j--;
            }
            k = j + 1;
            while (k <= i){
                stringBuilder.append(chars[k]);
                k++;
            }
            stringBuilder.append(' ');
            i = j;
        }
        return stringBuilder.toString().trim();
    }


    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(4^4);
    }

    public int hammingWeight1(int n) {
      long flag= 1;
      int count = 0;
      while (flag <= n){
          if ((n & flag) == flag){
              count++;
          }
          flag = flag << 1;
      }
      return count;
    }

    public int hammingWeight(int n){
        int res = 0;
        while (n != 0){
            res = n & 1;
            n >>>= 1;
        }
        return res;
    }

    public int[] singleNumbers(int[] nums) {
        int m = 1,x = 0,y = 0,n = 0;
        for (int num : nums) {
            n ^= num;
        }

        while ((n & m) == 0) {
            m = m << 1;
        }

        for (int num : nums) {
            if ((num & m) != 0) x ^= num;
            else y ^= num;
        }
        return new int[]{x,y};
    }
}
