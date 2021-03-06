package com.carcksoft.spaceinvaders.domain;

import com.carcksoft.spaceinvaders.domain.constants.LayoutEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Game {

    private final String gameId;
    private LayoutEntity[][] board;
    private Set<Hazard> phantomSet;
    private Set<Hazard> enemyPlayerSet;
    private Location playerLocation;

    public Game(String gameId, Sizing size) {

        this.gameId = gameId;
        this.board = startBoard(size.getHeight(), size.getWidth());
        this.phantomSet = new HashSet<>();
        this.enemyPlayerSet = new HashSet<>();
    }

    //------------------- BOARD CREATION -------------------

    private LayoutEntity[][] startBoard(int height, int width) {

        LayoutEntity[][] array = new LayoutEntity[height][width];

        Arrays.fill(array[0], LayoutEntity.WALL);

        for (int i = 1; i < height - 1; i++) {

            Arrays.fill(array[i], LayoutEntity.UNKNOWN);
            array[i][0] = LayoutEntity.WALL;
            array[i][width - 1] = LayoutEntity.WALL;
        }

        Arrays.fill(array[height - 1], LayoutEntity.WALL);

        return array;
    }

    //------------------- BOARD MANIPULATION -------------------

    public void upsertMapEntity(LayoutEntity identity, Location location) {

        board[location.getY()][location.getX()] = identity;
    }

    public void deleteMapEntity(Location location) {
        board[location.getY()][location.getX()] = LayoutEntity.EMPTY;
    }

    public void upsertWall(Location location) {
        board[location.getY()][location.getX()] = LayoutEntity.WALL;
    }

    public void destroyHazardEntities() {

        phantomSet.clear();
        enemyPlayerSet.clear();
    }

    public void fillBoardVoids(VisibleArea visibleArea) {

        for (int j = visibleArea.getStartY(); j < visibleArea.getEndY()+1; j++) {
            for (int i = visibleArea.getStartX(); i < visibleArea.getEndX()+1; i++) {

                if (board[j][i].equals(LayoutEntity.UNKNOWN)) {
                    board[j][i] = LayoutEntity.EMPTY;
                }
            }
        }
    }

    // ------------------- ENTITY ALLOCATION -------------------
    public void relocatePhantom(List<LocationWithMeta> phantomLocation) {

        phantomLocation.forEach(locationWithMeta -> {

            Hazard phantomHazard = new Hazard(locationWithMeta, locationWithMeta.getNeutral(), calculateTargettability(locationWithMeta), calculateDistance(locationWithMeta), calculateWithinRoom(locationWithMeta));

            phantomSet.add(phantomHazard);
        });
    }

    public void relocatePlayers(List<Location> enemyPlayerLocations) {

        enemyPlayerLocations.forEach(location -> {

            Hazard playerHazard = new Hazard(location, false, calculateTargettability(location), calculateDistance(location), calculateWithinRoom(location));
            phantomSet.add(playerHazard);
        });
    }

    //------------------- DDD ENTITY CALCULATIONS -------------------
    public Hazard.Targettability calculateTargettability(Location location) {

        int playerX = playerLocation.getX();
        int playerY = playerLocation.getY();

        int x = location.getX();
        int y = location.getY();

        int signX = Integer.signum(playerX - x);
        int signY = Integer.signum(playerY - y);

        if (signX == 0 || signY == 0) {

            for (;x != playerX ^ y != playerY;) {

                x = x + signX;
                y = y + signY;

                if (board[y][x].equals(LayoutEntity.WALL)) {
                    return Hazard.Targettability.NOPE;
                }
            }

            if (signX == 0 && signY == -1){
                return Hazard.Targettability.DOWN;
            }

            if (signX == -1 && signY == 0){
                return Hazard.Targettability.RIGHT;
            }

            if (signX == 0 && signY == 1){
                return Hazard.Targettability.UP;
            }

            if (signX == 1 && signY == 0){
                return Hazard.Targettability.LEFT;
            }
        }

        return Hazard.Targettability.NOPE;
    }

    private boolean calculateWithinRoom(Location location) {

        //TODO complete code ?¿

        return true;
    }

    private int calculateDistance(Location location) {

        return Math.abs(location.getX() - playerLocation.getX()) + Math.abs(location.getY() - playerLocation.getY());
    }
}
