package com.carcksoft.spaceinvaders.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hazard {

    private Location location;
    private boolean active;
    private boolean targettable;
    private boolean withinRoom;
    private int distanceToPlayer;

    public Hazard(int x, int y, boolean active, boolean targettable, int distanceToPlayer, boolean withinRoom) {

        new Hazard(new Location(x, y), active, targettable, distanceToPlayer, withinRoom);
    }

    public Hazard(Location location, boolean active, boolean targettable, int distanceToPlayer, boolean withinRoom) {
        this.location = location;
        this.active = active;
    }
}
