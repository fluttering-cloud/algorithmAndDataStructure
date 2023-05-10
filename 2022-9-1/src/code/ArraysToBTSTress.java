package code;

/**
 * 根据一个搜索二叉树的后序遍历的数组，还原这颗二叉树，并返回头节点
 * @return
 */
public class ArraysToBTSTress {
    public Node builtBTSTree(int[] array,int L,int R) {

        if (array == null) return null;

        if (L > R) return null;

        Node head = new Node();
        head.setVal(array[R]);

        if (L == R) return  head;

        int M = L - 1;

        for (int i = L; i < R; i++) {
            if (array[i] < array[R])
                M = i;
        }

        Node left = builtBTSTree(array,L,M);
        Node right = builtBTSTree(array,M + 1, R-1);

        head.setLeftNode(left);
        head.setRightNode(right);

        return  head;
    }

}
