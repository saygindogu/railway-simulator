package tr.bilkent.oop.railwaysimulator.model;

/**
 * Created by saygin on 4/19/2015.
 *
 * TODO change access..
 */
public class SimpleTime extends AbstractTime {
    public static AbstractTime OneDay = new SimpleTime( 86400000);
    public static AbstractTime OneHour = new SimpleTime( 360000);
    public static AbstractTime OneMinute = new SimpleTime( 60000);
    public static AbstractTime OneSecond = new SimpleTime( 1000);

    private long timestamp;

    protected SimpleTime( long timestamp){
        this.timestamp = timestamp;
    }

    @Override
    public AbstractTime getBirthOfCronus() {
        //TODO implement
        return null;
    }

    @Override
    protected long getTimestamp() {
        return timestamp;
    }
}
