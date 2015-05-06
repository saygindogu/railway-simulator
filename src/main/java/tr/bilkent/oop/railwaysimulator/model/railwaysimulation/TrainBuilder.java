package tr.bilkent.oop.railwaysimulator.model.railwaysimulation;


import tr.bilkent.oop.railwaysimulator.model.simulation.AbstractTimeTable;
import tr.bilkent.oop.railwaysimulator.model.simulation.SimpleTime;
import tr.bilkent.oop.railwaysimulator.model.simulation.SimpleTimeTable;
import tr.bilkent.oop.railwaysimulator.model.railwaysystem.Station;
import tr.bilkent.oop.railwaysimulator.model.simulation.Direction;
import tr.bilkent.oop.railwaysimulator.model.simulation.SimpleDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saygin on 5/4/2015.
 *
 * Builder Class for Train Complex object.
 * //
 */
public class TrainBuilder {
    private Station destination;
    private int speed;
    private Waggon prototype;
    private Direction initialDirection;
    private AbstractTimeTable timeTable;
    private int waggonCount;

    public TrainBuilder() {
        destination = null;
        speed = Train.DEFAULT_SPEED;
        this.prototype = Waggon.DEFAULT;
        initialDirection = new SimpleDirection( true);
        timeTable = new SimpleTimeTable( new SimpleTime(0), SimpleTime.ONE_HOUR);
        waggonCount = Train.DEFAULT_WAGGON_COUNT;
    }

    public TrainBuilder withDestination( Station station){
        this.destination = station;
        return this;
    }

    public TrainBuilder withSpeed( int speed){
        if( speed < 0 && speed > Train.MAX_SPEED ){
            this.speed = speed;
            return this;
        }
        else throw new IllegalArgumentException( "Train speed must be " + Train.MAX_SPEED + "  > speed > 0\n" );
    }

    public TrainBuilder withWaggonPrototype( Waggon waggon){
        this.prototype = waggon;
        return this;
    }

    public TrainBuilder withInitialDirection( Direction d){
        this.initialDirection = d;
        return this;
    }

    public TrainBuilder withWaggonCount( int count){
        waggonCount = count;
        return  this;
    }

    public TrainBuilder withTimeTable( AbstractTimeTable timeTable){
        this.timeTable = timeTable;
        return this;
    }

    public Train build(){
        Train instance = new Train();
        List<Waggon> waggons = new ArrayList<Waggon>( waggonCount);
        for( int i = 0; i < waggonCount; i++){
            waggons.add( new Waggon( prototype) );
        }
        instance.setWaggons( waggons);
        instance.setDestination(destination);
        instance.setSpeed(speed);
        instance.setInitialDirection(initialDirection);
        instance.setTimeTable( timeTable);
        return instance;
    }
}
