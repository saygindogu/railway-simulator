package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by saygin on 4/19/2015.
 */
public class Train {

    private  SimplePosition position;
    private Station destination;
    private int speed;
    private List<Waggon> waggons;
    private SimpleDirection direction;
    private SimpleTime departureTime; // departure time for the current rally
    private SimpleTimeTable timeTable;


    //TODO write constructor

    protected int getTotalCapacity(){
        //TODO implement
        throw new NotImplementedException();
    }

    protected int getNumberOfWaggons(){
        //TODO implement
        throw new NotImplementedException();
    }

    protected Direction getdirection(){
        //TODO implement
        throw new NotImplementedException();
    }

    protected SimpleTime getDepartureTime(){
        return departureTime;
    }

    protected SimpleTime getArrivaltime( Station station){
        //TODO implement
        throw new NotImplementedException();
    }

    protected SimpleTimeTable getTimeTable() {
        return timeTable;
    }
}
