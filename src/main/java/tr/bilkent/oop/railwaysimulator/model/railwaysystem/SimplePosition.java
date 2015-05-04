package tr.bilkent.oop.railwaysimulator.model.railwaysystem;


import tr.bilkent.oop.railwaysimulator.model.UnknownComparisonException;

/**
 * Created by saygin on 5/1/2015.
 */
public class SimplePosition extends Position {
    protected int distance;

    @Override
    protected int getDistanceFrom(Position other) {
        if( other instanceof SimplePosition){
            return getDistanceFrom( other);
        }
        else throw new UnknownComparisonException();
    }

    protected int getDistanceFrom( SimplePosition other){
        return Math.abs( other.distance - distance);
    }
}
