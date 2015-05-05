package tr.bilkent.oop.railwaysimulator.model.simulation;

import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Position;

/**
 * Created by saygin on 5/3/2015.
 */
public class SimpleDirection extends Direction {

    private boolean positive;

    public SimpleDirection(boolean positive) {
        this.positive = positive;
    }

    public boolean isPositive() {
        return positive;
    }

    protected int getCoefficent(){
        if( positive) return 1;
        else return -1;
    }

    public static SimpleDirection getDirection(Position currentPosition, Position newPosition) {
        boolean direction = currentPosition.compareTo( newPosition) < 0 ;
        return new SimpleDirection( direction);
    }
}
