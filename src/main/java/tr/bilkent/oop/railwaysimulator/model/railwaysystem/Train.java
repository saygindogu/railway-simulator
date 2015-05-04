package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 4/19/2015.
 *
 * This is supposed to be a static entity object. In simulation time, DynamicTrain objects will be used. This is for not messing with the state of
 * RailwaySystem during simulation time.
 *
 * Destination: The planned destination for the train.
 * If the destination is null, it is set to origin on the simulation time  //TODO do this spesification during implementation of simulation
 * and the train goes to the initial direction and returns after the last station at the line and stops at the origin.
 *
 */
public class Train implements Serializable {

    public static final int DEFAULT_SPEED = 10;
    public static final int DEFAULT_WAGGON_COUNT = 5;
    public static final int MAX_SPEED = 300;

    private Station destination;
    private int speed;
    private List<Waggon> waggons;
    private Direction initialDirection;
    private AbstractTimeTable timeTable;

    /**
     *  Use TrainBuilder To create Train objects.
     */
    protected Train(){
        destination = null;
        speed = DEFAULT_SPEED;
        waggons = null;
        initialDirection = new SimpleDirection( true);
        timeTable = null;
    }

    protected Station getDestination() {
        return destination;
    }

    protected int getSpeed() {
        return speed;
    }

    protected List<Waggon> getWaggons() {
        return waggons;
    }

    protected Direction getInitialDirection() {
        return initialDirection;
    }

    protected void setDestination(Station destination) {
        this.destination = destination;
    }

    protected void setSpeed(int speed) {
        this.speed = speed;
    }

    protected void setInitialDirection(Direction initialDirection) {
        this.initialDirection = initialDirection;
    }

    protected void setTimeTable(AbstractTimeTable timeTable) {
        this.timeTable = timeTable;
    }

    protected void setWaggons(List<Waggon> waggons) {
        this.waggons = waggons;
    }
}
