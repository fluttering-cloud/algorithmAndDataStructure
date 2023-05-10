class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }


public class Solution {

    int maxDeep = 0;
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        dfs(root,1);
        return maxDeep;
    }

    void dfss(TreeNode node,  int deep){
        if (node.left == null && node.right == null){
            if (deep > maxDeep) maxDeep = deep;
        }
        if (node.left != null) dfss(node.left,deep + 1);
        if (node.right != null) dfss(node.right, deep + 1);
    }


    boolean _isBalanced = true;
    public boolean isBalanced(TreeNode root) {
        if (root == null) return _isBalanced;
        int leftDeep = dfs(root.left);
        int rightDeep = dfs(root.right);
        if (Math.abs(leftDeep - rightDeep) > 1) _isBalanced = false;
        return _isBalanced;
    }

    int dfs(TreeNode node){
        if (node == null || !_isBalanced) return 0;
        int leftDeep = dfs(node.left);
        int rightDeep = dfs(node.right);
        if (Math.abs(leftDeep - rightDeep) > 1) _isBalanced = false;
        return Math.max(leftDeep,rightDeep) + 1;
    }


    public int sumNums(int n) {
        boolean x = n > 1 &&  (n = n + sumNums(n - 1)) > 0;
        return n;
    }


    /**
     * 寻找二叉排序树内两个节点的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     *
     * 思路：寻找二叉排序树中两个节点的最近各个祖先
     *      因为是二叉排序树，所以这两个节点有以下特性：
     *          1. 要么两个节点都大于根节点
     *          2.要么两个节点都小于根节点
     *          3.一个节点等于根节点，另一个节点大于或者小于根节点
     *      根据以上特性，可以得出以下规律
     *          1.要是两个节点都大于根节点，则两个节点的公共公共祖先肯定在树的右侧
     *          2.同理可得两个节点都小于根节点时的规律
     *      最后可得两个节点的最近公共祖先就是第一个出现的节点，该节点又以下特性：
     *         大于等于其中一个节点，小于另一个节点
     *         或者
     *         小于等于其中一个节点，大于另一个节点
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ac = null;
        if (root.val > p.val && root.val > q.val){
           ac = lowestCommonAncestor(root.left,p,q);
        }else if (root.val < p.val && root.val < q.val) {
           ac = lowestCommonAncestor(root.right,p,q);
        }else {
            return root;
        }
        return ac;
    }


    /**
     * 寻找二叉树的公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     *
     * 思路：
     *    因为二叉树内的节点都是无序的，所以不能根据节点的值去剪枝
     *    但是二次树中两个节点的公共祖先有以下特性：
     *       若一个节点f是其他两个节点的公共祖先，那么f的祖先也是这两个节点的公共祖先
     */
    boolean findP = false,isFindQ = false;
    TreeNode ac;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val == p.val && root.val == p.val)
        lowestCommonAncestor(root.left,p,q);
        lowestCommonAncestor(root.right,p,q);
    }


}
