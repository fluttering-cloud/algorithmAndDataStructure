import java.util.ArrayList;
import java.util.Stack;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
}

public class Solution01 {


    /**
     * 树的迭代（循环）中序遍历
     */

    public void iteratorTree(TreeNode root){
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();

        while (root != null || stack != null) {

             while (root != null) {
                 list.add(root.val);
                 stack.push(root);
                 root = root.left;
             }
             root = stack.pop();
             list.add(root.val);
             root = root.right;

        }

    }
}
