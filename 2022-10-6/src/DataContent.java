import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class DataContent {
   private int data;
   private static final StampedLock lock = new StampedLock();

   public DataContent(int data) {
       this.data = data;
   }

    public void readData() {
        // 乐观读
//        long stamp = lock.tryOptimisticRead();
//        if (lock.validate(stamp)) {
//            try {
//                System.out.println("读睡眠");
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("读执行");
//            System.out.println(data);
//            return;
//        }

        Long stamp = lock.readLock();;
        // 乐观读失败后进行锁升级
        try {
            System.out.println("读睡眠");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(data);
        } finally {
            lock.unlock(stamp);
        }
    }

    public void setData(int data) {
        try {
            System.out.println("写睡眠");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long stamp = lock.writeLock();
        try {
            System.out.println("写执行");
            this.data = data;
        } finally {
            lock.unlock(stamp);
        }

    }


    public static void main(String[] args) {
        DataContent dataContent = new DataContent(1);

        Thread t1 = new Thread(() -> {
           dataContent.readData();
        });

        Thread t2 = new Thread(() -> {
            dataContent.setData(2);
        });

        t1.start();
        t2.start();
   }

}
