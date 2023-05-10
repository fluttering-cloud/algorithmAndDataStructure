import java.util.*;

public class Solution {

    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices == null) return 0;
        int res = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {

            if (Math.max(0,prices[i] - min) > 0){
                res += prices[i] - min;
                min = prices[i];
            }
            if (min >= prices[i]) min = prices[i];
        }
        return res;
    }


    public void rotate(int[] nums, int k) {
      int len = nums.length - 1;
      while (len > 0){
          if (len >= k){
              for (int i = k; i > 0; i--){
                  exchange(nums,len,len - k);
                  len--;
              }
          }else { // len < k

          }
      }
    }

    public void exchange(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public void rotate1(int[] nums, int k) {
        k = k % nums.length;
       reverse(nums,0,nums.length - 1);
       if (k > nums.length) return;
       reverse(nums,0, k - 1);
       reverse(nums,k, nums.length - 1);
    }

    private void reverse(int[] array, int left, int right) {
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }


    char[]  c;
    List<String>  list;
    public String[] permutation(String s) {
        c = s.toCharArray();
        list = new ArrayList<>();
        dfs(0);
        return list.toArray(new String[list.size()]);
    }

    private void dfs(int index){
        if (index == c.length) {
           // 排列完毕，加入结果
            list.add(String.valueOf(c));
        }
        Set<Character> set = new HashSet<>();
        for (int i = index; i < c.length; i++){
            if (set.add(c[i])){
                // exchange c[i] c[index]
                dfs(index + 1);
                // exchange c[i] c[index]
            }
        }

    }

    int max;
    int search;
    public int kthLargest(TreeNode root, int k) {
        search = k;
        dfs(root);
        return max;
    }

    private void dfs(TreeNode node){
        if (node == null) return;
        dfs(node.left);
        search--;
        if (search == 0) max = node.val;
        dfs(node.right);
    }

    int maxDeep;
    int leftVal;
    public int maxDepth(TreeNode root) {
        dfs1(root,0);
        return maxDeep;
    }

    private void dfs1(TreeNode node, int deep){
        if (node == null) return;
        deep += 1;
        if (deep > maxDeep){
            maxDeep = deep;
            leftVal = node.val;
        }
        dfs1(node.left,deep);
        dfs1(node.right,deep);
    }

    boolean is_balanced = true;
    public boolean isBalanced(TreeNode root) {
      dfs2(root);
      return is_balanced;
    }
    private int dfs2(TreeNode node){
        if (node == null || !is_balanced) return 0;
        int leftDeep = dfs2(node.left);
        int rightDeep = dfs2(node.right);
        if (Math.abs(leftDeep - rightDeep) > 1) is_balanced = false;
        return Math.max(leftDeep,rightDeep) + 1;
    }

    public int sumNums(int n) {
        boolean f = n > 1 && (n = n + sumNums(n - 1)) > 0;
        return n;
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if ((p.val >= root.val && q.val <= root.val) || (p.val <= root.val && q.val >= root.val)) return root;
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left,p,q);
        else return lowestCommonAncestor1(root.right,p,q);
    }

    // (f && t) || (t && f)    t && t
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root.val == p.val || root.val == q.val) return  root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;//
        return root;
    }


}
class TreeNode{
    TreeNode left;
    TreeNode right;
    int val;
}