package examples.ex3;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Subscriber {
    private static final String EXCHANGE_NAME = "test.fanout";
    private Connection connection;
    private Channel channel;
    private String id;

    public Subscriber(String id) throws IOException, TimeoutException {
        this.id = id;
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
    }

    public void subscribe() throws IOException, TimeoutException {
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(consumerTag + ": Message '" + message + "' is received.");
            }
        };
        channel.basicConsume(queueName, true, id, consumer);
    }

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
