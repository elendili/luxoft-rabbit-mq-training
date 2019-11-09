package labtask.lab1;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.File;
import java.io.IOException;

public class Publisher {
    private static final String EXCHANGE_NAME = "lab.image";
    private Connection connection;
    private Channel channel;

    /**
     * Constructs new Publisher.
     * Creates new connection to the server and new channel.
     * Implement it.
     * Use "localhost" as a host for ConnectionFactory.
     */
    public Publisher() {
    }

    /**
     * Sends message with file content. Each message has its routing key.
     * The message goes to the queues whose binding key exactly matches the routing key of the message.
     * This method:
     * 1. Declares direct exchange.
     * 2. Reads bytes from the file using readBytes(File file) method.
     * 3. Creates AMQP.BasicProperties with message header containing file name using createProperties(File file) method.
     * 4. Publishes message with those properties to declared exchange.
     * Implement it.
     *
     * @param file       file which content should be sent
     * @param routingKey the routing key of the message
     */
    public void send(File file, String routingKey) throws IOException {
    }

    /**
     * Closes the channel and the connection.
     */
    public void close() {
    }

    /**
     * Reads all the bytes from a file.
     * Implement it.
     *
     * @param file file to read bytes from
     * @return a byte array containing the bytes read from the file
     */
    private byte[] readBytes(File file) {
        return null;
    }

    /**
     * Creates AMQP.BasicProperties object containing file name header:
     * - creates headers map;
     * - puts file name to that map;
     * - creates AMQP.BasicProperties object with headers.
     * Implement it.
     *
     * @param file file which name should be added to message headers
     * @return AMQP.BasicProperties containing file name header
     */
    private AMQP.BasicProperties createProperties(File file) {
        return null;
    }
}
