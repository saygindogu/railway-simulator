package tr.bilkent.oop.railwaysimulator.model.persistency;

import tr.bilkent.oop.railwaysimulator.model.RSim;

import java.io.*;

/**
 * Created by saygin on 5/11/2015.
 */
public class PersistentDataSystem {

    RSim rSim;
    FileOutputStream fos;

    public PersistentDataSystem() throws IOException{
        rSim = RSim.getInstance();
        fos = new FileOutputStream( new File( "RsimDatabase.db"));
    }

    public void saveSystem() throws IOException {
        ObjectOutputStream obj_out = new ObjectOutputStream ( fos);

        // Write object out to disk
        obj_out.writeObject ( rSim );
    }
}
