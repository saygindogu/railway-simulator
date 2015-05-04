package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import tr.bilkent.oop.railwaysimulator.model.user.User;
import tr.bilkent.oop.railwaysimulator.model.user.UserGroup;

import java.io.Serializable;
import java.util.List;


/**
 * Created by saygin on 4/19/2015.
 */
public class RailwaySystem implements Serializable {
    private User owner;
    private UserGroup group;
    private RailwayPermissions permissions;
    private List<Track> tracks;

    public RailwaySystem( User owner, UserGroup group, RailwayPermissions permissions ){
        this.owner = owner;
        this.group = group;
        this.permissions = permissions;
    }

    public RailwaySystem( User owner, RailwayPermissions permissions){
        this.owner = owner;
        this.group = null;
        this.permissions = permissions;
    }

    public RailwaySystem( User owner){
        this.owner = owner;
        this.group = null;
        this.permissions = new RailwayPermissions(); //set permissions to default
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
}
