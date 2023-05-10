import java.util.concurrent.*;


/**
 * FutureTask 支持两种构造方法，分别由于执行  Callable、Runnable接口
 * FutureTask 的优点是可以结合线程池进行异步计算，提高线程的执行效率
 *
 * FutureTask 的缺点：
 *         1.get()会造成阻塞
 *         2.isDone()会造成大量的空轮询，消耗cup资源
 */
public class MyFuture {



    public void test01() throws ExecutionException, InterruptedException {

        System.out.println("主线程启动");

        //FutureTask future = new FutureTask(new MyCallable());
        String s = "123";
        FutureTask future = new FutureTask( new MyRunnable(),null);
        Thread thread = new Thread(future,"MyCallnable");
        thread.start();
        System.out.println(future.get());
    }

    public void test02() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        FutureTask<String> stringFutureTask = new FutureTask<>(new MyCallable());

        Thread thread = new Thread(stringFutureTask);

        thread.start();

        while (!stringFutureTask.isDone()) {

        };
        stringFutureTask.get();
    }

}


class MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {

        System.out.println("进入Callable");

        return "hello Callable";
    }
}


class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("进入Runnable！！");
    }
}