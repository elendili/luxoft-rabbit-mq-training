package ab.lab3;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Starter {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection c = new ConnectionFactory().newConnection();
        Server server = new Server(c);
        server.receive();
        Client client = new Client(c);
        for (int i = 1; i < 10; i++) {
            System.out.println("Factorial of " + i + " = " + client.call(i));
        }
        c.close();
    }
}
