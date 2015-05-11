import org.junit.Test;
import tr.bilkent.oop.railwaysimulator.model.RSim;
import tr.bilkent.oop.railwaysimulator.model.PersistentDataSystem;
import tr.bilkent.oop.railwaysimulator.model.identity.Identity;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystem;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystemFacade;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Station;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Track;
import tr.bilkent.oop.railwaysimulator.model.user.User;

/**
 * Created by saygin on 5/11/2015.
 */
public class PersistencyTest {

    @Test
    public void persistencyTest() throws Exception {

        RSim.getInstance().addUser(new User("gizem", "gizem123"));
        RSim.getInstance().addUser(new User("saygin", "saygin456"));
        RSim.getInstance().addUser(new User("mevlut", "mevlut789"));

        PersistentDataSystem persistentDataSystem = new PersistentDataSystem();

        RailwaySystemFacade.getInstance().initilizeNewSystemFor(RSim.getInstance().getCurrentUser());
        Track track = RailwaySystemFacade.getInstance().addNewTrackToCurrentSystem();
        RailwaySystemFacade.getInstance().addStationTo( track, new Station("Ankara"));
        RailwaySystemFacade.getInstance().addStationTo( track, new Station( "Polatli"));
        RSim.getInstance().addUser(new User("elif", "elif91011"));
        persistentDataSystem.saveSystem();
        RSim.destroy();
        persistentDataSystem.loadSystem();
        for (User user : RSim.getInstance().getUserDatabase()) {
            System.out.println( user);
        }
        for (Identity identity : RSim.getInstance().getIdentityDatabase()) {
            System.out.println( identity);
        }


    }
}
