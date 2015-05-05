package tr.bilkent.oop.railwaysimulator.model.simulation;

import tr.bilkent.oop.railwaysimulator.model.TimeInterval;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Waggon;

/**
 * Created by saygin on 5/5/2015.
 */
public class DynamicWaggon implements DynamicObject {
    private Waggon waggon;
    private int currentLoad;

    public DynamicWaggon(Waggon waggon, int load) {
        this.waggon = waggon;
        currentLoad = load;
    }

    public void tick(TimeInterval dt) {
        //nothing
    }
}
