package labtask.lab2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Publisher {
    private static final String EXCHANGE_NAME = "lab.person";
    private Connection connection;
    private Channel channel;

    /**
     * Constructs new Publisher.
     * Creates new connection to the server and new channel.
     * Implement it.
     * Use "localhost" as a host for ConnectionFactory.
     */
    public Publisher() {
    }

    /**
     * Sends message with person. Each message has its routing key.
     * This method:
     * 1. Declares topic exchange.
     * 2. Serializes person to byte array.
     * 3. Publishes message with routing key to declared exchange.
     * Implement it.
     *
     * @param person     person which should be sent
     * @param routingKey the routing key of the message
     */
    public void send(Person person, String routingKey) {
    }

    /**
     * Closes the channel and the connection.
     * Implement it.
     */
    public void close() {
    }
}
