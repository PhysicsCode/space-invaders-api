package com.carcksoft.spaceinvaders.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location {

    @JsonProperty("x")
    private Integer x;

    @JsonProperty("y")
    private Integer y;
}
