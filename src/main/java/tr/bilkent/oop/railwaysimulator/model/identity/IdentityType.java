package tr.bilkent.oop.railwaysimulator.model.identity;

public enum IdentityType{
    USER(0), STATION(1), SIMULATION(2), NONE(100);
    private int value;
    IdentityType(int value) {
        this.value = value;
    }

    public int minus(IdentityType type) {
        return  this.value - type.value;
    }
}
