package tr.bilkent.oop.railwaysimulator.model.simulation;

import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Position;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by saygin on 4/19/2015.
 *
 * This contains the state of a railway system.
 * To do this, it hass additional information about the trains' positions.
 * Position's should be SimplePosition. There is no guarantee that this implementation of SimpleSimulation will work with other implementations of Position.
 *
 */
public class SimpleSimulation implements Simulation {

    private transient static SimpleSimulation instance = null;
    private transient long timeInterval;
    private transient AbstractTime currentTime;
    private transient List< DynamicTrain > dynamicTrains;
    private transient List< TrainDispacher > dispachers;
    private Queue<SimulationState> stateQueue;
    private AbstractTime time;


    public void resetSimulation(){
        //TODO clear state queue ( maybe save it to disk) clear dynamic trains, get dispachers again.
       instance = null;
    }

    /*Singleton Pattern*/
    private SimpleSimulation(){
        timeInterval = DEFAULT_INTERVAL_SIMULATION.getTimestamp();
        currentTime = SimpleTime.BIRTH_OF_CRONUS;
        stateQueue = new LinkedList<SimulationState>();
        dynamicTrains = new ArrayList<DynamicTrain>();

        dispachers = RailwaySystemFacade.getInstance().getDispachersOfTheSystem();
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
        this.time = time;
        final ExecutorService service = Executors.newSingleThreadExecutor();

        try
        {
            final Future<Object> f = service.submit(complicatedCalculation);

            f.get(1, TimeUnit.MINUTES);
        }
        catch (final TimeoutException e)
        {
            System.err.println("Calculation took to long");
        }
        catch ( Exception e){
            System.err.println( "caught exception." );
            e.printStackTrace();
        }
        finally
        {
            service.shutdown();
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
        try {
            for (DynamicTrain train : dynamicTrains) {
                train.tick(interval);
            }
        }
        catch ( ConcurrentModificationException cme){
            System.err.println(" Concurrent modification exception");
        }
        finally {
            for (TrainDispacher dispacher : dispachers) {
                dispacher.tick( interval);
            }
            currentTime = future;
            stateQueue.add( getStateSnapShot() );
        }

    }

    void addDynamicTrain(DynamicTrain dynamicTrain){
        dynamicTrains.add(dynamicTrain);
    }

    private SimulationState getStateSnapShot() {
        return new SimpleSimulationState( currentTime, dynamicTrains, dispachers);
    }

    public TrainDispacher getDispacher(Track track, Position position) {
        for (TrainDispacher trainDispacher : dispachers) {
            if( trainDispacher.getTrack().equals(track) && trainDispacher.getPosition().equals( position) )
                return trainDispacher;
        }
        return null;

    }

    public void removeDynamicTrain(DynamicTrain dynamicTrain) {
        dynamicTrains.remove( dynamicTrain);
    }

    public static void destroy() {
        instance = null;
    }

    static Callable<Object> complicatedCalculation
            = new Callable<Object>()
    {
        public Object call() throws Exception
        {
            while( SimpleSimulation.getInstance().time.compareTo( SimpleSimulation.getInstance().currentTime) > 0 ){
                SimpleSimulation.getInstance().tick();
            }
            return SimpleSimulation.getInstance();
        }

    };
}
