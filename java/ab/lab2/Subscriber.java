package ab.lab2;

import com.rabbitmq.client.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeoutException;

import static ab.lab2.Publisher.EXCHANGE_NAME;

public class Subscriber {
    private Channel channel;
    private String id;
    private String[] routingKeys;

    /**
     * Constructs new Subscriber.
     * Sets subscriber's id, routing keys.
     * Creates new connection to the server and new channel.
     * Implement it.
     *
     * @param id          subscriber's identifier
     * @param routingKeys subscriber's routing(binding) keys
     */
    public Subscriber(Connection c, String id, String[] routingKeys) throws IOException, TimeoutException {
        this.id = id;
        this.routingKeys = routingKeys;
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        channel = c.createChannel();
    }

    /**
     * Subscribes to receive messages if its routing keys matches subscriber's routing key.
     * This method:
     * 1. Declares topic exchange.
     * 2. Creates a non-durable, exclusive, autodelete queue with a generated name.
     * 3. Binds that queue to each routing key we are interested in.
     * 4. Creates new DefaultConsumer with overridden handleDelivery method,
     * which deserializes message body to Person object and prints it.
     * 5. Starts consuming messages from the declared queue.
     * Implement it.
     */
    public void subscribe() throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();
        for (String routingKey : routingKeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
        }
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(
                        new ByteArrayInputStream(body))) {
                    Person person = (Person) objectInputStream.readObject();
                    System.out.println(consumerTag + ": Person '" + person + "' is received.");
                    getChannel().basicAck(envelope.getDeliveryTag(),true);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        channel.basicConsume(queueName, false, id, consumer);
    }

}
