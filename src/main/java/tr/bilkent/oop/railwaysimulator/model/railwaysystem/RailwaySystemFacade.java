package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import tr.bilkent.oop.railwaysimulator.model.RSim;
import tr.bilkent.oop.railwaysimulator.model.exception.NotOnCurrentSystemException;
import tr.bilkent.oop.railwaysimulator.model.exception.RailwaySystemException;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Position;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Train;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.TrainBuilder;
import tr.bilkent.oop.railwaysimulator.model.simulation.*;
import tr.bilkent.oop.railwaysimulator.model.user.User;
import tr.bilkent.oop.railwaysimulator.model.user.UserGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 5/1/2015.
 *
 * Gateway to the RailwaySystem subsystem. This subsystem provides only this interface to outside.
 *
 * //TODO integration tests
 */
public class RailwaySystemFacade {
    private static RailwaySystemFacade instance = null;
    private RailwaySystem currentSystem;
    private User currentUser;
    private List<TrainDispacher> dispachers = null;

    private RailwaySystemFacade(){
        currentSystem = null;
        currentUser = null;
    }

    public static RailwaySystemFacade getInstance(){
        if( instance == null)
            instance = new RailwaySystemFacade();
        return instance;
    }

    public static void destroy() {
        instance = null;
    }

    /*
     Creational interface
     */
    public void initilizeNewSystemFor( User user){
        currentUser = user;
        currentSystem = new RailwaySystem( user);
        currentSystem.notifyChanges();
        RSim.getInstance().setCurrentUser(user);
        RSim.getInstance().setCurrentSystem(currentSystem);
    }

    public void initilizeSystemFor( User user, RailwaySystem system){
        // Done by Gizem - TODO check is user has read right
        /*if (!currentSystem.getPermissions().getOthersPermissions()[RailwayPermissions.OPEN])
            throw new RailwaySystemException();
        */

        currentUser = user;
        currentSystem = system;
        currentSystem.notifyChanges();
    }

    public void setPermissionsOfSystem(){
        //TODO check first if we can do this (  we hold modify right and stuff.. )
    }

    /*
     Station Interface...
     */
    public void setCurrentRailwaySystem( RailwaySystem system){
        //TODO maybe save state here...

        // Done by Gizem - TODO read right check
        if (!currentSystem.getPermissions().getOthersPermissions()[RailwayPermissions.OPEN])
            throw new RailwaySystemException();

        this.currentSystem = system;

    }

    public void addStationTo( Track track, Station station, Position position){
        // Done by Gizem - TODO modify right check
        if( !isCurrentUserOwnerOfCurrentSystem()){
            if (!currentSystem.getPermissions().getOthersPermissions()[RailwayPermissions.MODIFY])
                throw new RailwaySystemException();
        }
        if( isOnCurrentSystem( track)){
            station.addThisTo(track, position);
            currentSystem.notifyChanges();
        }
        else throw new NotOnCurrentSystemException();
    }

    public void addStationTo( Track track, Station station){
        // Done by Gizem - TODO modify right check
        if( !isCurrentUserOwnerOfCurrentSystem()){
            if (!currentSystem.getPermissions().getOthersPermissions()[RailwayPermissions.MODIFY])
                throw new RailwaySystemException();
        }
        if( isOnCurrentSystem(track)){
            station.addThisTo(track);
            currentSystem.notifyChanges();
        }
        else throw new NotOnCurrentSystemException();

    }

    public void removeStationFrom( Track track, Station station){
        // Done by Gizem - TODO modify right check
        if( !isCurrentUserOwnerOfCurrentSystem()){
            if (!currentSystem.getPermissions().getOthersPermissions()[RailwayPermissions.MODIFY])
                throw new RailwaySystemException();
        }
        if( isOnCurrentSystem( track)){
            station.removeThisFrom(track);
            currentSystem.notifyChanges();
        }
        else throw new NotOnCurrentSystemException();
    }

    public Position getStationPositionOnTrack( Track track, Station station){
        if( isOnCurrentSystem( track)){
            return station.getPositionOn(track);
        }
        else throw new NotOnCurrentSystemException();
    }


    /*
        //TODO check if RailwaySystem interface is complete
     */

    public int getStationsMaxNumWaggonsOnTrack( Track track, Station station){
        if( isOnCurrentSystem( track)){
            return station.getMaxNumWaggonsOn(track);
        }
        else throw new NotOnCurrentSystemException();
    }

    public void setUserGroup( UserGroup group ){
        currentSystem.setGroup( group );
        currentSystem.notifyChanges();
    }

    public boolean isCurrentUserOwnerOfCurrentSystem(){
        return currentSystem.getOwner().equals( currentUser );
    }

    public Track addNewTrackToCurrentSystem(){
        Track track = new Track( 50, 10); //TODO make these magic numbers constant so we can understand what the hell do those mean.
        currentSystem.getTracks().add(track);
        currentSystem.notifyChanges();
        return track;
    }

    public void addNewTrackToCurrentSystem( Station station){
        Track track = new Track( 50, 10, station);
        currentSystem.getTracks().add(track);
        currentSystem.notifyChanges();
    }

    public boolean isOnCurrentSystem( Track track){
        return currentSystem.getTracks().contains(track);
    }

    public boolean isOnCurrentSystem( Station station){
        return currentSystem.contains(station);
    }

    public void addNewTrainTo( Track track, Station station) {
        addNewTrainTo(track, station, new SimpleDirection(true));
    }

    public void addNewTimeTableTo(Track track, Station station) {
        //Done by Gizem - TODO check modify rights
        if( !isCurrentUserOwnerOfCurrentSystem()){
            if (!currentSystem.getPermissions().getOthersPermissions()[RailwayPermissions.MODIFY])
                throw new RailwaySystemException();
        }
        if( isOnCurrentSystem( station) ){
            AbstractTimeTable timeTable;
            timeTable = SimpleTimeTable.DEFAULT;
            station.addTimeTableOn(track, timeTable);
            currentSystem.notifyChanges();
        }
        else throw new NotOnCurrentSystemException();
    }

    public void addTrainTo( Track track, Station station, Train train){
        //Done by Gizem - TODO check modify rights
        if( !isCurrentUserOwnerOfCurrentSystem()){
            if (!currentSystem.getPermissions().getOthersPermissions()[RailwayPermissions.MODIFY])
                throw new RailwaySystemException();
        }
        if( isOnCurrentSystem( station) ){
            station.addTrainOn(track, train);
            currentSystem.notifyChanges();
        }
        else throw new NotOnCurrentSystemException();
    }

    public Position getFirstStationPositionsBetween( Track track, Position currentPosition, Position newPosition) {
        //Done by Gizem - TODO read right check
        if( !isCurrentUserOwnerOfCurrentSystem()){
            if (!currentSystem.getPermissions().getOthersPermissions()[RailwayPermissions.MODIFY])
                throw new RailwaySystemException();
        }
        if( isOnCurrentSystem( track)){
            return track.getFirstStationPositionsBetween( currentPosition, newPosition );
        }
        else throw new NotOnCurrentSystemException();
    }

    public List<TrainDispacher> getDispachersOfTheSystem(){
        //Done by Gizem - TODO check simulate permissions
        if( !isCurrentUserOwnerOfCurrentSystem()){
            if (!currentSystem.getPermissions().getOthersPermissions()[RailwayPermissions.SIMULATE])
                throw new RailwaySystemException();
        }
        if( dispachers == null){
            ArrayList<TrainDispacher> list = new ArrayList<TrainDispacher>();
            for (Track track : currentSystem.getTracks()) {
                for (Station station : track.getStations()) {
                    if( !station.getTrainQueueOn(track).isEmpty()){
                        list.add( new DefaultTrainDispacher( track, station.getTrainQueueOn(track), station.getTimeTableOn(track), station.getPositionOn(track) ) );
                    }
                }
            }
            dispachers = list;
            return dispachers;
        }
        else return dispachers;


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

    public void logout(){
        currentUser = null;
        dispachers = null;
    }

    public void createNewSystem() {
        currentSystem = new RailwaySystem( currentUser );
    }

    public List<Track> getTracksOnSystem() {
        return currentSystem.getTracks();
    }

    public Station getFirstStationOn(Track track) {
        return track.getFirstStation();
    }

    public Station getLastStationOn(Track track) {
        return track.getLastStation();
    }

    public void addNewTrainTo(Track track, Station station, Direction direction) {
        //Done by Gizem - TODO check modify rights
        if( !isCurrentUserOwnerOfCurrentSystem()){
            if (!currentSystem.getPermissions().getOthersPermissions()[RailwayPermissions.OPEN])
                throw new RailwaySystemException();
        }
        if( isOnCurrentSystem(station) ){
            Train train;
            TrainBuilder builder = new TrainBuilder();
            builder.withInitialDirection( direction);
            train = builder.build();
            station.addTrainOn(track, train);
            currentSystem.notifyChanges();
        }
        else throw new NotOnCurrentSystemException();
    }
}
