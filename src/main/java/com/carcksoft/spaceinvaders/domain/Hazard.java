package com.carcksoft.spaceinvaders.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Hazard {

    private Location location;
    private boolean active;
    private Targettability targettable;
    private boolean withinRoom;
    private int distanceToPlayer;

    public Hazard(int x, int y, boolean active, Targettability targettable, int distanceToPlayer, boolean withinRoom) {

        new Hazard(new Location(x, y), active, targettable, distanceToPlayer, withinRoom);
    }

    public Hazard(Location location, boolean active, Targettability targettable, int distanceToPlayer, boolean withinRoom) {
        this.location = location;
        this.active = active;
    }

    public enum Targettability {

        UP,
        DOWN,
        LEFT,
        RIGHT,
        NOPE
    }
}
