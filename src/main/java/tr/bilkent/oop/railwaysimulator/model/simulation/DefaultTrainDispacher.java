package tr.bilkent.oop.railwaysimulator.model.simulation;


import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Position;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Train;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.*;

import java.util.LinkedList;
import java.util.Queue;

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
        trains = new LinkedList<Train>();
        trains.addAll( trainQueue );
        this.track = track;
        this.timeTable = timetable;
        this.position = position;
    }

    public DefaultTrainDispacher(DefaultTrainDispacher trainDispacher) {
        this.track = trainDispacher.track;
        trains = new LinkedList<Train>();
        trains.addAll( trainDispacher.trains ); /* no need to copy train objects*/
        timeTable = trainDispacher.timeTable; /* Dynamic time tables is not supported, so no need to copy this.*/
        position = trainDispacher.position;
        now = trainDispacher.now;
    }

    public void dispach() {
        if( trains.size() > 0) {
            Train train = trains.poll();
            Station targetStation;
            if (train.getDestination() != null) {
                targetStation = train.getDestination();
            } else {
                targetStation = RailwaySystemFacade.getInstance().getStationOnPosition(track, position);
            }
            DynamicTrain dynamicTrain = new DynamicTrain(track, train, position, train.getInitialDirection(), targetStation);
            SimpleSimulation.getInstance().addDynamicTrain(dynamicTrain);

            dynamicTrain.moveEpsilon();
            now = new SimpleTime( now.getTimestamp() + 1);
            dynamicTrain.tick(new TimeInterval(now, future));

            //TODO the train is not added to the simulation's list or it is not saved to the state. Or it is immidiately removed. find out why and fix it.
        }
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
        now = (SimpleTime) dt.begin();
        future = (SimpleTime) dt.end();
        if( simpleTimeTable.getNextTime( dt.begin() ).compareTo( dt.end() ) < 0 ){
            AbstractTime nextDispachTime = simpleTimeTable.getNextTime(dt.begin());
            long elapsedTime = nextDispachTime.getTimeDistanceFrom(dt.begin());
            if( elapsedTime < 0){
                // no more trains to  in this interval. We are done.
                return;
            }
            else if( elapsedTime == 0){
                elapsedTime++;
            }
            TimeInterval nextInterval = dt.truncateFromBeggining( elapsedTime );
            dispach();
            tick(nextInterval);
        }

    }
}
