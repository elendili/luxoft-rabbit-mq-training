package examples.ex3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {
    private static final String EXCHANGE_NAME = "test.fanout";
    private Connection connection;
    private Channel channel;

    public Publisher() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
    }

    public void send(String message) throws IOException, TimeoutException {
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println("Message '" + message + "' is sent.");
    }

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
