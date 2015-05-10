package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import java.io.Serializable;

/**
 * Created by saygin on 5/4/2015.
 */
public class RailwayPermissions implements Serializable {
    public static final int GROUP = 0;
    public static final int OTHERS = 1;
    public static final int OPEN = 0;
    public static final int MODIFY = 1;
    public static final int SIMULATE = 2;

    /*
    * Permission is specified like this:
    *  group[OPEN] states if group members can open the system.
    *  group[MODIFY] states if group members can modify the system.
    *  group[SIMULATE] states if grorup members can simulate the system.
    *
    *  note that owner has infinite permissions.
    *
    *  TODO add relevant methods and implement them
     */
    private boolean[] group;
    private boolean[] others;

    public RailwayPermissions( boolean[][] permissionMatrix ){

        //TODO copy these walues instead of using references to get more secure...

        for(int i = 0; i < group.length; i++){
            group[i] = permissionMatrix[0][i];
        }

        for(int i = 0; i < others.length; i++){
            others[i] = permissionMatrix[1][i];
        }
    }

    public RailwayPermissions(){
        group = new boolean[3];
        others = new boolean[3];

        for( int i = 0; i < group.length; i++){
            group[i] = false;
            others[i] = false;
        }
    }

    public boolean[] getGroupPermissions() {
        return group;
    }

    public boolean[] getOthersPermissions() {
        return others;
    }
}
