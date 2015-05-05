package tr.bilkent.oop.railwaysimulator.model;

import javafx.util.Pair;

import java.sql.Time;

/**
 * Created by saygin on 5/5/2015.
 */
public class TimeInterval {
    private Pair<AbstractTime, AbstractTime> interval;

    public TimeInterval( AbstractTime t1, AbstractTime t2){
        interval = new Pair<AbstractTime, AbstractTime>( t1, t2);
    }
    public TimeInterval truncateFromBeggining( long time){
        if( time < interval.getValue().getTimeDistanceFrom(interval.getKey())  ){
            return new TimeInterval( new SimpleTime( interval.getKey().getTimestamp() + time), interval.getValue() );
        }
        else return new TimeInterval( interval.getValue(), interval.getValue() );
    }
}
