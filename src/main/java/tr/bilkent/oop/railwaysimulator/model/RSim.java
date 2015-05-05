package tr.bilkent.oop.railwaysimulator.model;

import tr.bilkent.oop.railwaysimulator.model.identity.IdentityComparator;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystem;
import tr.bilkent.oop.railwaysimulator.model.user.User;
import tr.bilkent.oop.railwaysimulator.model.identity.Identity;

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
 * TODO change access..
 */
public class RSim implements Serializable {
    private static transient RSim instance;
    List<User> userDatabase;
    Set<Identity> identityDatabase;

    RailwaySystem currentSystem;
    User currentUser = null;


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

    public Set<Identity> getIdentityDatabase() {
        return identityDatabase;
    }

    public List<User> getUserDatabase() {
        return userDatabase;
    }
}
