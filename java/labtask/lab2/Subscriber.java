package labtask.lab2;

import com.rabbitmq.client.Channel;

public class Subscriber {
    private static final String EXCHANGE_NAME = "lab.person";
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
    public Subscriber(String id, String[] routingKeys) {
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
    public void subscribe() {
    }
}
