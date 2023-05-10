/**
 * 实现T1、T2、T3线程顺序输出A、B、C
 *
 *  使用 join
 *    C 线程调用 B 线程的 join
 *    B 线程调用 A 线程的join
 */

public class Solution03 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Print_ABC(null),"A");
        Thread t2 = new Thread(new Print_ABC(t1),"B");
        Thread t3 = new Thread(new Print_ABC(t2),"C");
        t1.start();
        t2.start();
        t3.start();
    }


}

class Print_ABC implements Runnable{

    private Thread beforeThread;

    public Print_ABC(Thread beforeThread) {
        this.beforeThread = beforeThread;
    }

    @Override
    public void run() {
        if (beforeThread != null) {
            try {
                beforeThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName());
    }
}