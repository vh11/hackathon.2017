package org.hackathon.common.model;

/**
 * Created by vh on 11/18/17.
 */

public class Entity {

    public static final int NO_ID = -1;

    private int id;

    public Entity(int id) {
        this.id = id;
    }

    public Entity() {
        this(NO_ID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void deserialize(String serialized) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;

        Entity entity = (Entity) o;

        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
