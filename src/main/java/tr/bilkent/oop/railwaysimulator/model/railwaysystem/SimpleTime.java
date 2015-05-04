package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

/**
 * Created by saygin on 4/19/2015.
 */
public class SimpleTime extends AbstractTime {
    public static AbstractTime OneHour = new SimpleTime( 360000);
    public static AbstractTime OneMinute = new SimpleTime( 60000);
    public static AbstractTime OneSecond = new SimpleTime( 1000);

    private long timestamp;

    protected SimpleTime( long timestamp){
        this.timestamp = timestamp;
    }

    @Override
    protected long getTimestamp() {
        return timestamp;
    }
}
