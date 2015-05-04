package tr.bilkent.oop.railwaysimulator.model.railwaysystem;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 5/1/2015.
 */
public class DefaultTrainDispacher implements TrainDispacherImpl {

    List<Train> trains;

    public DefaultTrainDispacher(){
        trains = new ArrayList<Train>();
    }

    public void dispach() {
        //TODO... probably start timer here
    }

    public void addTrain( Train train) {
        trains.add( train);
    }
}
