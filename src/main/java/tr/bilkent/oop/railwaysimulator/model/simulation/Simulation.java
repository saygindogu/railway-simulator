package tr.bilkent.oop.railwaysimulator.model.simulation;

import tr.bilkent.oop.railwaysimulator.model.AbstractTime;
import tr.bilkent.oop.railwaysimulator.model.SimpleTime;

/**
 * Created by saygin on 5/5/2015.
 *
 * Dynamic RailwaySystem object..
 */
public interface Simulation extends DynamicObject {
    public static AbstractTime DEFAULT_TIME_SIMULATION = SimpleTime.OneDay;
    void simulate();
    void simulateUntil( AbstractTime time);
    void setDefaultTimeInterval( AbstractTime interval );
    void setDefaultTimeIntervalInMiliseconds( long interval );
}
