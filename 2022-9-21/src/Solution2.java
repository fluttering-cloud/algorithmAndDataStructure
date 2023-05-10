/**
 * 实现T1、T2、T3线程顺序输出A、B、C
 *
 *  使用 wait + notify
 */
public class Solution2 {

    private int num = 3;
    // 使用 static 的话让锁在class对象初始化时创建
    // 使用 final 的话保证变量只能被赋值一次，防止多个锁造成的多线程问题
    private static final Object lock = new Object();

    public void print_ABC(char name, int targetNum){
        synchronized (lock) {
            while (num % 3 != targetNum) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num++;
            System.out.print(name);
            lock.notifyAll();
        }
    }


    public static void main(String[] args) {
        Solution2 s = new Solution2();
        new Thread(() -> {
            s.print_ABC('A',0);
        }).start();
        new Thread(() -> {
            s.print_ABC('B',1);
        }).start();
        new Thread(() -> {
            s.print_ABC('C',2);
        }).start();
    }

}
