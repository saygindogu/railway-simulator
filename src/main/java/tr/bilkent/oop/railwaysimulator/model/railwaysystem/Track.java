package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by saygin on 4/19/2015.
 */
public class Track implements Serializable {
    public static final AbstractTime DEFAULT_WAITING_TIME = SimpleTime.OneMinute;
    private List<Station> stationList;
    private Station firstStation;
    private AbstractTime waitingTime;
    private int maxNumverOfStations;
    private int ministanceBetweenStations;

    public Track(int maxNumverOfStations, int ministanceBetweenStations) {
        this.maxNumverOfStations = maxNumverOfStations;
        this.ministanceBetweenStations = ministanceBetweenStations;
        waitingTime = DEFAULT_WAITING_TIME;
    }

    public Track(int maxNumverOfStations, int ministanceBetweenStations, Station firstStation) {
        this.maxNumverOfStations = maxNumverOfStations;
        this.ministanceBetweenStations = ministanceBetweenStations;
        this.firstStation = firstStation;
    }

    public boolean addStation( Station s){
        //TODO
        return  false;
    }

    public List<Station> getStations() {
        return stationList;
    }
}
