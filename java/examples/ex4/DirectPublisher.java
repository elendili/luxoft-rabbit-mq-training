package examples.ex4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectPublisher {
    private static final String EXCHANGE_NAME = "test.direct";
    private Connection connection;
    private Channel channel;

    public DirectPublisher() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
    }

    public void send(String message, String routingKey) throws IOException, TimeoutException {
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
        System.out.println("Message '" + message + "' is sent.");
    }

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
