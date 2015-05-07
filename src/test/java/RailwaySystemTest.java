package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.*;
import tr.bilkent.oop.railwaysimulator.model.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 5/4/2015.
 */

public class RailwaySystemTest {

    String[] stationNames = { "Ankara", "Polatl?", "Eski?ehir", "Bursa", "?stanbul" };
    User user;
    List<Station> stations;
    Track track;
    RailwaySystemFacade systemFacade;

    @Before
    public void setUp() throws Exception {
        user = new User( "tester", "1234" );

        systemFacade = RailwaySystemFacade.getInstance();
        systemFacade.initilizeNewSystemFor(user);
        systemFacade.createNewSystem();
        track = systemFacade.addNewTrackToCurrentSystem();
        systemFacade.getTracksOnSystem();
        stations = new ArrayList<Station>(5);
        for (String stationName : stationNames) {
            stations.add( new Station( stationName ) );
        }
        for (Station station : stations) {
            systemFacade.addStationTo( track, station, SimplePosition.ZERO);
        }
        systemFacade.addNewTimeTableTo(track, systemFacade.getFirstStationOn(track));
    }

    @Test
    public void getTracksTest() throws Exception {
        Assert.assertTrue(systemFacade.getTracksOnSystem().size() == 1);
    }

    @Test
    public void addTrainTest() throws Exception {
        systemFacade.addNewTrainTo(track, systemFacade.getFirstStationOn(track));
        Station s = systemFacade.getFirstStationOn( track);
        systemFacade.addNewTrainTo( track, s);
        Assert.assertTrue(s.getTrainQueueOn(track).size() == 1);
        systemFacade.addNewTrainTo( track, s);
        Assert.assertTrue(s.getTrainQueueOn(track).size() == 2);
    }

    @Test
    public void firstLastStationTest() throws Exception {
        Assert.assertTrue( systemFacade.getFirstStationOn(track).equals( stations.get(0)) );
        Assert.assertTrue( systemFacade.getLastStationOn(track).equals( stations.get(stations.size()-1)) );
    }

    @Test
    public void WaggonTest(){
        Waggon waggon = new Waggon();

        Assert.assertTrue( waggon.getCapacity() == Waggon.DEFAULT_CAPACITY );
        waggon.setCapacity(10);
        Assert.assertTrue(waggon.getCapacity() == 10);

    }

    @Test
    public void getStationPositionsTest() throws Exception {
        for (Station station : stations) {
            Position pos = RailwaySystemFacade.getInstance().getStationPositionOnTrack( track, station);
            Assert.assertTrue(pos.equals(station.getPositionOn(track)));
        }
    }

    @After
    public void tearDown() throws Exception {
        systemFacade.logout();
    }
}
