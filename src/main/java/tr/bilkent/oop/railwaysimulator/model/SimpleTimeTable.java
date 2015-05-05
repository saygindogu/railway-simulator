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

    protected SimpleTimeTable( AbstractTime base, AbstractTime period){
        times = new ArrayList<AbstractTime>(1);
        times.add(base);
        this.period = period.getTimestamp();
        last = DEFAULT_REPETITION;
    }

    protected SimpleTimeTable( AbstractTime base, long period, int repetitionCount){
        times = new ArrayList<AbstractTime>(1);
        times.add(base);
        this.period = period;
        last = repetitionCount;
    }

    protected boolean isPeriodical(){
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



    protected AbstractTime getNextTime( AbstractTime now){
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

    protected AbstractTime getPreviousTime( AbstractTime now){
        // TODO implement this
        throw new NotImplementedException();
    }

    protected AbstractTime getLastTime( AbstractTime now){
        return lastTime();
    }

    protected AbstractTime getFirstTime( AbstractTime now){
        return baseTime();
    }


}
