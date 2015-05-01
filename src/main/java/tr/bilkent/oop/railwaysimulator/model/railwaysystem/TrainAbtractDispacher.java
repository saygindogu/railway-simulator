package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

/**
 * Created by saygin on 4/19/2015.
 */
public class TrainAbtractDispacher extends AbtractDispacher {

    private TrainDispacherImpl implementation;

    public TrainAbtractDispacher(){
        implementation = new DefaultTrainDispacher();

    }

    @Override
    public void dispach() {
        implementation.dispach();
    }
}
