package examples.ex5;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * This example demonstrates using of topic exchange.
 * A message with a routing key set to "big.red.happy.one" will be delivered only to FirstSubscriber.
 * Message "small.grey.good" will only go to the SecondSubscriber.
 * A message with a routing key set to "big.blue.happy" will be delivered to both subscribers.
 */
public class TopicExample {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        String[] keysForFirstSubscriber = {"big.#"};
        TopicSubscriber firstSubscriber = new TopicSubscriber("FirstSubscriber", keysForFirstSubscriber);
        String[] keysForSecondSubscriber = {"*.grey.*", "big.*.happy"};
        TopicSubscriber secondSubscriber = new TopicSubscriber("SecondSubscriber", keysForSecondSubscriber);
        firstSubscriber.subscribe();
        secondSubscriber.subscribe();

        TopicPublisher publisher = new TopicPublisher();
        publisher.send("Message_1_big.red.happy.one", "big.red.happy.one");
        publisher.send("Message_1_small.grey.good", "small.grey.good");
        publisher.send("Message_1_big.blue.happy", "big.blue.happy");

        Thread.sleep(2000);
        publisher.close();
        firstSubscriber.close();
        secondSubscriber.close();
    }
}
