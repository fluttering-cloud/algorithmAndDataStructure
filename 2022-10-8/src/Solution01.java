import java.util.*;

class TreeNode {
    int val;
    TreeNode right;
    TreeNode left;
}

class Node {
    int val;
    Node right;
    Node left;
}

public class Solution01 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> list = new ArrayList<>();
        if (root != null) queue.offer(root);

        while (!queue.isEmpty()) {
            LinkedList<Integer> tem = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                // list的长度就是树的层数
                if (list.size() % 2 == 0) tem.addLast(node.val);
                else tem.addFirst(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            list.add(tem);
        }
        return list;
    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        LinkedList<Integer> temp = new LinkedList<>();
        temp.addLast(root.val);
        search(root,target,0,list,temp);
        temp.removeLast();
        return list;
    }

    public void search(TreeNode node,int target, int sum, List<List<Integer>> list,LinkedList<Integer> temp) {
        int sumTemp = sum + node.val;

        if (target == sumTemp
                && node.left == null && node.right == null) {
            List<Integer> tar = new ArrayList<>(temp);
            list.add(tar);
            return;
        }

        if (node.left != null) {
            temp.addLast(node.left.val);
            search(node.left,target,sumTemp,list,temp);
            temp.removeLast();
        }
        if (node.right != null) {
            temp.addLast(node.right.val);
            search(node.right,target,sumTemp,list,temp);
            temp.removeLast();
        }
    }

    Node head,pre,cur;
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void dfs(Node cur) {
        if (cur == null) return;
        dfs(cur.left);
        if (pre != null) pre.right = cur;
        else head = cur;

        cur.left  = pre;
        pre = cur;

        dfs(cur.right);
    }


    char[] c;
    List<String> list = new ArrayList<>();
    public String[] permutation(String s) {

        if (s == null) return null;
        c = s.toCharArray();
        dfs(0);

        return list.toArray(new String[list.size()]);
    }

    public void dfs(int  i) {
        if (i == c.length - 1) {
            list.add(String.valueOf(c));
        }
        HashSet<Character> set = new HashSet();
        for (int j = i; j < c.length; j++) {
            if (set.contains(c[j])) continue;
            set.add(c[j]);
            swap(i,j);
            dfs(i + 1);
            swap(i,j);
        }
    }

    public void swap(int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }


    int kMax = 0;
    int search =  0;
    public int kthLargest(TreeNode root, int k) {
        search = k;
        dfs(root);
        return kMax;
    }

    public void dfs(TreeNode node) {
        if (node  == null) return;
        dfs(node.right);
        search--;
        if (search == 0) kMax = node.val;
        dfs(node.left);
    }


}
