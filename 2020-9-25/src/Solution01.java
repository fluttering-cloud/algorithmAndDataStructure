class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

public class Solution01 {


    //  先在A中搜索，找到与B的根节点值相同的节点

    // 然后同时遍历两颗数，逐一比较各个节点

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }

        if (A.val == B.val) {
            boolean left = false;
            boolean right = false;
            if (B.left == null) {
                left = true;
            } else {
                left = isSubStructure(A.left,B.left);
            }
            if (B.right == null) {
                right = true;
            } else {
                right = isSubStructure(A.right,B.right);
            }
            return left && right;
        }

        return isSubStructure(A.left,B) || isSubStructure(A.right,B);


//        else {
//            if (isSubStructure(A.left,B) || isSubStructure(A.right,B)) return true;
//        }
//        return false;
    }

}
