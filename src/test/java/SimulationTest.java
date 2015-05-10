import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tr.bilkent.oop.railwaysimulator.model.railwaysimulation.SimplePosition;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystem;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystemFacade;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Station;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Track;
import tr.bilkent.oop.railwaysimulator.model.simulation.*;
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
        systemFacade.addNewTrainTo(track, systemFacade.getFirstStationOn(track));
    }

    @Test
    public void multipleDispacherTest() throws Exception {
        systemFacade.addNewTimeTableTo(track, systemFacade.getLastStationOn(track));
        systemFacade.addNewTrainTo(track, systemFacade.getLastStationOn(track), new SimpleDirection(false) );
        systemFacade.addNewTrainTo(track, systemFacade.getLastStationOn(track), new SimpleDirection(false) );

        List<TrainDispacher> dispachers = RailwaySystemFacade.getInstance().getDispachersOfTheSystem();
        Assert.assertTrue( dispachers.size() == 2);
        Assert.assertTrue(dispachers.get(0).getPosition().equals(systemFacade.getStationPositionOnTrack(track, stations.get(0))));
        Assert.assertTrue( dispachers.get(1).getPosition().equals(systemFacade.getStationPositionOnTrack(track, stations.get(4))));

        SimpleSimulation sim = SimpleSimulation.getInstance();
        sim.simulate();
        int count = 0;
        for (SimulationState simulationState : sim.getStateQueue()) {
            if( count++ > -1) break;
            System.out.println( simulationState);
        }
    }

    @Test
    public void customSimulationTest() throws Exception {
        SimpleSimulation sim = SimpleSimulation.getInstance();
        sim.setTimeInterval( new SimpleTime( 10000));
        System.out.println( "hey!");
        sim.simulate();
        int count = 0;
        for (SimulationState simulationState : sim.getStateQueue()) {
            if( count++ > 1000) break;
            System.out.println( simulationState);
        }

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
    }

    @Test
    public void bigSimulateTest() throws Exception {
        SimpleSimulation sim = SimpleSimulation.getInstance();
        sim.simulate();
        int count = 0;
        for (SimulationState simulationState : sim.getStateQueue()) {
            if( count++ > -1) break;
            System.out.println( simulationState);
        }
    }

    @After
    public void tearDown() throws Exception {
        user = null;
        stations = null;
        track = null;
        RailwaySystemFacade.destroy();
        SimpleSimulation.destroy();
    }
}
