package ab.lab4.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQSender {

    /**
     * Sends text message "Hello World!", so that JMSReceiver could receive it.
     * This method:
     * 1. Creates new connection to the server and new channel.
     * (Using "localhost" as a host for ConnectionFactory.)
     * 2. Declares queue to send messages to JMSReceiver.
     * 3. Publishes message to declared queue.
     * 4. Closes channel and connection.
     * Implement it.
     */
    public void send() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String message = "Hello World!";
        channel.queueDeclare("Lab4QueueRabbit", true, false, false, null);
        channel.basicPublish("", "Lab4QueueRabbit", null, message.getBytes());
        System.out.println("RabbitMQ client sent message: " + message);
        channel.close();
        connection.close();
    }
}
