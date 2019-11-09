package examples.ex1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    private static final String QUEUE_NAME = "test.helloworld";
    private Connection connection;
    private Channel channel;

    public Sender() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
    }

    public void send() throws IOException, TimeoutException {
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        // The first parameter is the the name of the exchange.
        // The empty string denotes the default exchange:
        // messages are routed to the queue with the name specified by routingKey.
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("Message '" + message + "' is sent.");
    }

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
