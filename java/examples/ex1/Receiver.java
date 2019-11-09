package examples.ex1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {
    private static final String QUEUE_NAME = "test.helloworld";
    private Connection connection;
    private Channel channel;

    public Receiver() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
    }

    public void receive() throws IOException, TimeoutException {
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Message '" + message + "' is received.");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
