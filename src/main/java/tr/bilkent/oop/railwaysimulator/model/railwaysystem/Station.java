package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import javafx.geometry.Pos;
import tr.bilkent.oop.railwaysimulator.model.exception.CorruptedStationException;
import tr.bilkent.oop.railwaysimulator.model.simulation.AbstractTimeTable;
import tr.bilkent.oop.railwaysimulator.model.exception.NoTimeTableException;
import tr.bilkent.oop.railwaysimulator.model.exception.StationAlreadyInTrackException;
import tr.bilkent.oop.railwaysimulator.model.exception.StationNotInTrackException;
import tr.bilkent.oop.railwaysimulator.model.identity.Identity;
import tr.bilkent.oop.railwaysimulator.model.identity.IdentityFactory;
import tr.bilkent.oop.railwaysimulator.model.identity.StationIdentityFactory;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Position;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Train;
import tr.bilkent.oop.railwaysimulator.model.simulation.SimpleTimeTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by saygin on 4/19/2015.
 *
 */
public class Station implements Serializable {
    public static final int DEFAULT_MAX_NUM_WAGGONS = 20;
    private Identity identity;
    private String name;

    private List<Track> tracks;
    private List<Position> positions; /**Parallel array with tracks */
    private List<Integer> maxNumberOfWaggonsAtPerons; /**Parallel array with tracks */
    private List< Queue<Train> > trainQueues; /**Parallel array with tracks */
    private List< AbstractTimeTable > departureTimeTables; /**Parallel array with tracks */

    public Station(String name) {
        this.name = name;
        tracks = new ArrayList<Track>(1);
        positions = new ArrayList<Position>(1);
        maxNumberOfWaggonsAtPerons = new ArrayList<Integer>(1);
        trainQueues = new ArrayList<Queue<Train>>(1);
        departureTimeTables = new ArrayList<AbstractTimeTable>(1);
        setNewIdentity();
    }

    private void setNewIdentity() {
        IdentityFactory factory = StationIdentityFactory.getInstance();
        identity = factory.newIdentity();
    }

    public Station( String name, Track track, Position position){
        setNewIdentity();
        this.name = name;

        tracks = new ArrayList<Track>(1);
        tracks.add(track);

        positions = new ArrayList<Position>(1);
        positions.add(position);

        maxNumberOfWaggonsAtPerons = new ArrayList<Integer>(1);
        maxNumberOfWaggonsAtPerons.add(DEFAULT_MAX_NUM_WAGGONS);

        trainQueues = new ArrayList<Queue<Train>>(1);
        trainQueues.add( new LinkedList<Train>());

        departureTimeTables = new ArrayList<AbstractTimeTable>(1);
        departureTimeTables.add( null);
        checkIntegrity();
    }

    public Station( String name, Track track, Position position, int maxNumberOfWaggons){
        setNewIdentity();

        this.name = name;

        tracks = new ArrayList<Track>(1);
        tracks.add(track);

        positions = new ArrayList<Position>(1);
        positions.add(position);

        maxNumberOfWaggonsAtPerons = new ArrayList<Integer>(1);
        maxNumberOfWaggonsAtPerons.add(maxNumberOfWaggons);

        trainQueues = new ArrayList<Queue<Train>>(1);
        trainQueues.add( new LinkedList<Train>());

        departureTimeTables = new ArrayList<AbstractTimeTable>(1);
        departureTimeTables.add( null);
        checkIntegrity();
    }

    /**
     *
     * Warning: This should be called strictly before track's addStation method.
     *
     * @param track the track that this station will be added on.
     */
    protected void addThisTo( Track track){
        addThisTo( track, null); // handle with care..
    }

    /**
     *
     * Warning: This should be called strictly before track's addStation method.
     *
     * @param track the track that this station will be added on.
     */
    protected void addThisTo( Track track, Position position){
        if( tracks.contains( track) ){
            throw new StationAlreadyInTrackException();
        }
        else{
            addNewEntriesToLists(track, position);
            if( !track.getStations().contains(this) ) {
                track.addStation(this);
            }
            else{
                System.err.println("We are in this unknown state!!");
            }
        }
        checkIntegrity();
    }

    private void addNewEntriesToLists( Track track, Position position) {
        tracks.add( track);
        positions.add( position);
        maxNumberOfWaggonsAtPerons.add( DEFAULT_MAX_NUM_WAGGONS);
        trainQueues.add( new LinkedList<Train>());
        departureTimeTables.add( null);
    }

    protected void removeThisFrom( Track track){
        if( tracks.contains( track) ){
            int index = tracks.indexOf( track);
            tracks.remove(index);
            positions.remove(index);
            maxNumberOfWaggonsAtPerons.remove(index);
            departureTimeTables.remove(index);
            trainQueues.remove( index);
            checkIntegrity();
        }
        else throw new StationNotInTrackException();
    }

    private void checkIntegrity() {
        int trackCount = tracks.size();

        boolean noProblem = true;
        noProblem &= trackCount == positions.size();
        noProblem &= trackCount == maxNumberOfWaggonsAtPerons.size();
        noProblem &= trackCount == trainQueues.size();
        noProblem &= trackCount == departureTimeTables.size();

        if( !noProblem){
            throw new CorruptedStationException();
        }
    }

    protected Position getPositionOn( Track track){
        if( tracks.contains( track) ){
            int index = tracks.indexOf( track);
            return positions.get( index);
        }
        else throw new StationNotInTrackException();
    }

    protected int getMaxNumWaggonsOn( Track track){
        if( tracks.contains( track) ){
            int index = tracks.indexOf( track);
            return maxNumberOfWaggonsAtPerons.get(index);
        }
        else throw new StationNotInTrackException();
    }

    protected void addTimeTableOn( Track track, AbstractTimeTable timetable){
        if( tracks.contains( track) ){
            int index = tracks.indexOf( track);
            departureTimeTables.set(index, timetable);
        }
        else throw new StationNotInTrackException();

        checkIntegrity();
    }

    protected void addTrainOn( Track track, Train train){
        if( tracks.contains( track) ){
            int index = tracks.indexOf( track);
            if( departureTimeTables.get( index) == null)
                throw new NoTimeTableException();
            if( trainQueues.get(index) == null){
                trainQueues.set(  index, new LinkedList<Train>() );
            }
            trainQueues.get(index).add(train);
        }
        else throw new StationNotInTrackException();

        checkIntegrity();
    }

    protected Queue<Train> getTrainQueueOn( Track track) {
        if( tracks.contains( track) ){
            int index = tracks.indexOf( track);
            return trainQueues.get(index);
        }
        else throw new StationNotInTrackException();
    }

    protected AbstractTimeTable getTimeTableOn( Track track) {
        if( tracks.contains( track) ){
            int index = tracks.indexOf( track);
            return departureTimeTables.get(index);
        }
        else throw new StationNotInTrackException();
    }

    public Identity getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        return getIdentity().equals(station.getIdentity());

    }

    @Override
    public int hashCode() {
        return getIdentity().hashCode();
    }

    protected List<Track> getTracks() {
        return tracks;
    }

    public void setPositionOn(Track track, Position position) {
        if( tracks.contains( track) ){
            int index = tracks.indexOf( track);
            positions.set( index, position);
        }
        else throw new StationNotInTrackException();
        checkIntegrity();
    }

    public void abortAddStation(Track track) {
        if( tracks.contains(track))
            removeThisFrom( track);
        checkIntegrity();
    }

    @Override
    public String toString() {
        if( positions.size() > 0){
            return "Station{" +
                    "name='" + name + '\'' +
                    "positionOn(0):" + positions.get(0).toString() +
            '}';
        }
        else return "Station {name="+name+"}";

    }
}
