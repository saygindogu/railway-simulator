package tr.bilkent.oop.railwaysimulator.model;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 5/3/2015.
 *
 * This class is highly coupled with SimpleTime, all AbstractTime objects should be of type SimpleTime.
 * It might not be safe to use other instances of AbstractTime.
 */
public class SimpleTimeTable extends AbstractTimeTable {
    public static final int DEFAULT_REPETITION = 1000;
    private List<AbstractTime> times;
    private long period;
    private int last;

    protected SimpleTimeTable( List<AbstractTime> times){
        this.times = times;
        period = 0;
        last = times.size();
    }

    public SimpleTimeTable( AbstractTime base, AbstractTime period){
        times = new ArrayList<AbstractTime>(1);
        times.add(base);
        this.period = period.getTimestamp();
        last = DEFAULT_REPETITION;
    }

    public SimpleTimeTable( AbstractTime base, long period, int repetitionCount){
        times = new ArrayList<AbstractTime>(1);
        times.add(base);
        this.period = period;
        last = repetitionCount;
    }

    public SimpleTimeTable() {
        times = new ArrayList<AbstractTime>(1);
        times.add( SimpleTime.BIRTH_OF_CRONUS );
        this.period = SimpleTime.ONE_HOUR.getTimestamp();
        last = DEFAULT_REPETITION;
    }

    public boolean isPeriodical(){
        return period > 0;
    }

    private AbstractTime baseTime(){
        return times.get(0);
    }

    private AbstractTime lastTime(){
        if( isPeriodical() ){
            return new SimpleTime( baseTime().getTimestamp() + last*period);
        }
        return times.get( last - 1 );
    }



    public AbstractTime getNextTime( AbstractTime now){
        if( now.compareTo( baseTime() ) > 0 ){
            if( now.compareTo( lastTime() ) <= 0 ) {
                if (isPeriodical()) {
                    int nextRepetition = (int) ((now.getTimestamp() - baseTime().getTimestamp()) / period);
                    return new SimpleTime(baseTime().getTimestamp() + nextRepetition * period);
                }
                else{
                    int nextIndex = 0;
                    while( now.compareTo( times.get(nextIndex)) > 0 && nextIndex < times.size() )
                        nextIndex++;
                    return times.get( nextIndex);
                }
            }
            else return new SimpleTime(-1);
        }
        else return  baseTime();
    }

    public AbstractTime getPreviousTime( AbstractTime now){
        // TODO implement this
        throw new NotImplementedException();
    }

    public AbstractTime getLastTime( AbstractTime now){
        return lastTime();
    }

    public AbstractTime getFirstTime( AbstractTime now){
        return baseTime();
    }


}
