package examples.ex4;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * This example demonstrates subscribing only to a subset of the messages.
 * As a result, FirstSubscriber receives only messages with routing key = "key1",
 * and SecondSubscriber receives all sent messages.
 */
public class RoutingExample {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        String[] keysForFirstSubscriber = {"key1"};
        DirectSubscriber firstSubscriber = new DirectSubscriber("FirstSubscriber", keysForFirstSubscriber);
        String[] keysForSecondSubscriber = {"key1", "key2"};
        DirectSubscriber secondSubscriber = new DirectSubscriber("SecondSubscriber", keysForSecondSubscriber);
        firstSubscriber.subscribe();
        secondSubscriber.subscribe();

        DirectPublisher publisher = new DirectPublisher();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String key = random.nextBoolean() ? "key1" : "key2";
            publisher.send("Message_" + i + "_" + key, key);
        }

        Thread.sleep(10_000);
        publisher.close();
        firstSubscriber.close();
        secondSubscriber.close();
    }
}
