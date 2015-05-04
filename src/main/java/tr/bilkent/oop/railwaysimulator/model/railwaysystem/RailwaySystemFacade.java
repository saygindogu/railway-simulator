package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import tr.bilkent.oop.railwaysimulator.model.user.User;

import javax.jws.soap.SOAPBinding;

/**
 * Created by saygin on 5/1/2015.
 */
public class RailwaySystemFacade {
    private static RailwaySystemFacade instance;
    private RailwaySystem currentSystem;
    private User currentUser;

    private RailwaySystemFacade(){
        currentSystem = null;
        currentUser = null;
    }

    public void initilizeNewSystemFor( User user){
        currentUser = user;
        currentSystem = new RailwaySystem( user);
    }

    public void setPermissionsOfSystem(){
        //TODO check if we can do this (  we hold modify right and stuff.. )
    }

    public RailwaySystemFacade getInstance(){
        if( instance == null)
            instance = new RailwaySystemFacade();
        return instance;
    }


    /*
     Station Interface...
     */
    public void setCurrentRailwaySystem( RailwaySystem system){
        //TODO maybe save state here...
        this.currentSystem = system;
    }


    public void addStationTo( Track track, Position position, Station station){
        station.addThisTo( track, position);
    }

    public void removeStationFrom( Track track, Station station){
       station.removeThisFrom( track);
    }

    public Position getStationPositionOnTrack( Track track, Station station){
        return station.getPositionOn( track);
    }

    protected int getStationsMaxNumWaggonsOnTrack( Track track, Station station){
        return station.getMaxNumWaggonsOn( track);
    }


    /*
        //TODO RailwaySystem interface
     */

     /*
        //TODO Train interface
     */

       /*
        //TODO WAggon interface
     */

     /*
        //TODO Dispacher interface
     */

}
