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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identity identity = (Identity) o;

        if (getId() != identity.getId()) return false;
        return getType() == identity.getType();

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getType().hashCode();
        return result;
    }
}

