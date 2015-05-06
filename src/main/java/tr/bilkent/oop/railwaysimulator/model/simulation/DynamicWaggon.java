package tr.bilkent.oop.railwaysimulator.model.simulation;

import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Waggon;

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

    public DynamicWaggon(DynamicWaggon waggon) {
        this.waggon = waggon.waggon;
        currentLoad = waggon.currentLoad;
    }

    public void tick(TimeInterval dt) {
        //nothing, waggon changes is not supported yet.
    }
}
