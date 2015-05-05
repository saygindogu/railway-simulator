package tr.bilkent.oop.railwaysimulator.model.railwaysimulation;

import java.io.Serializable;

/**
 * Created by saygin on 5/3/2015.
 */
public interface Position extends Serializable, Comparable<Position> {
    float getDistanceFrom(Position other);

    //test

}
