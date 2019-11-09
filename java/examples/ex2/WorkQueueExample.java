package examples.ex2;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * This example demonstrates distribution of time-consuming tasks
 * among two workers.
 * When we run two workers, all sent messages will be shared between them.
 */
public class WorkQueueExample {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Worker firstWorker = new Worker("FirstWorker");
        Worker secondWorker = new Worker("SecondWorker");
        firstWorker.receive();
        secondWorker.receive();

        Sender sender = new Sender();
        for (int i = 0; i < 10; i++) {
            sender.send("Message_" + i);
        }

        Thread.sleep(10_000);
        sender.close();
        firstWorker.close();
        secondWorker.close();
    }
}
