package examples.ex5;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicSubscriber {
    private static final String EXCHANGE_NAME = "test.topic";
    private Connection connection;
    private Channel channel;
    private String id;
    private String[] routingKeys;

    public TopicSubscriber(String id, String[] routingKeys) throws IOException, TimeoutException {
        this.id = id;
        this.routingKeys = routingKeys;
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
    }

    public void subscribe() throws IOException, TimeoutException {
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();
        for (String routingKey : routingKeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
        }
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
