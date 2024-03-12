package Objects;

public class User extends Person{
    private String email;
    private String password;

    public User() {
        super();
        this.email = faker.internet().safeEmailAddress();
        this.password = "Test!234";
    }
    public User(String name, String email, String password, String email1, String password1) {
        super(name, email, password);
        this.email = email1;
        this.password = password1;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
