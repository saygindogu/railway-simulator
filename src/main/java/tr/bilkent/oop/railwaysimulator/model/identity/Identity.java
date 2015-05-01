package tr.bilkent.oop.railwaysimulator.model.identity;

/**
 * Created by saygin on 4/19/2015.
 */
public class Identity {
    private long id;
    private IdentityType type;

    public long getId() {
        return id;
    }

    public IdentityType getType() {
        return type;
    }

    public Identity( long id, IdentityType type) {
        this.id = id;
        this.type = type;
    }
}

