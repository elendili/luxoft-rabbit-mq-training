package labtask.lab4.jms;

import javax.jms.*;

public class JMSSender {
    private ConnectionFactory connectionFactory;
    private Queue queue;

    public JMSSender(ConnectionFactory connectionFactory, Queue queue) {
        this.connectionFactory = connectionFactory;
        this.queue = queue;
    }

    public void send() throws JMSException {
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(queue);
        String text = "Hello world!";
        BytesMessage bytesMessage = session.createBytesMessage();
        bytesMessage.writeBytes(text.getBytes());
        producer.send(bytesMessage);
        System.out.println("JMS client sent message: " + text);
        connection.close();
    }
}
