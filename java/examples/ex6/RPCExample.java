package examples.ex6;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * This example demonstrates how to run a function on a remote computer and wait for the result.
 * A method named call sends an RPC request and blocks until the answer is received.
 */
public class RPCExample {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        RPCServer server = new RPCServer();
        server.receive();

        RPCClient client = new RPCClient();
        String result = client.call("Message");
        System.out.println("Result: " + result);

        client.close();
        server.close();
    }
}
