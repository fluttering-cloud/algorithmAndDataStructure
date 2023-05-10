class Node{
    int val;
    Node next;
    Node randomNode;
}

public class Solution02 {

   public Node reverseLinkList(Node head) {
       if (head == null) return null;
       Node pre = head;
       Node cur = head.next;
       if (cur == null) return head;
       Node next = cur.next;
       while (next != null) {
           cur.next  = pre;
           pre = cur;
           cur = next;
           next = next.next;
       }
       cur.next = pre;
       head.next = null;
       return cur;
   }

   public Node copyRandomList(Node head) {

       if (head == null) return null;
       Node cur = head;
       while (cur != null) {
       }


       return null;
   }
}
