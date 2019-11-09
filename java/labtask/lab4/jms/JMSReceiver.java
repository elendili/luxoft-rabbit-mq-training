package labtask.lab4.jms;

import javax.jms.*;
import java.io.UnsupportedEncodingException;

public class JMSReceiver implements MessageListener {
    private ConnectionFactory connectionFactory;
    private Queue queue;

    public JMSReceiver(ConnectionFactory connectionFactory, Queue queue) {
        this.connectionFactory = connectionFactory;
        this.queue = queue;
    }

    public void receive() throws JMSException {
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(this);
        connection.start();
    }

    @Override
    public void onMessage(Message message) {
        BytesMessage bytesMessage = (BytesMessage) message;
        try {
            byte[] bytes = new byte[100];
            bytesMessage.readBytes(bytes);
            System.out.println("JMS client received message with text = " + new String(bytes, "UTF-8"));
        } catch (JMSException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
