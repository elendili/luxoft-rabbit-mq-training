package ab.lab3;

import com.rabbitmq.client.*;

import java.io.*;

public class Server {
    public static final String REQUEST_QUEUE_NAME = "lab.factorial";
    private Channel channel;

    /**
     * Constructs new Server.
     * Creates new connection to the server and new channel.
     * Implement it.
     * Use "localhost" as a host for ConnectionFactory.
     */
    public Server(Connection c) throws IOException {
        channel = c.createChannel();
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
    public void receive() throws IOException {
        channel.queueDeclare(REQUEST_QUEUE_NAME, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                int number = Integer.parseInt(new String(body));
                int result = factorial(number);
                channel.basicPublish("", properties.getReplyTo(), null, ("" + result).getBytes());
            }
        };
        channel.basicConsume(REQUEST_QUEUE_NAME, true, consumer);
    }


    /**
     * Returns factorial of the given number
     * Implement it.
     *
     * @param number number to evaluate factorial of
     * @return factorial of the given number
     */
    private int factorial(int number) {
        if (number == 0) {
            return 1;
        }
        return number * factorial(number - 1);
    }
}
