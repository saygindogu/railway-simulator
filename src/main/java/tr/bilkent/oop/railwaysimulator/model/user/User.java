package tr.bilkent.oop.railwaysimulator.model.user;

/**
 * Created by saygin on 4/19/2015.
 */
public class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
