package code;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完成二叉树的反转
 *  思路：
 *      因为一颗树进行了反转，其子树的位置也变了，
 *      因此反转一颗树后，将其子节点对换一下即可，
 *     本质就是树的宽度遍历
 */
public class ReserveBinaryTree {


    public Node reserve(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll.getLeftNode() != null) {
                queue.add(root.getLeftNode());
            }
            if (poll.getRightNode() != null) {
                queue.add(root.getRightNode());
            }
            Node tempNode = poll.getLeftNode();
            poll.setLeftNode(poll.getRightNode());
            poll.setRightNode(tempNode);
        }
        return root;
    }
}
