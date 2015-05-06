import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.SimplePosition;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.Waggon;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystem;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystemFacade;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Station;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Track;
import tr.bilkent.oop.railwaysimulator.model.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 5/6/2015.
 */
public class SimulationTest{

    String[] stationNames = { "Ankara", "Polatl?", "Eski?ehir", "Bursa", "?stanbul" };
    User user;
    List<Station> stations;
    RailwaySystem system;

    @Before
    public void setUp() throws Exception {
        user = new User( "tester", "1234" );
        RailwaySystemFacade systemFacade = RailwaySystemFacade.getInstance();
        systemFacade.setUser(user);
        systemFacade.createNewSystem();
        Track track = systemFacade.addNewTrackToCurrentSystem();
        systemFacade.getTracksOnSystem();
        stations = new ArrayList<Station>(5);
        for (String stationName : stationNames) {
            stations.add( new Station( stationName ) );
        }

        systemFacade.addStationTo( track, SimplePosition.ZERO, station);

    }

    @Test
    public void WaggonTest(){
        Waggon waggon = new Waggon();

        Assert.assertTrue(waggon.getCapacity() == Waggon.DEFAULT_CAPACITY);
        waggon.setCapacity(10);
        Assert.assertTrue(waggon.getCapacity() == 10);

    }

    @After
    public void tearDown() throws Exception {


    }
}
