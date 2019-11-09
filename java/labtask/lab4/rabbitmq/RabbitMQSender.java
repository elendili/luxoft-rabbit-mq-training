package labtask.lab4.rabbitmq;

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
    public void send() {
    }
}
