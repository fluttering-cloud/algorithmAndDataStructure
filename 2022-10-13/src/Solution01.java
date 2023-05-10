import java.util.*;

public class Solution01 {


    public static void main(String[] args) {
    }

    int[] res;
    int index;
    StringBuilder builder;
    public int[] printNumbers(int n) {
        int pow = (int) Math.pow(10,n) - 1;
        res = new int[pow];
        index = 0;
        builder = new StringBuilder();
        for (int i = 1; i <= n; i++){
            dfs(i);
        }
        return res;
    }

    public void dfs(int n){
        if (n  == 0) {
            String s = builder.toString();
              Integer integer = Integer.valueOf(s);
              System.out.println(integer);
              res[index++] = integer;
            return;
        };
        for (int i = 0; i <= 9; i++) {
            if (builder.length() == 0 && i == 0)  continue;
            builder.append(i);
            dfs(n - 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }


    public boolean verifyPostorder1(int[] postorder) {
       return recur(postorder,0,postorder.length - 1);
    }

    public boolean recur(int[] postorder, int i , int root) {
        if (i >= root) return true;
        int l = i;
        while (postorder[l] < postorder[root]) l++;
        int r = l;
        while (postorder[r] > postorder[root]) r++;
        return r==root && recur(postorder,i,l - 1) && recur(postorder,l, root - 1);
    }

    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0 || postorder == null) return true;
        Stack<Integer> stack = new Stack<>();
        stack.push(postorder[postorder.length - 1]);
        int reverse = postorder.length - 2;
        int root = Integer.MAX_VALUE;
        while (reverse >= 0) {
            if (postorder[reverse] < stack.peek()) {
                while (!stack.isEmpty()) {
                    if (stack.peek() > postorder[reverse] && stack.peek()  < root){
                        root = stack.pop();
                    } else {
                        break;
                    }
                }
            }
            stack.push(postorder[reverse]);
            reverse--;
        }

        while (!stack.isEmpty()) {
            if (stack.peek()  < root){
                root = stack.pop();
            } else {
                break;
            }
        }
        if (stack.isEmpty()) return true;
        return false;
    }


    /**
     * 给一个数字数字，按照数组的顺序得到一个整数mun，然后用这个数组内的这些数字拼装出一个大于这个整数的数字target，且target要是比num大的这些数
     * 的最小的那个，要是有target，这按target内数字的顺序返回数组,否则将数组升序排列返回
     */


}
