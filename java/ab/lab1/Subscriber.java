package ab.lab1;

import com.rabbitmq.client.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.concurrent.TimeoutException;

public class Subscriber {
    private static final String EXCHANGE_NAME = "lab.image";
    private Channel channel;
    private String id;
    private String[] routingKeys;
    private File destination;

    /**
     * Constructs new Subscriber.
     * Sets subscriber's id, routing keys, and file destination (place where to save received file).
     * Creates new connection to the server and new channel.
     * Implement it.
     *
     * @param id          subscriber's identifier
     * @param routingKeys subscriber's routing(binding) keys
     * @param destination place where received file should be saved
     */
    public Subscriber(String id, String[] routingKeys, File destination) throws IOException, TimeoutException {
        this.id = id;
        this.routingKeys = routingKeys;
        this.destination = destination;
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        channel = connection.createChannel();
    }

    /**
     * Subscribes to receive messages if its routing keys exactly matches subscriber's ones.
     * This method:
     * 1. Declares direct exchange.
     * 2. Creates a non-durable, exclusive, autodelete queue with a generated name.
     * 3. Binds that queue to each routing key we are interested in.
     * 4. Creates new DefaultConsumer with overridden handleDelivery method, which saves received file to subscriber's destination.
     * It uses file name message header to create proper image file.
     * 5. Starts consuming messages from the declared queue.
     * Implement it.
     */
    public void subscribe() throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String qName = "hello-queue-"+id;
        channel.queueDeclare(qName, false, true, true, Collections.EMPTY_MAP);
        for (String routingKey : routingKeys) {
            channel.queueBind(qName, EXCHANGE_NAME, routingKey);
        }
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String fileName = properties.getHeaders().get("fileName").toString();
                File file = new File(destination, fileName);
                Path path = Paths.get(file.getPath());
                Files.write(path, body);
                System.out.println(consumerTag + ": File '" + fileName + "' is received and saved to " + destination);
            }
        };
        channel.basicConsume(qName, true, id, consumer);
    }

}
