package tr.bilkent.oop.railwaysimulator.view;


import tr.bilkent.oop.railwaysimulator.model.Observable;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystem;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystemFacade;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Track;

import javax.swing.*;

/**
 * Created by saygin on 5/1/2015.
 */
public class RailwaySystemView extends JPanel implements Observer
{

    RailwaySystem system;
    JComboBox<Track> trackChose;
    JPanel trackView;

    public RailwaySystemView(){
        super();
        system = RailwaySystemFacade.getInstance().getCurrentSystem();
        trackChose = new JComboBox<Track>( (Track[])RailwaySystemFacade.getInstance().getTracksOnSystem().toArray() );
        Track selected = RailwaySystemFacade.getInstance().getTracksOnSystem().get( trackChose.getSelectedIndex() );
        trackView = new TrackView( selected);
        add( trackChose);
        add( trackView);
    }

    public void update(Observable observable) {
        system = (RailwaySystem) observable;
        Track selected = RailwaySystemFacade.getInstance().getTracksOnSystem().get( trackChose.getSelectedIndex() );

    }
}
