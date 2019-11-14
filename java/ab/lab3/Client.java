package ab.lab3;

import com.rabbitmq.client.*;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.BiFunction;

import static ab.lab3.Server.REQUEST_QUEUE_NAME;

public class Client {
    private Channel channel;

    /**
     * Constructs new Client.
     * Creates new connection to the server and new channel.
     * Implement it.
     * Use "localhost" as a host for ConnectionFactory.
     */
    public Client(Connection c) throws IOException {
        channel = c.createChannel();
    }

    /**
     * Sends request to server to evaluate factorial of the given number.
     * Blocks until the answer from the server is received.
     * This method:
     * 1. Declares non-durable, exclusive, autodelete callback queue with a generated name.
     * 2. Creates AMQP.BasicProperties object with replyTo property, which is set to the callback queue.
     * 3. Converts number to byte array and publishes it to request queue (REQUEST_QUEUE_NAME).
     * 4. Creates BlockingQueue to store response as we need to block client until the answer from the server is received.
     * 5. Creates new DefaultConsumer with overridden handleDelivery method,
     * which retrieves number from message body, which is the result of factorial calculations,
     * and writes it to BlockingQueue.
     * 6. Starts consuming messages from the callback queue. Blocks until the answer is received.
     * 7. Returns evaluated factorial value.
     * Implement it.
     */
//    final static BlockingQueue<Integer> response = new ArrayBlockingQueue<>(10);

    public Integer call(int number) throws IOException, InterruptedException {
        String qName = "q-";
//        String qName = "q"+number+"q";
        channel.queueDeclare(qName, false, false, false, null);
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().replyTo(qName).build();
        channel.basicPublish("", REQUEST_QUEUE_NAME, props, (number + "").getBytes());
        final BQ bq1 = new BQ("bq:"+qName+number);
        DefaultConsumer callback = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                bq1.abq.offer(Integer.parseInt(new String(body)));
                System.out.println(bq1);
            }
        };
        channel.basicConsume(qName, true, callback);
        System.out.println(bq1);
        Integer x = bq1.abq.take();
        return x;
    }
}
