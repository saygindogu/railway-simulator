package tr.bilkent.oop.railwaysimulator.model.railwaysystem;


import tr.bilkent.oop.railwaysimulator.model.UnknownComparisonException;

/**
 * Created by saygin on 5/1/2015.
 */
public class SimplePosition extends Position {
    protected int distance;

    public SimplePosition( int distance){
        this.distance = distance;
    }

    @Override
    public int getDistanceFrom(Position other) {
        if( other instanceof SimplePosition){
            return getDistanceFrom( other);
        }
        else throw new UnknownComparisonException();
    }

    protected int getDistanceFrom( SimplePosition other){
        return Math.abs( other.distance - distance);
    }

    public int getDistance() {
        return distance;
    }

    public int compareTo(Position p) {
        //TODO casting check
        return distance - ((SimplePosition)p).getDistance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimplePosition that = (SimplePosition) o;

        return getDistance() == that.getDistance();
    }

    @Override
    public int hashCode() {
        return getDistance();
    }
}
