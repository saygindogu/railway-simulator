package tr.bilkent.oop.railwaysimulator.model.simulation;


import tr.bilkent.oop.railwaysimulator.model.AbstractTimeTable;
import tr.bilkent.oop.railwaysimulator.model.SimpleTime;
import tr.bilkent.oop.railwaysimulator.model.SimpleTimeTable;
import tr.bilkent.oop.railwaysimulator.model.TimeInterval;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.*;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by saygin on 5/1/2015.
 */
public class DefaultTrainDispacher implements TrainDispacher {

    Track track;
    Queue<Train> trains;
    AbstractTimeTable timeTable;
    Position position;
    SimpleTime now, future;

    public DefaultTrainDispacher(Track track, Queue<Train> trainQueue, AbstractTimeTable timetable, Position position) {
        trains = trainQueue;
        this.track = track;
        this.timeTable = timetable;
        this.position = position;
    }

    public void dispach() {
        Train train = trains.poll();
        Station targetStation;
        if( train.getDestination() != null ){
            targetStation = train.getDestination();
        }
        else{
            targetStation = RailwaySystemFacade.getInstance().getStationOnPosition( track, position);
        }
        DynamicTrain dynamicTrain = new DynamicTrain( track, train, position, train.getInitialDirection(), targetStation );
        dynamicTrain.tick( new TimeInterval( now, future));
    }

    public Track getTrack() {
        return track;
    }

    public Position getPosition() {
        return position;
    }

    /**
     *
     * @param train a train which comes to the station that is the last
     *              station of that train and it needs to be added to the dispacher dynamically.
     */
    public void addTrain( DynamicTrain train) {
        trains.add( train.getTrain() );
    }

    public void tick(TimeInterval dt) {
        //TODO typecasting check
        SimpleTimeTable simpleTimeTable = (SimpleTimeTable) timeTable;
        if( simpleTimeTable.getNextTime( dt.begin() ).compareTo( dt.end() ) < 0 ){
            TimeInterval nextInterval = dt.truncateFromBeggining(simpleTimeTable.getNextTime(dt.begin()).getTimeDistanceFrom(dt.begin()));
            dispach();
            tick( nextInterval);
        }

    }
}
