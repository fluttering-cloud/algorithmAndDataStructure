package code;

/**
 *  给出一个链表head，给两个数left、right，请反转联邦left到right的元素
 *  例如：
 *     head 为 1 2 3 4 5
 *     left 为 2 ； right为 4
 *     则反转后的链表为 1 4 3 2 5
 *
 */

class Node {
    Node next;
}

public class Solution {

    public Node reverse(Node head, int left, int right) {

        if (left == 1) {
            doReverse(head,right -  right);
        }

        int step = left - 2;
        Node leftHead = head;
        while (step > 0) {
            leftHead = leftHead.next;
        }

        Node node = doReverse(leftHead.next, right - left);
        leftHead.next = node;
        return head;
    }

    public Node doReverse(Node head, int step) {
        Node p1 = head;
        Node p2 = p1.next;
        Node p3 = null;
        while (step > 0) {
            p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            step --;
        }
        head.next = p2;
        return p1;
    }
}
