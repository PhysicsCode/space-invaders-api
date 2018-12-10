package com.carcksoft.spaceinvaders.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.carcksoft.spaceinvaders.domain.Location;
import com.carcksoft.spaceinvaders.domain.LocationWithMeta;
import com.carcksoft.spaceinvaders.domain.Sizing;
import com.carcksoft.spaceinvaders.domain.VisibleArea;
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
        private Sizing size;

        @JsonProperty("walls")
        private List<Location> wallsLocation = new ArrayList<>();

    }
}
