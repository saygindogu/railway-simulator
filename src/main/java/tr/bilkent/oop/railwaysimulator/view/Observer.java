package tr.bilkent.oop.railwaysimulator.view;

import tr.bilkent.oop.railwaysimulator.model.Observable;

/**
 * Created by saygin on 5/9/2015.
 */
public interface Observer {
    public void update( Observable observable);
}
