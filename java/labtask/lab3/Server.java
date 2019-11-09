package labtask.lab3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Server {
    private static final String REQUEST_QUEUE_NAME = "lab.factorial";
    private Channel channel;
    private Connection connection;

    /**
     * Constructs new Server.
     * Creates new connection to the server and new channel.
     * Implement it.
     * Use "localhost" as a host for ConnectionFactory.
     */
    public Server() {
    }

    /**
     * Subscribes to receive messages on the queue for requests (REQUEST_QUEUE_NAME).
     * When a request appears, it evaluates factorial and sends a message with the result back to the client,
     * using the queue from the replyTo field.
     * This method:
     * 1. Declares non-durable, non-exclusive, non-autodelete queue for requests (REQUEST_QUEUE_NAME).
     * 2. Creates new DefaultConsumer with overridden handleDelivery method,
     * which retrieves number from message body and evaluates factorial of it;
     * after that the message with result is published to the queue from the replyTo field.
     * 3. Starts consuming messages from the declared queue.
     * Implement it.
     */
    public void receive() {
    }

    /**
     * Closes the channel and the connection.
     * Implement it.
     */
    public void close() {
    }


    /**
     * Returns factorial of the given number
     * Implement it.
     *
     * @param number number to evaluate factorial of
     * @return factorial of the given number
     */
    private int factorial(int number) {
        return 0;
    }
}
