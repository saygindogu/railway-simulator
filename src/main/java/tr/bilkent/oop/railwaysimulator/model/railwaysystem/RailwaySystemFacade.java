package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

/**
 * Created by saygin on 5/1/2015.
 */
public class RailwaySystemFacade {
    private static RailwaySystemFacade instance;

    private RailwaySystemFacade(){

    }

    public RailwaySystemFacade getInstance(){
        if( instance == null)
            instance = new RailwaySystemFacade();
        return instance;
    }
}
