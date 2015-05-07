package tr.bilkent.oop.railwaysimulator.model.simulation;

/**
 * Created by saygin on 5/5/2015.
 *
 * Dynamic RailwaySystem object..
 */
public interface Simulation {
    public AbstractTime DEFAULT_TIME_SIMULATION = SimpleTime.ONE_DAY;
    public AbstractTime DEFAULT_INTERVAL_SIMULATION = SimpleTime.ONE_SECOND;
    void simulate();
    void simulateUntil( AbstractTime time);
    void setTimeInterval(AbstractTime interval);
    void setTimeIntervalInMiliseconds( long interval );
    void tick();
}
