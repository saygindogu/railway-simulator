package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import tr.bilkent.oop.railwaysimulator.model.simulation.AbstractTimeTable;
import tr.bilkent.oop.railwaysimulator.model.exception.NoTimeTableException;
import tr.bilkent.oop.railwaysimulator.model.exception.StationAlreadyInTrackException;
import tr.bilkent.oop.railwaysimulator.model.exception.StationNotInTrackException;
import tr.bilkent.oop.railwaysimulator.model.identity.Identity;
import tr.bilkent.oop.railwaysimulator.model.identity.IdentityFactory;
import tr.bilkent.oop.railwaysimulator.model.identity.StationIdentityFactory;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Position;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Train;

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

    public Station( String name, Track track, Position position){
        IdentityFactory factory = StationIdentityFactory.getInstance();
        identity = factory.newIdentity();

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
    }

    public Station( String name, Track track, Position position, int maxNumberOfWaggons){
        IdentityFactory factory = StationIdentityFactory.getInstance();
        identity = factory.newIdentity();

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
    }

    protected void addThisTo( Track track, Position position){
        if( tracks.contains( track) ){
            throw new StationAlreadyInTrackException();
        }
        else{
            tracks.add( track);
            positions.add( position);
            maxNumberOfWaggonsAtPerons.add( maxNumberOfWaggonsAtPerons.get( 0));
            trainQueues.add( new LinkedList<Train>());
            departureTimeTables.add( null);
        }
    }

    protected void removeThisFrom( Track track){
        if( tracks.contains( track) ){
            int index = tracks.indexOf( track);
            tracks.remove(index);
            positions.remove(index);
        }
        else throw new StationNotInTrackException();
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
    }

    protected void addTrainOn( Track track, Train train){
        if( tracks.contains( track) ){
            int index = tracks.indexOf( track);
            if( departureTimeTables.get( index) == null)
                throw new NoTimeTableException();
            trainQueues.get(index).add(train);
        }
        else throw new StationNotInTrackException();
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
}
