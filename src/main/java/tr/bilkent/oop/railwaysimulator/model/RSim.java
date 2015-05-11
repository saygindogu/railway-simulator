package tr.bilkent.oop.railwaysimulator.model;

import tr.bilkent.oop.railwaysimulator.model.identity.Identity;
import tr.bilkent.oop.railwaysimulator.model.identity.IdentityComparator;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystem;
import tr.bilkent.oop.railwaysimulator.model.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by saygin on 4/19/2015.
 *
 * Program's main entity object. The universe is this object.
 *
 */
public class RSim implements Serializable {
    private static transient RSim instance;
    List<User> userDatabase;
    Set<Identity> identityDatabase;

    transient RailwaySystem currentSystem;
    transient User currentUser = null;


    private RSim(){
        userDatabase = new ArrayList<User>();
        identityDatabase = new TreeSet<Identity>( new IdentityComparator() );
    }
    
    public static RSim getInstance() {
        if( instance == null){
            instance = new RSim();
        }
        return instance;
    }

    protected static void setInstance( RSim newInstance){
        instance = newInstance;
    }

    public static void destroy() {
        instance = null;
    }

    protected static void setInstance( RSim newInstance){
        instance = newInstance;
    }

    public static void destroy() {
        instance = null;
    }

    public Set<Identity> getIdentityDatabase() {
        return identityDatabase;
    }

    public List<User> getUserDatabase() {
        return userDatabase;
    }

    public void addUser(User user) {
        userDatabase.add( user);
    }

    public void addUser(User user) {
        userDatabase.add( user);
        currentUser = user;
    }

    public RailwaySystem getCurrentSystem() {
        return currentSystem;
    }

    public void setCurrentSystem(RailwaySystem currentSystem) {
        this.currentSystem = currentSystem;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
