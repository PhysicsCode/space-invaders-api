package com.carcksoft.spaceinvaders.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OutputInstructionDTO {

    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right"),
    F_UP("fire-up"),
    F_DOWN("fire-down"),
    F_LEFT("fire-left"),
    F_RIGHT("fire-right");

    @JsonProperty
    private final String move;

    OutputInstructionDTO(String move) {
        this.move = move;
    }
}
