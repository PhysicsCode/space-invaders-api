package com.carcksoft.spaceinvaders.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisibleArea {

    @JsonProperty("x1")
    private Integer startX;

    @JsonProperty("x2")
    private Integer endX;

    @JsonProperty("y1")
    private Integer startY;

    @JsonProperty("y2")
    private Integer endY;
}
