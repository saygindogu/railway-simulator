package tr.bilkent.oop.railwaysimulator;

import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystemFacade;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Station;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Track;
import tr.bilkent.oop.railwaysimulator.model.user.User;
import tr.bilkent.oop.railwaysimulator.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saygin on 16.2.2015.
 * 
 *  Dummy Main class. To make an initial commit to respitory. 
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World. I'm a railway simulator.");
        String[] stationNames = { "Ankara", "Polatli", "Eskisehir", "Bursa", "Istanbul" };
        User user;
        List<Station> stations;
        Track track;
        RailwaySystemFacade systemFacade;
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
        JFrame frame = new MainFrame();
        frame.pack();
        frame.setVisible( true);
    }

}
