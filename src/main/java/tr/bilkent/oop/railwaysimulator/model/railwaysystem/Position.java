package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

/**
 * Created by saygin on 5/3/2015.
 */
public abstract class Position {
    protected abstract int getDistanceFrom(Position other);
}