package com.carcksoft.spaceinvaders.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationWithMeta extends Location{

    public LocationWithMeta(int x, int y) {
        super(x, y);
    }

    @JsonProperty
    private Boolean neutral;
}
