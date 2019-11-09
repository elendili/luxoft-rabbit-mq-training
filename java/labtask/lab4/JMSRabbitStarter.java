package labtask.lab4;

import labtask.lab4.jms.JMSSender;
import labtask.lab4.rabbitmq.RabbitMQReceiver;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

public class JMSRabbitStarter {
    public static void main(String[] args) throws NamingException, JMSException, IOException, TimeoutException, InterruptedException {
        Properties environmentParameters = new Properties();
        environmentParameters.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        environmentParameters.put(Context.PROVIDER_URL, "file:resources/rabbitmq-bindings");
        InitialContext initialContext = new InitialContext(environmentParameters);
        ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
        Queue queue = (Queue) initialContext.lookup("Lab4Queue");

        RabbitMQReceiver receiver = new RabbitMQReceiver();
        receiver.receive();

        JMSSender sender = new JMSSender(connectionFactory, queue);
        sender.send();
    }
}
