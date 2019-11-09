package labtask.lab4.rabbitmq;

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
    public void receive() {
    }
}
