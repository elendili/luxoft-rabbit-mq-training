package ab.lab2;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Starter {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection c = connectionFactory.newConnection();

        System.out.println("hi");
        String[] nameKeys = {"*.John.*"};
        Subscriber nameSubscriber = new Subscriber(c,"NameSubscriber", nameKeys);
        nameSubscriber.subscribe();

        String[] countryKeys = {"Ukraine.#", "Spain.#"};
        Subscriber countrySubscriber = new Subscriber(c,"CountrySubscriber", countryKeys);
        countrySubscriber.subscribe();

        String[] yearAndCountryKeys = {"USA.*.1990", "USA.*.1991"};
        Subscriber yearAndCountrySubscriber = new Subscriber(c,"YearAndCountrySubscriber", yearAndCountryKeys);
        yearAndCountrySubscriber.subscribe();

        String[] allKeys = {"#"};
        Subscriber allSubscriber = new Subscriber(c,"AllSubscriber", allKeys);
        allSubscriber.subscribe();

        Publisher publisher = new Publisher(c);
        publisher.send(new Person("USA", "John", 1990), "USA.John.1990");
        publisher.send(new Person("USA", "Kate", 1991), "USA.Kate.1991");
        publisher.send(new Person("Ukraine", "Ann", 1990), "Ukraine.Ann.1990");
        publisher.send(new Person("Spain", "Tom", 1989), "Spain.Tom.1989");
        publisher.send(new Person("Ukraine", "John", 1988), "Ukraine.John.1988");

        c.close();
        System.out.println("z");
    }
}
