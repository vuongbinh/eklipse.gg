package Objects;

import com.github.javafaker.Faker;

public class Person {
    public Faker faker = new Faker();
    private final String name;
    public Person() {
        this.name = faker.name().fullName();
    }
    public Person(String name, String email, String password) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
