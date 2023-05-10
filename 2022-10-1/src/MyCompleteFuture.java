import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyCompleteFuture {


    public static void test01() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(()->{
            return 1;
        });
        completableFuture.whenComplete((e,w)->{

        });
        completableFuture.exceptionally(e -> {
         return null;
        });

        Object o = completableFuture.get();
        completableFuture.get(2, TimeUnit.SECONDS);
        Object now = completableFuture.getNow(2);
    }

}
