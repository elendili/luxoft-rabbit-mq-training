package examples.ex2;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class Worker {
    private static final String QUEUE_NAME = "test.workqueue";
    private Connection connection;
    private Channel channel;
    private String id;

    public Worker(String id) throws IOException, TimeoutException {
        this.id = id;
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
                // simulate long-lasting job
                Random random = new Random();
                int duration = random.nextBoolean() ? 1000 : 2000;
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(consumerTag + ": Message '" + message + "' is received.");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, id, consumer);
    }

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
