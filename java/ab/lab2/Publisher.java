package ab.lab2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeoutException;

public class Publisher {
    public static final String EXCHANGE_NAME =  "lab.person";
    private Channel channel;

    /**
     * Constructs new Publisher.
     * Creates new connection to the server and new channel.
     * Implement it.
     * Use "localhost" as a host for ConnectionFactory.
     */
    public Publisher(Connection c) throws IOException, TimeoutException {
        channel = c.createChannel();
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
    public void send(Person person, String routingKey) throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(person);
            objectOutputStream.flush();
            byte[] bytes = outputStream.toByteArray();
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, bytes);
        }
        System.out.println("Person '" + person + "' is sent.");
    }

    /**
     * Closes the channel and the connection.
     * Implement it.
     */
    public void close() throws IOException, TimeoutException {
        // no need
    }
}
