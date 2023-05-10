import java.util.HashMap;
import java.util.Map;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
}


public class Solution01 {
    ThreadLocal<Integer> n1;
    volatile Integer n2;
    Integer n3;

    public static void main(String[] args) {
        Solution01 solution01 = new Solution01();
        solution01.n1.set(10);
        solution01.n2 = 10;
        solution01.n3 = 10;

       Thread t1 = new Thread(()->{
            solution01.n1.set(6);
            boolean interrupted = Thread.currentThread().isInterrupted();
        });
        t1.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();

        new Thread(()->{
            solution01.n2 = 10 - 1;
        }).start();

        new Thread(() -> {
           solution01.n3 = 10 - 1;
        }).start();

        // n1为10，因为ThreadLocal修饰的变量为线程独有
        System.out.println(solution01.n1);
        // n2 为 9，被volatile修饰的变量保证了可见性
        System.out.println(solution01.n2);
        // n3 的输出不确定
        System.out.println(solution01.n3);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null && right == null) return null;

        if (left == null) return right;
        if (right == null) return left;

        return root;
    }


    int[] pre;
    Map<Integer,Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = preorder;
        map = new HashMap();
        for (int i = 0; i < inorder.length; i++){
            map.put(inorder[i],i);
        }
        return curer(0,0,inorder.length - 1);
    }

    public TreeNode curer(int root,int left, int right) {
        if (left > right) return null;
        TreeNode node = new TreeNode();
        node.val = pre[root];
        Integer i = map.get(pre[root]); // root 在 inorder 数组中的下标值,i标识左子树最右边的节点

        node.left = curer(root + 1, left, i - 1);

        node.right = curer(i - left + 1 + root,i + 1,right);
        return node;
    }
}
