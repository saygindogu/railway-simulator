package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import org.junit.Assert;
import org.junit.Test;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Waggon;

/**
 * Created by saygin on 5/4/2015.
 */

public class RailwaySystemTest {

    @Test
    public void WaggonTest(){
        Waggon waggon = new Waggon();

        Assert.assertTrue( waggon.getCapacity() == Waggon.DEFAULT_CAPACITY );
        waggon.setCapacity(10);
        Assert.assertTrue(waggon.getCapacity() == 10);

    }
}
