package tr.bilkent.oop.railwaysimulator.model.railwaysystem;

/**
 * Created by saygin on 4/19/2015.
 */
public class AbtractTrainDispacher extends AbtractDispacher {

    private TrainDispacherImpl implementation;

    public AbtractTrainDispacher(){
        implementation = new DefaultTrainDispacher();

    }

    @Override
    public void dispach() {
        implementation.dispach();
    }
}
