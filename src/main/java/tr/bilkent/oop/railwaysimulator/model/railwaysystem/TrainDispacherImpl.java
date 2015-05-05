package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

/**
 * Created by saygin on 5/1/2015.
 */
public interface TrainDispacherImpl {

    //TODO add a dispach queue somewhere...
    // TODO move time table here... ( from trains)
    void dispach();
    void addTrain( Train train);
}
