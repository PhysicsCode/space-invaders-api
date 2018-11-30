package com.privalia.mvc.basket.poc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.privalia.mvc.basket.poc.domain.Location;
import com.privalia.mvc.basket.poc.domain.LocationWithMeta;
import com.privalia.mvc.basket.poc.domain.Sizing;
import com.privalia.mvc.basket.poc.domain.VisibleArea;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SpaceInvadersInputDTO {

    @JsonProperty("game")
    private GameDTO gameDTO;

    @JsonProperty("player")
    private PlayerDTO playerDTO;

    @JsonProperty("board")
    private BoardDTO boardDTO;

    @JsonProperty("players")
    private List<Location> playersLocation = new ArrayList<>();

    @JsonProperty("invaders")
    private List<LocationWithMeta> invadersLocation = new ArrayList<>();


    @Data
    public static class GameDTO {

        @JsonProperty("id")
        private String gameId;
    }

    @Data
    public static class PlayerDTO {

        @JsonProperty("id")
        private String gameId;

        @JsonProperty("name")
        private String name;

        @JsonProperty("position")
        private Location position;

        @JsonProperty("previous")
        private Location previousPosition;

        @JsonProperty("area")
        private VisibleArea visibleArea;

        @JsonProperty("fire")
        private Boolean canFire;
    }

    @Data
    public static class BoardDTO {

        @JsonProperty("size")
        private Sizing gameId;

        @JsonProperty("walls")
        private List<Location> wallsLocation = new ArrayList<>();

    }
}
