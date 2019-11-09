package examples.ex6;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RPCServer {
    private static final String QUEUE_NAME = "test.rpc";
    private Connection connection;
    private Channel channel;

    public RPCServer() throws IOException, TimeoutException {
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
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(properties.getCorrelationId())
                        .build();
                String message = new String(body, "UTF-8");
                System.out.println("Received message '" + message + "'");
                String response = "Response for message '" + message + "'";
                channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
