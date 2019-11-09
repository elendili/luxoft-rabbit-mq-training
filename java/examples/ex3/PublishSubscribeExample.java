package examples.ex3;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * This example demonstrates publish/subscribe pattern.
 * When we run two workers, all sent messages will be broadcast to both of them.
 */
public class PublishSubscribeExample {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Subscriber firstSubscriber = new Subscriber("FirstSubscriber");
        Subscriber secondSubscriber = new Subscriber("SecondSubscriber");
        firstSubscriber.subscribe();
        secondSubscriber.subscribe();

        Publisher publisher = new Publisher();
        for (int i = 0; i < 10; i++) {
            publisher.send("Message_" + i);
        }

        Thread.sleep(10_000);
        publisher.close();
        firstSubscriber.close();
        secondSubscriber.close();
    }
}
