package tr.bilkent.oop.railwaysimulator.model.identity;

import tr.bilkent.oop.railwaysimulator.model.RSim;

import java.util.TreeSet;

/**
 * Created by saygin on 5/1/2015.
 */
public class StationIdentityFactory implements IdentityFactory {

    private static StationIdentityFactory instance;

    public StationIdentityFactory(){}

    public Identity newIdentity() {
        RSim rsim = RSim.getInstance();

        TreeSet<Identity> set = (TreeSet<Identity>)rsim.getIdentityDatabase();
        Identity lastStationId = set.lower(new Identity(-1, IdentityType.SIMULATION ));
        if( lastStationId.getType() != IdentityType.STATION ){
            lastStationId = null;
        }
        Identity nextStationId;
        if( lastStationId != null){
            nextStationId = new Identity( lastStationId.getId()+1, IdentityType.STATION );
        }
        else nextStationId = new Identity( 0, IdentityType.STATION );
        rsim.getIdentityDatabase().add( nextStationId );
        return nextStationId;
    }

    public static IdentityFactory getInstance() {
        if( instance == null){
            instance = new StationIdentityFactory();
        }
        return instance;
    }
}
