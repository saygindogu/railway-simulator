package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

/**
 * Created by saygin on 4/19/2015.
 */
public class Waggon {
    private int capacity;

    private Waggon(int capacity) {

        this.capacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {

        return capacity;
    }
}
