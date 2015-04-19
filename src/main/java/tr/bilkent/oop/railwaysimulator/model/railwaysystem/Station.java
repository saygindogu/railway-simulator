package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import tr.bilkent.oop.railwaysimulator.model.Identity;

import javax.swing.text.Position;
import java.io.Serializable;
import java.util.List;

/**
 * Created by saygin on 4/19/2015.
 */
public class Station implements Serializable {
    private Identity identity;
    private String name;
    private transient List<Track> tracks;
    private List<Position> positions;

}
