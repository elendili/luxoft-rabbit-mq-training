package labtask.lab3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Client {
    private static final String REQUEST_QUEUE_NAME = "lab.factorial";
    private Channel channel;
    private Connection connection;

    /**
     * Constructs new Client.
     * Creates new connection to the server and new channel.
     * Implement it.
     * Use "localhost" as a host for ConnectionFactory.
     */
    public Client() {
    }

    /**
     * Sends request to server to evaluate factorial of the given number.
     * Blocks until the answer from the server is received.
     * This method:
     * 1. Declares non-durable, exclusive, autodelete callback queue with a generated name.
     * 2. Creates AMQP.BasicProperties object with replyTo property, which is set to the callback queue.
     * 3. Converts number to byte array and publishes it to request queue (REQUEST_QUEUE_NAME).
     * 4. Creates BlockingQueue to store response as we need to block client until the answer from the server is received.
     * 5. Creates new DefaultConsumer with overridden handleDelivery method,
     * which retrieves number from message body, which is the result of factorial calculations,
     * and writes it to BlockingQueue.
     * 6. Starts consuming messages from the callback queue. Blocks until the answer is received.
     * 7. Returns evaluated factorial value.
     * Implement it.
     *
     * @param number number to evaluate factorial of
     * @return evaluated factorial value
     */
    public Integer call(int number) {
        return 0;
    }

    /**
     * Closes the channel and the connection.
     * Implement it.
     */
    public void close() {
    }
}
