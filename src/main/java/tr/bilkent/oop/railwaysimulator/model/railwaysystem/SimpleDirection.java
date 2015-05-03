package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

/**
 * Created by saygin on 5/3/2015.
 */
public class SimpleDirection extends Direction {

    private boolean positive;

    protected SimpleDirection(boolean positive) {
        this.positive = positive;
    }

    protected boolean isPositive() {
        return positive;
    }
}
