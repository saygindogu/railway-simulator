package tr.bilkent.oop.railwaysimulator.model.simulation;

import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Position;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.SimplePosition;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Train;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Waggon;
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
    private Station targetStation;


    public DynamicTrain( Track track, Train train, Position currentPosition, Direction currentDirection, Station targetStation){
        this.currentTrack = track;
        this.train = train;
        waggons = new ArrayList<DynamicWaggon>();
        for (Waggon waggon : train.getWaggons()) {
            waggons.add( new DynamicWaggon( waggon, 0));
        }
        this.currentDirection = (SimpleDirection)currentDirection;
        this.currentPosition = (SimplePosition)currentPosition;
        this.targetStation = targetStation;

        this.speed = train.getSpeed();
    }


    public DynamicTrain(DynamicTrain train) {
        waggons = new ArrayList<DynamicWaggon>( train.waggons.size() );
        for (DynamicWaggon waggon : train.waggons) {
            waggons.add( new DynamicWaggon(waggon ));
        }
        this.train = train.train;
        currentPosition = new SimplePosition( train.currentPosition.getDistance() );
        currentDirection = new SimpleDirection( train.currentDirection );
        speed = train.speed;
        currentTrack = train.currentTrack; /* No need to copy because this field refers to a static object during simulation */
        targetStation = train.targetStation; /* No need to copy because this field refers to a static object during simulation */

    }

    public void tick(TimeInterval dt) {
        Position newPosition = new SimplePosition( ((SimplePosition) currentPosition).getDistance() + convertToMetersPerMiliSeconds(speed) * currentDirection.getCoefficent() );
        Position stationPosision = RailwaySystemFacade.getInstance().getFirstStationPositionsBetween(currentTrack, currentPosition, newPosition);
        if( stationPosision == null){
            currentPosition = (SimplePosition)newPosition;
        }
        else{
            Station station = RailwaySystemFacade.getInstance().getStationOnPosition( currentTrack, stationPosision );
            if( station.equals( targetStation ) ){
                ((DefaultTrainDispacher) SimpleSimulation.getInstance().getDispacher(currentTrack, stationPosision)).addTrain( this);
            }
            else{
                long timePassed = getRequiredTimeToGo(stationPosision);
                currentPosition = (SimplePosition)stationPosision;
                long waitingTime = RailwaySystemFacade.getInstance().getWaitingTimeOf( currentTrack);
                tick( dt.truncateFromBeggining( timePassed + waitingTime ) );
            }
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
        //TODO maybe introduce speed object because of these conversions
    }

    public Train getTrain() {
        return train;
    }
}
