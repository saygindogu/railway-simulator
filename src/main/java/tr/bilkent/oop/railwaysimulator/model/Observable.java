package tr.bilkent.oop.railwaysimulator.model;

import tr.bilkent.oop.railwaysimulator.view.Observer;

/**
 * Created by saygin on 5/9/2015.
 */
public interface Observable {
     void notifyChanges();
     void subscribe( Observer observer);
     void unsubscribe( Observer observer);
}
