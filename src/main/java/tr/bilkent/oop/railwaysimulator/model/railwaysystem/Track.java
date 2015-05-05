package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

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
    public static final int DEFAULT_DISTANCE_BETWEEN_STATIONS = 0;

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

    protected boolean addStation( Station s){
        if( stationList.size() >= maxNumverOfStations ){
            return false;
        }
        s.addThisTo(this, new SimplePosition(((SimplePosition) stationList.get(stationList.size() - 1).getPositionOn(this)).getDistance() + DEFAULT_DISTANCE_BETWEEN_STATIONS));
        stationList.add( s);
        return true;
    }

    protected boolean addStation( Station s, Position position){
        if( stationList.size() >= maxNumverOfStations ){
            return false;
        }
        else if( position.getDistanceFrom( stationList.get(stationList.size()-1).getPositionOn(this)) > ministanceBetweenStations ){
            return false;
        }
        s.addThisTo(this, new SimplePosition(((SimplePosition) stationList.get(stationList.size() - 1).getPositionOn(this)).getDistance() + DEFAULT_DISTANCE_BETWEEN_STATIONS));
        stationList.add( s);
        return true;
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
                if( station.getPositionOn(this).compareTo( currentPosition) > 0 )
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

    private Station getLastStation() {
        return stationList.get( stationList.size() - 1 );
    }

    private Station getFirstStation() {
        return stationList.get( 0 );
    }
}
