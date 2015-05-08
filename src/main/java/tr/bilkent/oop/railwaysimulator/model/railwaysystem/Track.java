package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import javafx.util.Pair;
import tr.bilkent.oop.railwaysimulator.model.exception.*;
import tr.bilkent.oop.railwaysimulator.model.identity.StationIdentityFactory;
import tr.bilkent.oop.railwaysimulator.model.simulation.AbstractTime;
import tr.bilkent.oop.railwaysimulator.model.simulation.SimpleTime;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Position;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.SimplePosition;
import tr.bilkent.oop.railwaysimulator.model.simulation.SimpleDirection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 4/19/2015.
 */
public class Track implements Serializable {
    public static final AbstractTime DEFAULT_WAITING_TIME = SimpleTime.ONE_MINUTE;
    public static final int DEFAULT_DISTANCE_BETWEEN_STATIONS = 250;

    private List<Station> stationList;
    private AbstractTime waitingTime;
    private int maxNumverOfStations;
    private int ministanceBetweenStations;

    public Track(int maxNumverOfStations, int ministanceBetweenStations) {
        this.maxNumverOfStations = maxNumverOfStations;
        this.ministanceBetweenStations = ministanceBetweenStations;
        waitingTime = DEFAULT_WAITING_TIME;

        stationList = new ArrayList<Station>();
    }

    public Track(int maxNumverOfStations, int ministanceBetweenStations, Station firstStation) {
        this.maxNumverOfStations = maxNumverOfStations;
        this.ministanceBetweenStations = ministanceBetweenStations;
        waitingTime = DEFAULT_WAITING_TIME;

        stationList = new ArrayList<Station>();
    }

    /**
    *    This method determines the new position of the station and sets it on the station. ,
    *    After, it calls addStation( s, newPosition );
    *
    *    Warning this method have to be called strictly after station's
    *    addThisTo( Track track, Position position) method!
    *    else Station will throw an exception ( StationNotOnTrackException )
    */
    protected void addStation( Station station){
        if( station == null){
            throw new NullStationException();
        }
        if( stationList.size() >= maxNumverOfStations ){
            throw new ExceededStationLimitException();
        }

        Position newPosition;
        if( station.getPositionOn(this) == null ){
            if( stationList.size() == 0){
                newPosition = SimplePosition.ZERO;
            }
            else{
                newPosition = getPositionOfStationAfterCurrentLastStation();
            }
            station.setPositionOn( this, newPosition);
        }
        else{
            newPosition = station.getPositionOn(this);
        }
        addStation(station, newPosition);

    }

    /**
     *    This method determines the new position of the station and sets it on the station. ,
     *    After, it calls addStation( s, newPosition );
     *
     *    Warning this method have to be called strictly after station's
     *    addThisTo( Track track, Position position) method!
     *    else Station will throw an exception ( StationNotOnTrackException )
     */
    protected void addStation( Station s, Position position){
        if( s == null){
            throw  new NullStationException();
        }

        if( position == null){
            s.abortAddStation( this);
            throw new NullPositionException();
        }

        if( stationList.size() >= maxNumverOfStations ){
            s.abortAddStation( this);
            throw new ExceededStationLimitException();
        }

        if( !s.getPositionOn( this).equals( position) ){
            s.abortAddStation( this);
            throw new StationPositionMismatchException();
        }

        if( stationList.size() == 0 && !s.getPositionOn( this).equals( SimplePosition.ZERO ) ){
            s.abortAddStation( this);
            throw new FirstStationPositionException();
        }
        else if( stationList.size() == 0){
            stationList.add( s);
            return;
        }
        /* integrity checks and corner cases ends here.*/

        Pair< Station, Station> stationPair = getLeftAndRightStations( position);
        if( stationPair.getValue() != null ){
            if( position.getDistanceFrom(  stationPair.getValue().getPositionOn(this) ) < ministanceBetweenStations ){
                s.abortAddStation(this);
                throw new MinimumDistanceBetweenStationsException();
            }
        }

        if ( position.getDistanceFrom(stationPair.getKey().getPositionOn(this)) < ministanceBetweenStations ){
            s.abortAddStation(this);
            throw new MinimumDistanceBetweenStationsException();
        }

        stationList.add(stationList.indexOf(stationPair.getKey()) + 1, s);
    }

    protected Pair<Station,Station> getLeftAndRightStations(Position position) {
        Station left, right;
        left = right = null;
        for ( int i = 0; i < stationList.size(); i++) {
            if( position.compareTo( stationList.get(i).getPositionOn(this)) < 0 ){
                if( i > 0)
                    left = stationList.get( i - 1);
                right = stationList.get( i);
                break;
            }
        }
        if( left == null){
            left = getLastStation();
        }
        return new Pair<Station, Station>(left,right);

    }

    private SimplePosition getPositionOfStationAfterCurrentLastStation() {
        return new SimplePosition( ( (SimplePosition) stationList.get(stationList.size() - 1).getPositionOn(this) ).getDistance() + DEFAULT_DISTANCE_BETWEEN_STATIONS);
    }


    protected List<Station> getStations() {
        return stationList;
    }

    protected AbstractTime getWaitingTime() {
        return waitingTime;
    }

    protected Position getFirstStationPositionsBetween(Position currentPosition, Position newPosition) {
        SimpleDirection direction = SimpleDirection.getDirection( currentPosition, newPosition);
        if( direction.isPositive() ){
            for (Station station : stationList) {
                if( station.getPositionOn(this).compareTo( currentPosition) >= 0 )
                    if( station.getPositionOn(this).compareTo( newPosition) < 0)
                        return station.getPositionOn(this);
                    else return null;
            }
            return getLastStation().getPositionOn(this);
        }
        else{
            for( int i = stationList.size() - 1; i >= 0 ; i--){
                if( stationList.get(i).getPositionOn(this).compareTo( currentPosition) < 0 )
                    if( stationList.get(i).getPositionOn(this).compareTo( newPosition) > 0)
                        return stationList.get(i).getPositionOn(this);
                    else return null;
            }
            return getLastStation().getPositionOn(this);
        }
    }

    protected Station getLastStation() {
        if( stationList.size() <= 0) return null;
        return stationList.get( stationList.size() - 1 );
    }

    protected Station getFirstStation() {
        if( stationList.size() <= 0) return null;
        return stationList.get( 0 );
    }

    @Override
    public String toString() {
        String s =  "Track{ Stations:\n";
        for (Station station : stationList) {
            s += station.toString() + ",\n";
        }
        return s + "}";


    }
}
