package code;

import java.util.*;

/**
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 */
public class Node {

    private Integer val;
    private Node next;
    private Node randomNode;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.randomNode = null;
    }

    /**
     * map法：
     *      1.先构造每个node，令其next、randomNode为null
     *      2.将构造的节点存入map中，key为原对象，val为复制对像
     *      3.由于map中的每个key都是原对象，val为复制对象：
     *          因此通过key，我们可以拿到key对应的val、key的next、key的randomNode
     *          这时，我们通过 next、randomNode就可以获取其对应的复制对象了，
     *          最后，在将 key的复制对象的next、randomNode指向key.next、key.randomNode即可
     *          时间复杂度 2N,空间复杂度N
     * @param head
     * @return
     */
    public Node copyRandomList1(Node head) {
        if (head == null) return null;
        Node cur = head;
        Map<Node,Node> map = new HashMap<>();
        while (cur.next != null) {
            map.put(cur,new Node(cur.getVal()));
            cur = cur.next;
        }

        while (cur.next != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).randomNode = map.get(cur.getRandomNode());
            cur = cur.next;
        }
        return null;
    }

    /**插分法
     * 将复制的节点插在原节点的后面，然后再为复制的节点设置 randomNode、next
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        Node cur = head;

        while (cur != null) {
            Node node = new Node(cur.getVal());
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }

        cur = head;
        while ( cur != null ) {
            Node p = cur.next;
            if (cur.randomNode != null)
            p.randomNode = cur.randomNode.next;
            cur = p.next;
        }
        cur = head;
        Node copyHead = head.next;

        while (cur != null) {
            Node p = cur.next;
            cur.next = p.next;
            if (p.next != null)
            p.next = p.next.next;
            cur = cur.next;
        }
        return copyHead;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public Node getRandomNode() {
        return randomNode;
    }

    public void setRandomNode(Node randomNode) {
        this.randomNode = randomNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(val, node.val) && Objects.equals(next, node.next) && Objects.equals(randomNode, node.randomNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next, randomNode);
    }
}
