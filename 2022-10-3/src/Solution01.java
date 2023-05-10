import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程交替输出 a b c
 */
public class Solution01 {

    static final ReentrantLock lock = new ReentrantLock();
    static int flag = 1;
    static final Condition c1 = lock.newCondition();
    static final Condition c2 = lock.newCondition();
    static final Condition c3 = lock.newCondition();

    public static void main(String[] args) {
        Solution01 solution01 = new Solution01();
        Thread t1 = new Thread(() -> {
            solution01.print('a', 1, 2 ,3);
        },"t1");

        Thread t2 = new Thread(() -> {
            solution01.print('b', 2, 3,3);
        },"t2");

        Thread t3 = new Thread(() -> {
            solution01.print('c', 3, 1,3);
        },"t1");

        t1.start();
        t2.start();
        t3.start();

    }


    public void print(char ch, int wait, int notify, int loopNumber) {
        while (loopNumber > 0) {
            synchronized (this) {
                if (flag == wait) {
                    System.out.println(ch);
                    flag = notify;
                    loopNumber--;
                }
            }
        }
    }


}
