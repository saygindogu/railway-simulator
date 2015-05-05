package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.List;

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


    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    protected int getCapacity() {
        return capacity;
    }

}
