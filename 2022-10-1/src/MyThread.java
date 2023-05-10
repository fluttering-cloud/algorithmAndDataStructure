public class MyThread {


    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            try {
                Thread.sleep(500);
                System.out.println("t1执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()->{
            t1.interrupt();
            System.out.println("t2执行完毕");
        });
        t1.start();
        t2.start();
        System.out.println("主线程执行完毕！！");
    }
}
