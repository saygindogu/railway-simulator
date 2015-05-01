package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import java.util.List;

/**
 * Created by saygin on 4/19/2015.
 */
public class Track {
    private List<Station> stationList;
    private Station firstStation;
    private int maxNumverOfStations;
    private int ministanceBetweenStations;

    public Track(int maxNumverOfStations, int ministanceBetweenStations) {
        this.maxNumverOfStations = maxNumverOfStations;
        this.ministanceBetweenStations = ministanceBetweenStations;
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
}
