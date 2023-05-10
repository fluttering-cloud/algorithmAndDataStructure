package code;

import java.util.Stack;

/**
定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
调用 min、push 及 pop 的时间复杂度都是 O(1)。
*/

public class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        int min = minStack.peek();
        if (x <= min) minStack.push(x);
    }

    public void pop() {
        int pop = stack.pop();
        if (minStack.peek() == pop) minStack.pop();
        System.out.println(pop);
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
