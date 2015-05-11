package tr.bilkent.oop.railwaysimulator.model.user;

import tr.bilkent.oop.railwaysimulator.model.exception.InvalidUserNameException;
import tr.bilkent.oop.railwaysimulator.model.identity.Identity;
import tr.bilkent.oop.railwaysimulator.model.identity.UserIdentityFactory;

import java.io.Serializable;

/**
 * Created by saygin on 4/19/2015.
 */
public class User implements Serializable{
    private String name;
    private String password; //TODO use hash for this...
    private Identity identity;

    public User(String name, String password) {
        if( isValidUserName( name) ){
            this.name = name;
            this.password = password;
            identity = UserIdentityFactory.getInstance().newIdentity();
        }
        else throw new InvalidUserNameException();
    }



    public String getName() {
        return name;
    }

    public void changeName( String newName) {
        if( isValidUserName( newName) )
            this.name = newName;
        else throw  new InvalidUserNameException();
    }

    private boolean isValidUserName(String newName) {
        //TODO check if the new name does not exits in the database.
        //TODO use some constraints for username.
        if( newName != null)
            return true;
        return false;
    }

    public String getPassword() {
        return password;
    }

    public Identity getIdentity() {
        return identity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getIdentity().equals(user.getIdentity());

    }

    @Override
    public int hashCode() {
        return getIdentity().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                "id=" + identity.toString() +
                '}';
    }
}
