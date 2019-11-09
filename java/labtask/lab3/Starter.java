package labtask.lab3;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Starter {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Server server = new Server();
        server.receive();
        Client client = new Client();
        for (int i = 1; i < 10; i++) {
            System.out.println("Factorial of " + i + " = " + client.call(i));
        }
        client.close();
        server.close();
    }
}
