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
    private boolean waitingAtStation;
    private AbstractTime stationWaitTimeEnd;


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
        waitingAtStation = false;
        stationWaitTimeEnd = null;
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
        waitingAtStation = false;
        stationWaitTimeEnd = null;

    }

    public void tick(TimeInterval dt) {
        TimeInterval timeInterval;
        if( waitingAtStation ){
            if( dt.end().compareTo( stationWaitTimeEnd) > 0) {
                moveEpsilon();
                timeInterval = dt.truncateFromBeggining(stationWaitTimeEnd.getTimeDistanceFrom(dt.end()));
                waitingAtStation = false;
                stationWaitTimeEnd = null;
            }
            else{
                return; // it will wait until the time interval ends.
                //TODO dynamic waggon stuff goes here. This is not supported in this version. maybe we'll do it in the future.
            }
        }
        else{ //not waiting at station.
            timeInterval = dt;
        }
        long elapsedTime = timeInterval.end().getTimestamp() - timeInterval.begin().getTimestamp(); //TODO please make this a function in TimeInterval...
        Position newPosition = new SimplePosition(  currentPosition.getDistance() + convertToMetersPerMiliSeconds(speed) * currentDirection.getCoefficent() * elapsedTime );//TODO please make this multiline to have readibility
        Position stationPosition = RailwaySystemFacade.getInstance().getFirstStationPositionsBetween(currentTrack, currentPosition, newPosition);
        if( stationPosition == null){
            currentPosition = (SimplePosition)newPosition;
        }
        else{
            Station station = RailwaySystemFacade.getInstance().getStationOnPosition(currentTrack, stationPosition);
            if( station.equals( RailwaySystemFacade.getInstance().getLastStationOn(currentTrack)) && currentDirection.isPositive() ){
                currentDirection.changeDirection();
            }
            if( station.equals( RailwaySystemFacade.getInstance().getFirstStationOn(currentTrack)) && !currentDirection.isPositive() ){
                currentDirection.changeDirection();
            }
            if( station.equals( targetStation ) ){ //Train has reached it's destination.
                ((DefaultTrainDispacher) SimpleSimulation.getInstance().getDispacher(currentTrack, stationPosition)).addTrain(this);
                SimpleSimulation.getInstance().removeDynamicTrain(this);
                System.out.println( "destination reached" );
            }
           else{ //the train has reached a different station than it's destination.
                long timePassed = getRequiredTimeToGo(stationPosition);
                currentPosition = (SimplePosition)stationPosition;
                long waitingTime = RailwaySystemFacade.getInstance().getWaitingTimeOf(currentTrack);
                timeInterval = timeInterval.truncateFromBeggining( timePassed );
                if( waitingTime > elapsedTime){
                    stationWaitTimeEnd = new SimpleTime( timeInterval.begin().getTimestamp()+waitingTime );
                    waitingAtStation = true;
                }
                else{
                    timeInterval = timeInterval.truncateFromBeggining( waitingTime );
                }
                tick( timeInterval);
            }
        }
    }

    protected void moveEpsilon() {
        currentPosition = new SimplePosition(  currentPosition.getDistance() + (2 * SimplePosition.EPSILON) * currentDirection.getCoefficent() );
    }

    private long getRequiredTimeToGo(Position target) {
        //TODO typecast check
        SimplePosition simplePosition = (SimplePosition)target;
        return Math.round( currentPosition.getDistanceFrom(target) / convertToMetersPerMiliSeconds(speed) ) ;
        // TODO rounding errors might be a pain in the ...head... take care of them somehow
    }

    private float convertToMetersPerMiliSeconds( int metersPerSeconds){
        return (float)metersPerSeconds / 1000;
        //TODO maybe introduce speed object because of these conversions
    }

    public Train getTrain() {
        return train;
    }

    @Override
    public String toString() {
        return "DynamicTrain{" +
                "currentPosition=" + currentPosition.toString() +
                ", currentDirection=" + currentDirection.toString() +
                ", speed=" + speed +
                '}';
    }
}
