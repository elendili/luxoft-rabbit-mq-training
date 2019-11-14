package ab.lab3;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class InvestigateReordering {
     AtomicInteger bqcounter = new AtomicInteger();
     AtomicInteger counter = new AtomicInteger();
     Executor executor = Executors.newSingleThreadExecutor();

    public  void m() throws InterruptedException {
        final BQ bq1 = new BQ("bq:" + bqcounter.incrementAndGet());
        Runnable callback = () -> {
            bq1.abq.offer(counter.incrementAndGet());
            System.out.println(bq1);
        };
        executor.execute(callback);
        System.out.println(bq1);
    }

    public static void main(String... as) throws InterruptedException {
        InvestigateReordering ir = new InvestigateReordering();
        ir.m();
        ir.m();

    }
}
