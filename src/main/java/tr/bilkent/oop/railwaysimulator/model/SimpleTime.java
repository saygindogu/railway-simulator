package tr.bilkent.oop.railwaysimulator.model;

/**
 * Created by saygin on 4/19/2015.
 *
 */
public class SimpleTime extends AbstractTime {
    public static final AbstractTime ONE_DAY = new SimpleTime( 86400000);
    public static final AbstractTime ONE_HOUR = new SimpleTime( 360000);
    public static final AbstractTime ONE_MINUTE = new SimpleTime( 60000);
    public static final AbstractTime ONE_SECOND = new SimpleTime( 1000);
    public static final AbstractTime BIRTH_OF_CRONUS = new SimpleTime( 0);

    private long timestamp;

    public SimpleTime( long timestamp){
        this.timestamp = timestamp;
    }


    @Override
    public long getTimestamp() {
        if( timestamp < 0)
            return Integer.MAX_VALUE; //infinite time
        return timestamp;
    }

    @Override
    public long getTimeDistanceFrom(AbstractTime otherTime) {
        return timestamp - otherTime.getTimestamp();
    }
}
