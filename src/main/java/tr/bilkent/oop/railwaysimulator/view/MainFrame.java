package tr.bilkent.oop.railwaysimulator.view;

import javax.swing.*;

/**
 * Created by saygin on 5/1/2015.
 */
public class MainFrame extends JFrame {

    public MainFrame(){
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add( new RailwaySystemView() );
    }
}
