package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

/**
 * Created by saygin on 4/19/2015.
 */
public class SimpleTime extends AbstractTime {
    //TODO think about this. Maybe arrange weekly time stuff for schedules, or introduce schedule object..
    private long timestamp;

    protected SimpleTime( long timestamp){
        this.timestamp = timestamp;
    }

    @Override
    protected long getTimestamp() {
        return timestamp;
    }
}
