package tr.bilkent.oop.railwaysimulator.model.user;

import tr.bilkent.oop.railwaysimulator.model.identity.Identity;

/**
 * Created by saygin on 4/19/2015.
 */
public class User {
    private String name;
    private String password; //TODO use hash for this...
    private Identity identity;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        //TODO handle identity stuff, similar to Station identity stuff, use hash of username
    }

    public String getName() {
        return name;
    }

    public void changeName( String newName) {
        //TODO check if the new name does not exits in the database.
        this.name = newName;
    }

    public String getPassword() {
        return password;
    }

    public Identity getIdentity() {
        return identity;
    }
}
