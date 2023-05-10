import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}

public class Solution01 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return true;
        return doIsSymmetric(root.left,root.right);
    }

    public boolean doIsSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if ( (left == null && right != null) || (left != null && right == null)) return false;
        if (left.val != right.val ) return false;
        return doIsSymmetric(left.left, right.right) && doIsSymmetric(left.right,right.left);
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> que = new LinkedList<>();
        List<List<Integer>> list = new ArrayList<>();

        if (root == null) return list;

        que.add(root);
        int curCount = 1;
        int childCount = 0;
        while (!que.isEmpty()) {
            List<Integer> valList = new ArrayList<>();
            while (curCount > 0) {
                TreeNode poll = que.poll();
                valList.add(poll.val);
                if (poll.left != null) {
                    que.add(poll.left);
                    childCount++;
                }
                if (poll.right != null) {
                    que.add(poll.right);
                    childCount++;
                }
                curCount--;
            }
            list.add(valList);
            curCount = childCount;
            childCount = 0;
        }
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(46);
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        Semaphore semaphore = new Semaphore(3);
        ReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();
        lock.writeLock();
        ReentrantLock reentrantLock = new ReentrantLock();



        return list;
    }

}
