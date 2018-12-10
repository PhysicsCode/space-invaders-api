package com.carcksoft.spaceinvaders.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationWithMeta extends Location{

    public LocationWithMeta(int x, int y) {
        super(x, y);
    }

    public LocationWithMeta(int x, int y, boolean neutral){
        super(x,y);
        this.neutral = neutral;
    }

    @JsonProperty
    private Boolean neutral;
}
