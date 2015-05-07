package tr.bilkent.oop.railwaysimulator.model.simulation;


import java.io.Serializable;

/**
 * Created by saygin on 5/3/2015.
 *
 *
 */
public abstract class AbstractTime implements Comparable<AbstractTime>, Serializable{

    public abstract long getTimestamp();

    public static long getIntervalBetween( AbstractTime t1, AbstractTime t2){
        return t2.getTimestamp() - t1.getTimestamp();
    }

    public int compareTo(AbstractTime o) {
        return (int) ((this.getTimestamp() - o.getTimestamp()) % Integer.MAX_VALUE);
        //TODO this probably returns >= in the case this:30000, this:86400000, investigate the problem.
    }

    public abstract long getTimeDistanceFrom(AbstractTime otherTime);

    @Override
    public String toString() {
        return "AbstractTime{"+ getTimestamp() + " }";
    }
}
