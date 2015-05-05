package tr.bilkent.oop.railwaysimulator.model.simulation;

import javafx.util.Pair;
import tr.bilkent.oop.railwaysimulator.model.AbstractTime;
import tr.bilkent.oop.railwaysimulator.model.SimpleTime;
import tr.bilkent.oop.railwaysimulator.model.TimeInterval;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.*;

import java.util.List;
import java.util.Queue;

/**
 * Created by saygin on 4/19/2015.
 *
 * This contains the state of a railway system.
 * To do this, it hass additional information about the trains' positions.
 * Position's should be SimplePosition. There is no guarantee that this implementation of SimpleSimulation will work with other implementations of Position.
 *
 */
public class SimpleSimulation implements Simulation {

    private transient static SimpleSimulation instance;
    private transient long timeInterval;
    private transient AbstractTime currentTime;
    private transient RailwaySystem system;
    private transient List< DynamicTrain > dynamicTrains;
    private transient List< TrainDispacher > dispachers;
    private Queue<SimulationState> stateQueue;


    /*Singleton Pattern*/
    private SimpleSimulation(){
        timeInterval = DEFAULT_TIME_SIMULATION.getTimestamp();
        currentTime = SimpleTime.BIRTH_OF_CRONUS;
        system = RailwaySystemFacade.getInstance().getCurrentSystem();
    }

    public static SimpleSimulation getInstance(){
        if( instance == null) {
            instance = new SimpleSimulation();
        }
        return instance;
    }

    public void simulate() {
        simulateUntil( DEFAULT_TIME_SIMULATION);
    }

    public void simulateUntil(AbstractTime time) {
        while( time.compareTo( currentTime) < 0 ){
            tick();
        }
    }

    public Queue<SimulationState> getStateQueue() {
        return stateQueue;
    }

    public void setTimeInterval(AbstractTime interval) {
        timeInterval = interval.getTimestamp();
    }

    public void setTimeIntervalInMiliseconds(long interval) {
        timeInterval = interval;
    }

    public void tick() {
        SimpleTime now = (SimpleTime) currentTime;
        SimpleTime future = new SimpleTime(  now.getTimestamp() + timeInterval );
        TimeInterval interval = new TimeInterval( now, future);
        for (DynamicTrain dynamicTrain : dynamicTrains) {
            dynamicTrain.tick( interval);
        }
        for (TrainDispacher dispacher : dispachers) {
            dispacher.tick( interval);
        }
        now = future;
        stateQueue.add( getStateSnapShot() );
    }

    private SimulationState getStateSnapShot() {
        //TODO ...
        return null;
    }

    public TrainDispacher getDispacher(Track track, Position position) {
        for (TrainDispacher trainDispacher : dispachers) {
            if( trainDispacher.getTrack().equals(track) && trainDispacher.getPosition().equals( position) )
                return trainDispacher;
        }
        return null;

    }
}
