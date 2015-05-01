package tr.bilkent.oop.railwaysimulator.model.identity;

import tr.bilkent.oop.railwaysimulator.model.UnknownComparisonException;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

/**
 * Created by saygin on 5/1/2015.
 */
public class IdentityComparator implements java.util.Comparator{

    @Override
    public int compare(Object o1, Object o2){
        Identity i1, i2;
        i1 = (Identity) o1;
        i2 = (Identity) o2;

        if( i1.getType() == i2.getType() ){
            return (int) ( i1.getId() - i2.getId() % Integer.MAX_VALUE );
        }
        else return i1.getType().minus( i2.getType() );
    }
}
