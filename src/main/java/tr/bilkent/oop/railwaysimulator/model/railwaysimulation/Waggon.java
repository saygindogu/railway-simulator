package tr.bilkent.oop.railwaysimulator.model.railwaysimulation;

import java.io.Serializable;

/**
 * Created by saygin on 4/19/2015.
 *
 * Dynamic waggon objects are used during simulation.
 */
public class Waggon implements Serializable {
    public static final int DEFAULT_CAPACITY = 100;
    private int capacity;


    public Waggon() {

        this.capacity = DEFAULT_CAPACITY;
    }

    public Waggon(int capacity) {
        this.capacity = capacity;
    }

    public Waggon(Waggon waggon){
        this.capacity = waggon.getCapacity();
    }


    public  void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

}
