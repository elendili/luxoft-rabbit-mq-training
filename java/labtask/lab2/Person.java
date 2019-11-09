package labtask.lab2;

import java.io.Serializable;

public class Person implements Serializable {
    private String nativeCountry;
    private String name;
    private int yearOfBirth;

    public Person(String nativeCountry, String name, int yearOfBirth) {
        this.nativeCountry = nativeCountry;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nativeCountry='" + nativeCountry + '\'' +
                ", name='" + name + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
