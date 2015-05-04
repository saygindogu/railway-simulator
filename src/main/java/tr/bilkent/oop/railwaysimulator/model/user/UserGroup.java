package tr.bilkent.oop.railwaysimulator.model.user;

import tr.bilkent.oop.railwaysimulator.model.identity.Identity;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by saygin on 5/4/2015.
 */
public class UserGroup {
    private String name;
    private List<Identity> users;
    private Identity identity; //Exactly same properties with user identity

    public UserGroup(String name) {
        this.name = name;
        createNewIdentity();
    }

    public UserGroup(String name, List<Identity> users) {
        this.name = name;
        this.users = users;
        createNewIdentity();
    }

    private void createNewIdentity(){
        //TODO handle identity stuff, similar to Station identity stuff, use hash of name
    }

    protected void addUser( User user){
        //TODO add user, getting his identity..
    }

    protected String getName() {
        return name;
    }

    protected void changeName( String newName) {
        //TODO check if the new name does not exits in the database.
        this.name = newName;
    }

    protected Identity getIdentity() {
        return identity;
    }
}
