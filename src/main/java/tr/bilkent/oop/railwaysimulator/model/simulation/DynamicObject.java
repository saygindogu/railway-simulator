package tr.bilkent.oop.railwaysimulator.model.simulation;

import tr.bilkent.oop.railwaysimulator.model.TimeInterval;

/**
 * Created by saygin on 5/5/2015.
 */
public interface DynamicObject {
    void tick( TimeInterval dt);
}
