package labtask.lab1;

import com.rabbitmq.client.Channel;

import java.io.File;

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
    public Subscriber(String id, String[] routingKeys, File destination) {
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
    public void subscribe() {
    }

}
