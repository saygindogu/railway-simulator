package tr.bilkent.oop.railwaysimulator.model.railwaysimulation;


import tr.bilkent.oop.railwaysimulator.model.exception.UnknownComparisonException;

/**
 * Created by saygin on 5/1/2015.
 */
public class SimplePosition implements Position {
    public static final float EPSILON = 0.0001f;

    protected float distance;

    public SimplePosition( float distance){
        this.distance = distance;
    }

    public float getDistanceFrom(Position other) {
        if( other instanceof SimplePosition){
            return getDistanceFrom( other);
        }
        else throw new UnknownComparisonException();
    }

    protected float getDistanceFrom( SimplePosition other){
        return Math.abs( other.distance - distance);
    }

    public float getDistance() {
        return distance;
    }

    public int compareTo(Position p) {
        //TODO casting check
        return Math.round( Math.signum( distance - ((SimplePosition)p).getDistance() ) );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimplePosition that = (SimplePosition) o;

        return Math.abs(getDistance() - that.getDistance()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return (getDistance() != +0.0f ? Float.floatToIntBits(getDistance()) : 0);
    }
}
