package tr.bilkent.oop.railwaysimulator.model.simulation;

import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Position;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Track;

/**
 * Created by saygin on 4/19/2015.
 */
public interface TrainDispacher extends DynamicObject {
        void dispach();
        Track getTrack();
        Position getPosition();
}
