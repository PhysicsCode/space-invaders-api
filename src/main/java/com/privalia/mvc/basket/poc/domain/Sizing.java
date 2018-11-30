package com.privalia.mvc.basket.poc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Sizing {

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("width")
    private Integer width;
}
