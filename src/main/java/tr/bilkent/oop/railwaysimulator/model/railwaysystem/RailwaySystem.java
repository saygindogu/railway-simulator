package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import tr.bilkent.oop.railwaysimulator.model.user.User;

import java.util.List;


/**
 * Created by saygin on 4/19/2015.
 */
public class RailwaySystem {
    private User owner;
    private List<Track> tracks;

    public RailwaySystem(){

    }

    public User getOwner() {
        return owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
