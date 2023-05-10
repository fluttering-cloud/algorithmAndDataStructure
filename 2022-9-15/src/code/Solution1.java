package code;

/**
 * 将一颗二次排序树变为有序的双向链表，要求时间复杂度为O(n)，空间复杂度为O(1)
 */
class TreeNode {
    Integer val;
    TreeNode left;
    TreeNode right;
}
public class Solution1 {

    /**
     * @param root
     * @return
     *
     * 思路：
     *     1.后序遍历，将叶子节点和叶子节点的父节点变为双向链表，然后返回链表的两个端点
     *     2.父节点可以得到左、右子树形成的链表的两个端点，共4个端点
     *     3.将左子树的最大端点与父节点相连，将右子树的最小端点与父节点相连
     *     4.完成 3 后，将左子树的最小端点、右子树的最大端点返回，因为这两个端点是新链表的两个端点
     * 缺点：
     *     有空间浪费，我们每次形成新链表时，其实只需要左子树的最大端点、右子树的最小端点
     *
     * 思考：有没有什么方法使得时间复杂度为O(n),空间复杂度为O(1)呢
     */
    public TreeNode getList(TreeNode root) {
        toList(root);
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public TreeNode[] toList(TreeNode root) {
        if (root == null) return null;

        TreeNode[] left = toList(root.left);
        TreeNode[] right = toList(root.right);

        TreeNode[] nodes = new TreeNode[2];

        if (left == null) {
            nodes[0] = root;
        } else {
            root.left = left[1];
            left[1].right = root;
            nodes[0] = left[0];
        }
        if (right == null) {
            nodes[1] = root;
        } else {
            root.right = right[0];
            right[0].left = root;
            nodes[1] = right[1];
        }
        return nodes;
    }


}
