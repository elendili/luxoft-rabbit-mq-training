package labtask.lab2;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Starter {
    public static void main(String[] args) throws IOException, TimeoutException {
        String[] nameKeys = {"*.John.*"};
        Subscriber nameSubscriber = new Subscriber("NameSubscriber", nameKeys);
        nameSubscriber.subscribe();

        String[] countryKeys = {"Ukraine.#", "Spain.#"};
        Subscriber countrySubscriber = new Subscriber("CountrySubscriber", countryKeys);
        countrySubscriber.subscribe();

        String[] yearAndCountryKeys = {"USA.*.1990", "USA.*.1991"};
        Subscriber yearAndCountrySubscriber = new Subscriber("YearAndCountrySubscriber", yearAndCountryKeys);
        yearAndCountrySubscriber.subscribe();

        String[] allKeys = {"#"};
        Subscriber allSubscriber = new Subscriber("AllSubscriber", allKeys);
        allSubscriber.subscribe();

        Publisher publisher = new Publisher();
        publisher.send(new Person("USA", "John", 1990), "USA.John.1990");
        publisher.send(new Person("USA", "Kate", 1991), "USA.Kate.1991");
        publisher.send(new Person("Ukraine", "Ann", 1990), "Ukraine.Ann.1990");
        publisher.send(new Person("Spain", "Tom", 1989), "Spain.Tom.1989");
        publisher.send(new Person("Ukraine", "John", 1988), "Ukraine.John.1988");
        publisher.close();
    }
}
