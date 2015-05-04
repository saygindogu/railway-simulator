package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import java.io.Serializable;

/**
 * Created by saygin on 5/3/2015.
 */
public abstract class Position implements Serializable {
    protected abstract int getDistanceFrom(Position other);

}
