import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.SimplePosition;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystem;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystemFacade;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Station;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Track;
import tr.bilkent.oop.railwaysimulator.model.simulation.SimpleSimulation;
import tr.bilkent.oop.railwaysimulator.model.simulation.SimulationState;
import tr.bilkent.oop.railwaysimulator.model.simulation.TrainDispacher;
import tr.bilkent.oop.railwaysimulator.model.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 5/6/2015.
 */
public class SimulationTest{

    String[] stationNames = { "Ankara", "Polatli", "Eskisehir", "Bursa", "Istanbul" };
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
            systemFacade.addStationTo( track, station);
        }
        systemFacade.addNewTimeTableTo(track, systemFacade.getFirstStationOn(track));
        systemFacade.addNewTrainTo(track, systemFacade.getFirstStationOn(track));
    }

    @Test
    public void getDispacherTest() throws Exception {
        List<TrainDispacher> dispachers = RailwaySystemFacade.getInstance().getDispachersOfTheSystem();
        Assert.assertTrue( dispachers.size() == 1);
        Assert.assertTrue(dispachers.get(0).getPosition().equals( systemFacade.getStationPositionOnTrack( track, stations.get(0)) ) );
    }

    @Test
    public void simulateTest() throws Exception {
        SimpleSimulation sim = SimpleSimulation.getInstance();
        Assert.assertTrue( sim != null );
        Assert.assertTrue( sim.getStateQueue().size() == 0 );
        sim.tick();
        Assert.assertTrue(sim.getStateQueue().size() == 1);
        sim.tick();



        //TODO check why there is a second train that is being dispach

        //TODO observe multiple trains' behaviors.
    }

    @Test
    public void bigSimulateTest() throws Exception {
        SimpleSimulation sim = SimpleSimulation.getInstance();
        sim.simulate();
        System.out.println( sim.getStateQueue().size() );
        int count = 0;
        for (SimulationState simulationState : sim.getStateQueue()) {
            System.out.println( simulationState );
            if( count++ > 1000) break;
        }
        //TODO observe the code output here. The train is going in right speed but it is not stopping at the stations.

    }

    @After
    public void tearDown() throws Exception {
        user = null;
        stations = null;
        track = null;
        systemFacade = null;
        SimpleSimulation.getInstance().destroy();
    }
}
