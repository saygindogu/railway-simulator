package tr.bilkent.oop.railwaysimulator.model.railwaysystem;


/**
 * Created by saygin on 5/3/2015.
 */
public abstract class AbstractTime implements Comparable<AbstractTime>{

    protected abstract long getTimestamp();
    protected long getIntervalBetween( AbstractTime t1, AbstractTime t2){
        return t2.getTimestamp() - t1.getTimestamp();
    }

    @Override
    public int compareTo(AbstractTime o) {
        return (int) ((this.getTimestamp() - o.getTimestamp()) % Integer.MAX_VALUE);
    }
}
