/**
 *  下面代码解释了 wait 和 notify 的关系：
 *      其实 wait 和 notify 之间是通过 锁 进行通信的
 *   这个锁可以是任意的对象，但是对一个 线程 使用  wait 和
 *   notify  的锁必须是同一个锁，无法使用 B锁 的notify 去
 *   唤醒 A锁的 wait
 *      同时，锁是通过 synchronized 来捕获的，所以 wait
 *    和 notify 必须使用在 synchronized内，同时我们还需要
 *    给synchronized一把锁
 */


public class Solution01 {

    public static void main(String[] args) {
        Solution01 solution01 = new Solution01();

        Thread thread = new Thread() {
            @Override
            public void run() {
                Solution01 s = new Solution01();
                try {
                    Thread.sleep(3 * 1000);
                    s.setMyNotify(Solution01.class);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        solution01.setMyWait(Solution01.class);
    }


    public void setMyWait(Class<Solution01> lock) {
      synchronized (lock) {
          try {
              System.out.println("线程进入等待");
              lock.wait();
              System.out.println("结束了！！！！");
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
    }

    public void setMyNotify(Class<Solution01> lock) {
        synchronized (lock) {
            lock.notify();
            System.out.println("线程被唤醒了");
        }
    }

}
