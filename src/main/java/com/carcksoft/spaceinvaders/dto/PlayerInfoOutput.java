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
                "Ismael Rodríguez García",
                "ismael.rodriguez@ext.privalia.com");
    }
}
