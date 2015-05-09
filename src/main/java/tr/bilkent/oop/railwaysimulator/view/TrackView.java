package tr.bilkent.oop.railwaysimulator.view;

import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Track;

import javax.swing.*;

/**
 * Created by saygin on 5/9/2015.
 */
public class TrackView extends JPanel {

    private JTextArea area;

    public TrackView(Track selected) {
        super();
        area = new JTextArea( selected.toString() );
    }
}
