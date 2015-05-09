package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import tr.bilkent.oop.railwaysimulator.model.Observable;
import tr.bilkent.oop.railwaysimulator.model.user.User;
import tr.bilkent.oop.railwaysimulator.model.user.UserGroup;
import tr.bilkent.oop.railwaysimulator.view.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by saygin on 4/19/2015.
 */
public class RailwaySystem implements Serializable, Observable {
    private User owner;
    private UserGroup group;
    private RailwayPermissions permissions;
    private List<Track> tracks;
    private transient List<Observer> observers;

    public RailwaySystem( User owner, UserGroup group, RailwayPermissions permissions ){
        this.owner = owner;
        this.group = group;
        this.permissions = permissions;
        observers = new ArrayList<Observer>();
    }

    public RailwaySystem( User owner, RailwayPermissions permissions){
        this.owner = owner;
        this.group = null;
        this.permissions = permissions;
        observers = new ArrayList<Observer>();

    }

    public RailwaySystem( User owner){
        this.owner = owner;
        this.group = null;
        this.permissions = new RailwayPermissions(); //set permissions to default
        tracks = new ArrayList<Track>(1);
        observers = new ArrayList<Observer>();

    }

    protected UserGroup getGroup() {
        return group;
    }

    protected User getOwner() {
        return owner;
    }

    protected List<Track> getTracks() {
        return tracks;
    }

    protected void setGroup(UserGroup group) {
        this.group = group;
    }

    protected void setPermissions(RailwayPermissions permissions) {
        this.permissions = permissions;
    }

    protected RailwayPermissions getPermissions() {
        return permissions;
    }


    public boolean contains(Station station) {
        for (Track track : tracks) {
            for (Station nativeStation : track.getStations()) {
                if( station.equals( nativeStation) )
                    return true;
            }
        }
        return false;

    }

    @Override
    public String toString() {
        String s = "RailwaySystem{" +
                "owner=" + owner.toString() +
                ", permissions=" + permissions
                + "\nTracks=\n---\n";
        for (Track track : tracks) {
            s += track.toString() + "\n-----\n";
        }
        return s + "}";

    }

    public void notifyChanges() {
        for (Observer observer : observers) {
            observer.update( this);
        }

    }

    public void subscribe(Observer observer) {
        observers.add( observer);

    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }
}
