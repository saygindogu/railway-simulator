package tr.bilkent.oop.railwaysimulator.model;

import javafx.util.Pair;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.*;

import java.util.List;


/**
 * Created by saygin on 4/19/2015.
 *
 * This contains the state of a railway system.
 * To do this, it hass additional information about the trains' positions.
 * Position's should be SimplePosition. There is no guarantee that this implementation of Simulation will work with other implementations of Position.
 */
public class Simulation {

    private static Simulation instance;
    private AbstractTime currentTime;
    private RailwaySystem system;
    private List< Pair<Train,Position> > trainPositions;


    /*Singleton Pattern*/
    private Simulation(){
        // TODO constructor
    }

    public static Simulation getInstance(){
        if( instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    public void simulate( RailwaySystem system, AbstractTime untilThisTime){
        //TODO
    }

    public void passTime( RailwaySystem system, AbstractTime timeToPass ){
        //TODO
    }
}
