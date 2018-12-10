package com.carcksoft.spaceinvaders.service;

import com.carcksoft.spaceinvaders.domain.Game;
import com.carcksoft.spaceinvaders.domain.Hazard;
import com.carcksoft.spaceinvaders.domain.Location;
import com.carcksoft.spaceinvaders.domain.constants.LayoutEntity;
import com.carcksoft.spaceinvaders.domain.constants.StuffInContact;
import com.carcksoft.spaceinvaders.dto.OutputInstructionDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class StrategyService {

    public OutputInstructionDTO defineMoveByGame(Game game, boolean mayIShoot) {

        //Player on sight
        Set<Hazard> playersOnSight = game.getEnemyPlayerSet().stream()
                                .filter(hazard -> !hazard.getTargettable().equals(Hazard.Targettability.NOPE))
                                .collect(Collectors.toCollection(HashSet::new));

        Set<Hazard> phantomsOnSight = game.getPhantomSet().stream()
                                .filter(hazard -> !hazard.getTargettable().equals(Hazard.Targettability.NOPE))
                                .collect(Collectors.toCollection(HashSet::new));

        Map<Hazard, Hazard.Targettability> phantomsTouching = phantomsOnSight.stream()
                                .filter(hazard -> hazard.getDistanceToPlayer() == 1)
                                .collect(Collectors.toMap(hazard -> hazard, Hazard::getTargettable));

        Map<Hazard, Hazard.Targettability> playersTouching = playersOnSight.stream()
                                .filter(hazard -> hazard.getDistanceToPlayer() == 1)
                                .collect(Collectors.toMap(hazard -> hazard, Hazard::getTargettable));


        Map<Hazard.Targettability, StuffInContact> contactMap = fillContactMapWithLayout(game.getBoard(), game.getPlayerLocation());

        fillContactMapWithEntities(contactMap, phantomsTouching, playersTouching);


        //I can shoot
        if (mayIShoot) {

            //players on sight
            if (!playersOnSight.isEmpty()) {

                // only 1
                if (playersOnSight.size() == 1) {

                    //shoot him
                    return targettableToShootInstruction(playersOnSight.stream().findFirst().get().getTargettable());

                // more than 1 in line
                } else if (playersInLine(playersOnSight))  {

                    //phantom or wall blocking 1 direction or else everything blocked

                    Hazard.Targettability target = calculateFreeDirectionToRun(contactMap);

                    if (target != null) {
                        return targettableToMoveInstruction(target);
                    } else {
                        return kebabCase(phantomsOnSight, playersOnSight);
                    }

                // more than 1 not in line
                } else {

                    //fuck it, shoot in the max points direction
                    return kebabCase(phantomsOnSight, playersOnSight);
                }

            //ghosts on sight and no players
            } else if (!phantomsOnSight.isEmpty()) {

                //No active close ones
                if (!contactMap.containsValue(StuffInContact.PHANTOM)) {

                    //neutral close one
                    if (contactMap.containsValue(StuffInContact.NEUTRAL_PHANTOM)) {

                        //move towards it if safe
                        Hazard.Targettability target = calculateFreeDirectionToRun(contactMap);

                        if (target != null) {
                            return targettableToMoveInstruction(target);
                        } else {
                            return kebabCase(phantomsOnSight, playersOnSight);
                        }
                    } else {

                        //Shoot in the max points direction
                        return kebabCase(phantomsOnSight, playersOnSight);
                    }
                //1 active one close
                } else if (phantomsTouching.size() == 1 && !contactMap.get(phantomsTouching.keySet().stream().findFirst().get()).equals(StuffInContact.NEUTRAL_PHANTOM)) {

                    //shoot it
                    return targettableToShootInstruction(phantomsTouching.values().stream().findFirst().get());

                } else if (phantomsTouching.size() == 1 && contactMap.get(phantomsTouching.keySet().stream().findFirst().get()).equals(StuffInContact.NEUTRAL_PHANTOM)) {

                    return targettableToMoveInstruction(phantomsTouching.values().stream().findFirst().get());

                //2+ active in line
                } else if (canRunFromPhantoms(contactMap)) {

                    //move in opposite direction (best for points perhaps not active ghost?)
                    return targettableToShootInstruction(calculateFreeDirectionToRun(contactMap));

                //active phantom kebab - 4 ...
                } else {
                    //fuck it, shoot in the max points direction
                    return kebabCase(phantomsOnSight, playersOnSight);
                }
            }

        //calculate safe locations near and move towards one
        }

        Hazard.Targettability target = calculateFreeDirectionToRun(contactMap);

        if (target != null) {
            return targettableToMoveInstruction(target);
        }

        return null;
    }

    private HashMap<Hazard.Targettability, StuffInContact> fillContactMapWithLayout(LayoutEntity [][] board, Location playerLocation) {

        HashMap<Hazard.Targettability, StuffInContact> contactMap = new HashMap<>();

        contactMap.put(Hazard.Targettability.UP, board[playerLocation.getY()-1][playerLocation.getX()] == LayoutEntity.EMPTY ? StuffInContact.NOTHING : StuffInContact.WALL);
        contactMap.put(Hazard.Targettability.DOWN, board[playerLocation.getY()+1][playerLocation.getX()] == LayoutEntity.EMPTY ? StuffInContact.NOTHING : StuffInContact.WALL);
        contactMap.put(Hazard.Targettability.LEFT, board[playerLocation.getY()][playerLocation.getX()-1] == LayoutEntity.EMPTY ? StuffInContact.NOTHING : StuffInContact.WALL);
        contactMap.put(Hazard.Targettability.RIGHT, board[playerLocation.getY()][playerLocation.getX()+1] == LayoutEntity.EMPTY ? StuffInContact.NOTHING : StuffInContact.WALL);

        return contactMap;
    }

    private void fillContactMapWithEntities(Map<Hazard.Targettability, StuffInContact> contactMap, Map<Hazard, Hazard.Targettability> phantomsTouching, Map<Hazard, Hazard.Targettability> playersTouching) {

        if (!phantomsTouching.isEmpty()) {

            phantomsTouching.forEach((hazard, targettability) -> contactMap.put(targettability, hazard.isActive() ? StuffInContact.PHANTOM : StuffInContact.NEUTRAL_PHANTOM));
        }

        if (!playersTouching.isEmpty()) {

            playersTouching.forEach((hazard, targettability) -> contactMap.put(targettability, StuffInContact.PLAYER));
        }
    }

    private boolean playersInLine(Set<Hazard> playersInSight) {

        Set<Hazard.Targettability> targettabilities = playersInSight.stream().map(Hazard::getTargettable).collect(Collectors.toSet());

        if (targettabilities.contains(Hazard.Targettability.LEFT) || targettabilities.contains(Hazard.Targettability.RIGHT)) {

            return !(targettabilities.contains(Hazard.Targettability.UP) || targettabilities.contains(Hazard.Targettability.DOWN));
        }

        return true;
    }

    private boolean canRunFromPhantoms(Map<Hazard.Targettability, StuffInContact> contactMap) {

        return contactMap.entrySet().stream().anyMatch(entry -> entry.getValue().equals(StuffInContact.NOTHING) || entry.getValue().equals(StuffInContact.NEUTRAL_PHANTOM));
    }

    private Hazard.Targettability calculateFreeDirectionToRun(Map<Hazard.Targettability, StuffInContact> contactMap) {

        Set<Hazard.Targettability> neutralPhantom = contactMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(StuffInContact.NEUTRAL_PHANTOM))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        if (!neutralPhantom.isEmpty()) {
            return neutralPhantom.stream().findFirst().orElse(null);
        }

        Set<Hazard.Targettability> movableDirections = contactMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(StuffInContact.NOTHING))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        ArrayList<Hazard.Targettability> moveList = new ArrayList<>(movableDirections);
        Collections.shuffle(moveList);

        return Optional.ofNullable(moveList.get(0)).orElse(null);
    }

    private OutputInstructionDTO kebabCase(Set<Hazard> visiblePhantom, Set<Hazard> visiblePlayers) {

        Map<Hazard.Targettability, Long> pointPerDirection = new HashMap<>();
        //discover most valuable shot
        pointPerDirection.put(Hazard.Targettability.LEFT,
                        visiblePhantom.stream()
                            .filter(hazard -> hazard.getTargettable().equals(Hazard.Targettability.LEFT))
                            .count() * 50 +
                        visiblePlayers.stream()
                            .filter(hazard -> hazard.getTargettable().equals(Hazard.Targettability.LEFT))
                            .count() * 100);

        pointPerDirection.put(Hazard.Targettability.RIGHT,
                        visiblePhantom.stream()
                            .filter(hazard -> hazard.getTargettable().equals(Hazard.Targettability.RIGHT))
                            .count() * 50 +
                        visiblePlayers.stream()
                            .filter(hazard -> hazard.getTargettable().equals(Hazard.Targettability.RIGHT))
                            .count() * 100);

        pointPerDirection.put(Hazard.Targettability.UP,
                        visiblePhantom.stream()
                            .filter(hazard -> hazard.getTargettable().equals(Hazard.Targettability.UP))
                            .count() * 50 +
                        visiblePlayers.stream()
                            .filter(hazard -> hazard.getTargettable().equals(Hazard.Targettability.UP))
                            .count() * 100);

        pointPerDirection.put(Hazard.Targettability.DOWN,
                        visiblePhantom.stream()
                            .filter(hazard -> hazard.getTargettable().equals(Hazard.Targettability.DOWN))
                            .count() * 50 +
                        visiblePlayers.stream()
                            .filter(hazard -> hazard.getTargettable().equals(Hazard.Targettability.DOWN))
                            .count() * 100);

        //actual shot
        return targettableToShootInstruction(Collections.max(pointPerDirection.entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getKey());
    }

    private OutputInstructionDTO targettableToMoveInstruction(Hazard.Targettability targettability) {

        switch (targettability) {

            case UP:
                return OutputInstructionDTO.UP;
            case DOWN:
                return OutputInstructionDTO.DOWN;
            case LEFT:
                return OutputInstructionDTO.LEFT;
            case RIGHT:
                return OutputInstructionDTO.RIGHT;
            case NOPE:
                return OutputInstructionDTO.UP;
        }

        return null; //unreachable
    }

    private OutputInstructionDTO targettableToShootInstruction(Hazard.Targettability targettability) {

        switch (targettability) {

            case UP:
                return OutputInstructionDTO.F_UP;
            case DOWN:
                return OutputInstructionDTO.F_DOWN;
            case LEFT:
                return OutputInstructionDTO.F_LEFT;
            case RIGHT:
                return OutputInstructionDTO.F_RIGHT;
            case NOPE:
                return OutputInstructionDTO.F_UP;
        }

        return null; //unreachable
    }
}
