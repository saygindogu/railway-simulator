package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import tr.bilkent.oop.railwaysimulator.model.exception.NotOnCurrentSystemException;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Position;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Train;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.TrainBuilder;
import tr.bilkent.oop.railwaysimulator.model.simulation.DefaultTrainDispacher;
import tr.bilkent.oop.railwaysimulator.model.simulation.TrainDispacher;
import tr.bilkent.oop.railwaysimulator.model.user.User;
import tr.bilkent.oop.railwaysimulator.model.user.UserGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 5/1/2015.
 *
 * Gateway to the RailwaySystem subsystem. This subsystem provides only this interface to outside.
 * //TODO check completenes.
 * //TODO integration tests
 */
public class RailwaySystemFacade {
    private static RailwaySystemFacade instance;
    private RailwaySystem currentSystem;
    private User currentUser;

    private RailwaySystemFacade(){
        currentSystem = null;
        currentUser = null;
    }

    public static RailwaySystemFacade getInstance(){
        if( instance == null)
            instance = new RailwaySystemFacade();
        return instance;
    }

    /*
     Creational interface
     */
    public void initilizeNewSystemFor( User user){
        currentUser = user;
        currentSystem = new RailwaySystem( user);
    }

    public void initilizeSystemFor( User user, RailwaySystem system){
        //TODO check is user has read right
        currentUser = user;
        currentSystem = system;
    }

    public void setPermissionsOfSystem(){
        //TODO check first if we can do this (  we hold modify right and stuff.. )
    }

    /*
     Station Interface...
     */
    public void setCurrentRailwaySystem( RailwaySystem system){
        //TODO maybe save state here...

        //TODO read right check
        this.currentSystem = system;

    }


    public void addStationTo( Track track, Position position, Station station){
        //TODO modify right check
        if( isOnCurrentSystem( track)){
            station.addThisTo(track, position);
        }
        else throw new NotOnCurrentSystemException();

    }

    public void removeStationFrom( Track track, Station station){
        //TODO modify right check
        if( isOnCurrentSystem( track)){
            station.removeThisFrom(track);
        }
        else throw new NotOnCurrentSystemException();
    }

    public Position getStationPositionOnTrack( Track track, Station station){
        if( isOnCurrentSystem( track)){
            return station.getPositionOn(track);
        }
        else throw new NotOnCurrentSystemException();
    }

    public int getStationsMaxNumWaggonsOnTrack( Track track, Station station){
        if( isOnCurrentSystem( track)){
            return station.getMaxNumWaggonsOn(track);
        }
        else throw new NotOnCurrentSystemException();
    }


    /*
        //TODO check if RailwaySystem interface is complete
     */

    public void setUserGroup( UserGroup group ){
        currentSystem.setGroup( group );
    }

    public boolean isCurrentUserOwnerOfCurrentSystem(){
        return currentSystem.getOwner().equals( currentUser );
    }

    public void addNewTrackToCurrentSystem(){
        Track track = new Track( 50, 10);
        currentSystem.getTracks().add( track);
    }

    public void addNewTrackToCurrentSystem( Station station){
        Track track = new Track( 50, 10, station);
        currentSystem.getTracks().add( track);
    }

    public boolean isOnCurrentSystem( Track track){
        return currentSystem.getTracks().contains(track);
    }

    public boolean isOnCurrentSystem( Station station){
        return currentSystem.contains(station);
    }


    public void addNewTrainTo( Track track, Station station){
        //TODO check modify rights
        if( isOnCurrentSystem( station) ){
            Train train;
            TrainBuilder builder = new TrainBuilder();
            train = builder.build();
            station.addTrainOn(track, train);
        }
        else throw new NotOnCurrentSystemException();
    }

    public void addTrainTo( Track track, Station station, Train train){
        //TODO check modify rights
        if( isOnCurrentSystem( station) ){
            station.addTrainOn(track, train);
        }
        else throw new NotOnCurrentSystemException();
    }


    public Position getFirstStationPositionsBetween( Track track, Position currentPosition, Position newPosition) {
        //TODO read right check
        if( isOnCurrentSystem( track)){
            return track.getFirstStationPositionsBetween( currentPosition, newPosition );
        }
        else throw new NotOnCurrentSystemException();
    }

    public List<TrainDispacher> getDispachersOfTheSystem(){
        //TODO check simulate permissions
        ArrayList<TrainDispacher> list = new ArrayList<TrainDispacher>();
        for (Track track : currentSystem.getTracks()) {
            for (Station station : track.getStations()) {
                if( !station.getTrainQueueOn(track).isEmpty()){
                    list.add( new DefaultTrainDispacher( track, station.getTrainQueueOn(track), station.getTimeTableOn(track), station.getPositionOn(track) ) );
                }
            }
        }
        return list;

    }

    public RailwaySystem getCurrentSystem() {
        return currentSystem;
    }

    public Station getStationOnPosition(Track track, Position positionOnTrack) {
        if( isOnCurrentSystem( track) ){
            for (Station station : track.getStations()) {
                if( station.getPositionOn( track).equals( positionOnTrack) ){
                    return station;
                }
            }
            return null;
        }
        throw new NotOnCurrentSystemException();
    }

    public long getWaitingTimeOf(Track track) {
        if( isOnCurrentSystem( track) ){
            return track.getWaitingTime().getTimestamp();
        }
        throw new NotOnCurrentSystemException();
    }
}
