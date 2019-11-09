package ab.lab4.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQReceiver {

    /**
     * Subscribes to receive messages sent by JMSSender.
     * This method:
     * 1. Creates new connection to the server and new channel.
     * (Using "localhost" as a host for ConnectionFactory.)
     * 2. Declares queue to receive messages from JMSSender.
     * 3. Creates new DefaultConsumer with overridden handleDelivery method,
     * which converts message body to text and prints it.
     * 4. Starts consuming messages from the declared queue.
     * Implement it.
     */
    public void receive() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("Lab4QueueRabbit", true, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("RabbitMQ client received message with text = " + message);
            }
        };
        channel.basicConsume("Lab4QueueRabbit", true, consumer);
    }
}
