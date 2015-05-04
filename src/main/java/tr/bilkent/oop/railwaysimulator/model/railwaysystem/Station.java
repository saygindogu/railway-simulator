package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import tr.bilkent.oop.railwaysimulator.model.identity.Identity;
import tr.bilkent.oop.railwaysimulator.model.identity.IdentityFactory;
import tr.bilkent.oop.railwaysimulator.model.identity.StationIdentityFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 4/19/2015.
 */
public class Station implements Serializable {
    public static final int DEFAULT_MAX_NUM_WAGGONS = 20;
    private Identity identity;
    private String name;
    private transient List<Track> tracks;
    private List<Position> positions; // positions on tracks
    private List<Integer> maxNumberOfWaggonsAtPerons;
    private TrainDispacherImpl trainDispacher;

    public Station( String name, Track track, Position position){
        IdentityFactory factory = StationIdentityFactory.getInstance();
        identity = factory.newIdentity();

        this.name = name;
        tracks = new ArrayList<Track>(1);
        positions = new ArrayList<Position>(1);
        maxNumberOfWaggonsAtPerons = new ArrayList<Integer>(1);
        tracks.add(track);
        positions.add(position);
        maxNumberOfWaggonsAtPerons.add( DEFAULT_MAX_NUM_WAGGONS);
        trainDispacher = null;
    }

    public Station( String name, Track track, Position position, int maxNumberOfWaggons){
        IdentityFactory factory = StationIdentityFactory.getInstance();
        identity = factory.newIdentity();

        this.name = name;
        tracks = new ArrayList<Track>(1);
        positions = new ArrayList<Position>(1);
        maxNumberOfWaggonsAtPerons = new ArrayList<Integer>(1);
        tracks.add(track);
        positions.add(position);

        trainDispacher = null;
    }

    protected void addThisTo( Track track, Position position){
        if( tracks.contains( track) ){
            throw new StationAlreadyInTrackException();
        }
        else{
            tracks.add( track);
            positions.add( position);
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
            return maxNumberOfWaggonsAtPerons.get( index);
        }
        else throw new StationNotInTrackException();
    }

    protected void setTrainDispacher( TrainDispacherImpl dispacher){
        this.trainDispacher = dispacher;
    }

    protected void addTrain( Train train){
        if( trainDispacher == null ){
            throw new NoTrainDispacherException();
        }
        else{
            trainDispacher.addTrain( train);
        }
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
