package com.carcksoft.spaceinvaders.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerInfoOutput {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    public static PlayerInfoOutput defaultPojo() {

        return new PlayerInfoOutput(
                "El ******** del mando de la xbox",
                "ismael.rodriguez@ext.privalia.com");
    }
}
