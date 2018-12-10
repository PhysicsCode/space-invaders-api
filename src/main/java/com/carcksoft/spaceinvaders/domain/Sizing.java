package com.carcksoft.spaceinvaders.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sizing {

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("width")
    private Integer width;
}
