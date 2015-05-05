package tr.bilkent.oop.railwaysimulator.model.simulation;

import javafx.util.Pair;
import tr.bilkent.oop.railwaysimulator.model.AbstractTime;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.*;

import java.util.List;

/**
 * Created by saygin on 4/19/2015.
 *
 * This contains the state of a railway system.
 * To do this, it hass additional information about the trains' positions.
 * Position's should be SimplePosition. There is no guarantee that this implementation of SimpleSimulation will work with other implementations of Position.
 *
 */
public class SimpleSimulation implements Simulation {

    private static SimpleSimulation instance;
    private AbstractTime currentTime;
    private RailwaySystem system;
    private List< Pair<Train,Position> > trainPositions;


    /*Singleton Pattern*/
    private SimpleSimulation(){
        // TODO constructor
        Train train = new TrainBuilder().build();
    }

    public static SimpleSimulation getInstance(){
        if( instance == null) {
            instance = new SimpleSimulation();
        }
        return instance;
    }

    public void simulate( RailwaySystem system, AbstractTime untilThisTime){
        //TODO
    }

    public void passTime( RailwaySystem system, AbstractTime timeToPass ){
        //TODO
    }

    public void simulate() {
        //TODO
    }

    public void simulateUntil(AbstractTime time) {
        //TODO
    }

    public void setDefaultTimeInterval(AbstractTime interval) {
        //TODO
    }

    public void setDefaultTimeIntervalInMiliseconds(long interval) {
        //TODO
    }

    public void tick() {
        //TODO
    }
}
