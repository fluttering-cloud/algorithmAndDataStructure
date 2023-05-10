import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class MyCompleteFuture {

    public static void main(String[] args) {
        test02();
    }

    // 创建CompletableFuture 的四种方法
    public static void test() {
        CompletableFuture completedFuture = CompletableFuture.runAsync(new MyRunnable());
        CompletableFuture completableFuture = CompletableFuture.runAsync(new MyRunnable(), Executors.newSingleThreadExecutor());

        CompletableFuture completableFuture1 = CompletableFuture.supplyAsync(new MySupplier());
        CompletableFuture completableFuture2 = CompletableFuture.supplyAsync(new MySupplier(), Executors.newSingleThreadExecutor());
    }

    /**
     * 异步线程执行案例
     *  1. 先执行异步线程
     *  2. 异步线程执行完毕后会将异步线程的结果以回调的形式交给 whenComplete 执行
     *  3. 若异步线程在执行时出现异常时，则会将其抛出，然后交由 exceptionally 进行处理
     *
     * CompletableFuture 与 FuTureTask 相比的优点
     *     1. FuTureTask 采用 isDone() + get() 的方式执行，会阻塞主线程
     *     2. CompletableFuture 采用 通知 的形式来执行异步线程，异步线程与主线程的执行是完全分离的
     *
     * 想想通知的底层是怎么实现的呢？
     */
    public static void test02() {
        ExecutorService threadPool =  Executors.newFixedThreadPool(3);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                System.out.println("异步线程阻塞");
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "异步线程已经完成";
        },threadPool);

        completableFuture.whenComplete((v,e) -> {
            if (e==null)
                System.out.println(v);
        }).exceptionally(throwable -> {
            System.out.println("异步线程出错了");
            return null;
        });

        threadPool.shutdown();

        System.out.println("主线程执行完毕了");
    }

}

class MySupplier implements Supplier{

    @Override
    public Object get() {
        return null;
    }
}