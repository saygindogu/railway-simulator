package tr.bilkent.oop.railwaysimulator.model.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by saygin on 5/5/2015.
 */
public class SimpleSimulationState implements SimulationState {

    private transient AbstractTime currentTime;
    private transient List< DynamicTrain > dynamicTrains;
    private transient List< TrainDispacher > dispachers;

    public SimpleSimulationState( AbstractTime currentTime, List<DynamicTrain> trains, List<TrainDispacher> dispachers  ){
        this.currentTime = currentTime;
        this.dynamicTrains = new ArrayList<DynamicTrain>( trains.size() );
        for (DynamicTrain train : trains) {
            this.dynamicTrains.add( new DynamicTrain( train )); //Keep a copy to not let changes in the state.
        }

        this.dispachers = new ArrayList<TrainDispacher>( dispachers.size() );
        for (TrainDispacher trainDispacher : dispachers) {
            this.dispachers.add( new DefaultTrainDispacher( (DefaultTrainDispacher) trainDispacher ));
        }
    }

}
