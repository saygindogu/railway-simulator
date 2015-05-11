package tr.bilkent.oop.railwaysimulator.model;

import tr.bilkent.oop.railwaysimulator.model.RSim;

import java.io.*;

/**
 * Created by saygin on 5/11/2015.
 */
public class PersistentDataSystem {

    public String RSIM_DB_NAME = "RsimDatabase";
    RSim rSim;
    FileOutputStream fos;

    public PersistentDataSystem() throws IOException{
        rSim = RSim.getInstance();
        fos = new FileOutputStream( new File( RSIM_DB_NAME));
    }

    public void saveSystem() throws IOException {
        ObjectOutputStream obj_out = new ObjectOutputStream ( fos);

        // Write object out to disk
        obj_out.writeObject ( rSim );
    }

    public void loadSystem() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream( new File( RSIM_DB_NAME));
        ObjectInputStream ois = new ObjectInputStream( fis);
        rSim = (RSim) ois.readObject();
        RSim.setInstance( rSim);
    }

    public void saveRailwaySystem( )
}
