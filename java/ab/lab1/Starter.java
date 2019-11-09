package ab.lab1;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Starter {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();

        System.out.println("=======================");
        String[] catKeys = {"cat"};
        File catFolder = new File("resources/lab1/cat");
        Subscriber catSubscriber = new Subscriber(connection,"CatSubscriber", catKeys, catFolder);
        catSubscriber.subscribe();

        String[] dogKeys = {"dog"};
        File dogFolder = new File("resources/lab1/dog");
        Subscriber dogSubscriber = new Subscriber(connection,"DogSubscriber", dogKeys, dogFolder);
        dogSubscriber.subscribe();

        String[] funKeys = {"panda", "raccoon"};
        File funFolder = new File("resources/lab1/fun");
        Subscriber funSubscriber = new Subscriber(connection,"FunSubscriber", funKeys, funFolder);
        funSubscriber.subscribe();

        Publisher publisher = new Publisher(connection);
        File source = new File("resources/lab1/source");
        File[] files = source.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().contains("cat")) {
                    publisher.send(file, "cat");
                } else if (file.getName().contains("dog")) {
                    publisher.send(file, "dog");
                } else if (file.getName().contains("panda")) {
                    publisher.send(file, "panda");
                } else if (file.getName().contains("raccoon")) {
                    publisher.send(file, "raccoon");
                }
            }
        }
        System.out.println("Hi");
        connection.close();
    }
}
