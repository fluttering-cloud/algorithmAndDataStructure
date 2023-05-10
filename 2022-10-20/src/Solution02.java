class ListNode {
    int val;
    ListNode next;
}

public class Solution02 {

    public ListNode deleteNode(ListNode head, int val) {
        ListNode cur = head;
        if (head.val == val){
            head = head.next;
            cur.next = null;
            return head;
        }

        while (cur.next != null && cur.next.val != val){
            cur = cur.next;
        }

        if (cur.next == null) return head;

        ListNode next = cur.next;
        cur.next = next.next;
        next.next = null;

        return head;
    }

    public int[] exchange(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r){
            if (l < r && (nums[l] & 2) == 1) l++;
            if (l < r && (nums[r] & 2) == 0) r--;
            swap(nums,l,r);
        }
        return nums;
    }

    void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public ListNode getKthFromEnd1(ListNode head, int k) {
        int count = 0;
        ListNode cur = head;
        while (cur != null){
            count++;
            cur = cur.next;
        }

        count = count - k + 1;
        cur = head;
        while (count > 0){
            cur = cur.next;
            count --;
        }
        return cur;
    }

    // 快慢指针法
    public ListNode getKthFromEnd(ListNode head, int k){
        ListNode fCur = head;
        ListNode sCur = head;
        while (fCur.next != null){
            fCur = fCur.next;
            if (k > 0)  k--;
            if (k == 0) sCur = sCur.next;
        }

        return sCur;
    }

}
