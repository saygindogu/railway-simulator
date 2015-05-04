package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import tr.bilkent.oop.railwaysimulator.model.user.User;
import tr.bilkent.oop.railwaysimulator.model.user.UserGroup;

import java.util.List;


/**
 * Created by saygin on 4/19/2015.
 */
public class RailwaySystem {
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

    public List<Track> getTracks() {
        return tracks;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }

    public void setPermissions(RailwayPermissions permissions) {
        this.permissions = permissions;
    }

    public RailwayPermissions getPermissions() {
        return permissions;
    }


}
