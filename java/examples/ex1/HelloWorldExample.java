package examples.ex1;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * This example demonstrates a producer sending a single message,
 * and a consumer that receives messages and prints them.
 */
public class HelloWorldExample {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Receiver receiver = new Receiver();
        receiver.receive();

        Sender sender = new Sender();
        sender.send();

        Thread.sleep(2000);
        sender.close();
        receiver.close();
    }
}
