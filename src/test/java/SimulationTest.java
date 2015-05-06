import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.SimplePosition;
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

    @Before
    public void setUp() throws Exception {
        user = new User( "tester", "1234" );
        RailwaySystemFacade systemFacade = RailwaySystemFacade.getInstance();
        systemFacade.initilizeNewSystemFor(user);
        systemFacade.createNewSystem();
        Track track = systemFacade.addNewTrackToCurrentSystem();
        systemFacade.getTracksOnSystem();
        stations = new ArrayList<Station>(5);
        for (String stationName : stationNames) {
            stations.add( new Station( stationName ) );
        }
        for (Station station : stations) {
            systemFacade.addStationTo( track, SimplePosition.ZERO, station);
        }
        systemFacade.addNewTimeTableTo( track, systemFacade.getFirstStationOn(track) );
        systemFacade.addNewTrainTo( track, systemFacade.getFirstStationOn(track) );
    }

    @Test
    public void getDispacherTest() throws Exception {
        Assert.assertTrue(RailwaySystemFacade.getInstance().getDispachersOfTheSystem().size() == 1);
    }



}
