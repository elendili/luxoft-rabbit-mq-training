package examples.ex2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    private static final String QUEUE_NAME = "test.workqueue";
    private Connection connection;
    private Channel channel;

    public Sender() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
    }

    public void send(String message) throws IOException, TimeoutException {
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("Message '" + message + "' is sent.");
    }

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
