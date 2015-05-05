package tr.bilkent.oop.railwaysimulator.model.simulation;

import tr.bilkent.oop.railwaysimulator.Main;
import tr.bilkent.oop.railwaysimulator.model.TimeInterval;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 5/5/2015.
 */
public class DynamicTrain implements DynamicObject {

    private List<DynamicWaggon> waggons;
    private Train train;
    private SimplePosition currentPosition;
    private SimpleDirection currentDirection;
    private int speed;
    private Track currentTrack;


    public DynamicTrain( Train train, Position currentPosition, Direction currentDirection){
        this.train = train;
        waggons = new ArrayList<DynamicWaggon>();
        for (Waggon waggon : train.getWaggons()) {
            waggons.add( new DynamicWaggon( waggon, 0));
        }
        this.currentDirection = (SimpleDirection)currentDirection;
        this.currentPosition = (SimplePosition)currentPosition;

    }

    public void tick(TimeInterval dt) {
        //TODO probably this will yield 0 change in position almost always which is a HUUGE problem. Change simple position parameter to float.
        Position newPosition = new SimplePosition( ((SimplePosition) currentPosition).getDistance() + Math.round(convertToMetersPerMiliSeconds(speed) * currentDirection.getCoefficent() ));
        Position stationPosision = RailwaySystemFacade.getInstance().getFirstStationPositionsBetween( currentTrack, currentPosition, newPosition );
        if( stationPosision == null){
            currentPosition = (SimplePosition)newPosition;
        }
        else{
            long timePassed = getRequiredTimeToGo( stationPosision );
            currentPosition = (SimplePosition)stationPosision;
            tick( dt.truncateFromBeggining( timePassed + currentTrack.getWaitingTime().getTimestamp() ) );
        }
    }

    private long getRequiredTimeToGo(Position target) {
        //TODO typecast check
        SimplePosition simplePosition = (SimplePosition)target;
        return Math.round( currentPosition.getDistanceFrom(target) / convertToMetersPerMiliSeconds(speed) ) ;
        // TODO rounding errors might be a pain in the ...head... take care of them somehow
    }

    private float convertToMetersPerMiliSeconds( int metersPerSeconds){
        return metersPerSeconds / 1000;
        //TODO introduce speed object because of these conversions
    }

    public Train getTrain() {
        return train;
    }
}
