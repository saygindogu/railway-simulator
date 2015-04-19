package tr.bilkent.oop.railwaysimulator.model;

/**
 * Created by saygin on 4/19/2015.
 */
public class Simulation {

    private static Simulation instance;

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
}
