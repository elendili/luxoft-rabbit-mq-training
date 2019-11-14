package ab.lab3;

import java.util.concurrent.ArrayBlockingQueue;

public class BQ {
    BQ(String name) {
        this.name = name;
    }

    final String name;
    final ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<>(10);

    @Override
    public String toString() {
        return "BQ{" +
                "name='" + name + '\'' +
                ", abq=" + abq +
                '}';
    }
}