import org.junit.Test;
import tr.bilkent.oop.railwaysimulator.model.RSim;
import tr.bilkent.oop.railwaysimulator.model.PersistentDataSystem;
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
        RSim.getInstance().addUser( new User( "elif", "elif91011" ) );
        PersistentDataSystem persistentDataSystem = new PersistentDataSystem();
        persistentDataSystem.saveSystem();
        RSim.destroy();
        persistentDataSystem.loadSystem();
        for (User user : RSim.getInstance().getUserDatabase()) {
            System.out.println( user);
        }


    }
}
