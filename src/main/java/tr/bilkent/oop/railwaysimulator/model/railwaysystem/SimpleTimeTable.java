package tr.bilkent.oop.railwaysimulator.model.railwaysystem;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 5/3/2015.
 */
public class SimpleTimeTable extends AbstractTimeTable {
    public static final int DEFAULT_REPETITION = 1000;
    private List<SimpleTime> times;
    private long period;
    private int last;

    protected SimpleTimeTable( List<SimpleTime> times){
        this.times = times;
        period = 0;
        last = times.size();
    }

    protected SimpleTimeTable( SimpleTime base, long period){
        times = new ArrayList<SimpleTime>(1);
        times.add(base);
        this.period = period;
        last = DEFAULT_REPETITION;
    }

    protected SimpleTimeTable( SimpleTime base, long period, int repetitionCount){
        times = new ArrayList<SimpleTime>(1);
        times.add(base);
        this.period = period;
        last = repetitionCount;
    }

    protected boolean isPeriodical(){
        return period > 0;
    }

    private SimpleTime baseTime(){
        return times.get(0);
    }

    private SimpleTime lastTime(){
        if( isPeriodical() ){
            return new SimpleTime( baseTime().getTimestamp() + last*period);
        }
        return times.get( last - 1 );
    }



    protected SimpleTime getNextTime( SimpleTime now){
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

    protected SimpleTime getPreviousTime( SimpleTime now){
        // TODO implement this
        throw new NotImplementedException();
    }

    protected SimpleTime getLastTime( SimpleTime now){
        return lastTime();
    }

    protected SimpleTime getFirstTime( SimpleTime now){
        return baseTime();
    }


}
