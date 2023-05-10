import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现T1、T2、T3线程顺序输出A、B、C
 *
 * 使用 Lock 类 对代码加锁,原理和 wait/notify 相同
 *
 */
public class Solution04 {

    private int num;
    private static final Lock lock= new ReentrantLock();

    private void printABC(char name, int targetNum){
            lock.lock();
            if (num % 3 == targetNum) {
                System.out.print(name);
                num++;
            }
            lock.unlock();
    }

}
