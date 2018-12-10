package com.carcksoft.spaceinvaders.service;

import com.carcksoft.spaceinvaders.domain.Game;
import com.carcksoft.spaceinvaders.domain.Hazard;
import com.carcksoft.spaceinvaders.domain.constants.Strategy;
import com.carcksoft.spaceinvaders.dto.OutputInstructionDTO;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StrategyService {
    public OutputInstructionDTO defineMoveByGame(Game game, boolean mayIShoot) {

        //Player on sight
        Set<Hazard> playersOnSight = game.getEnemyPlayerSet().stream()
                                            .filter(hazard -> !hazard.getTargettable().equals(Hazard.Targettability.NOPE))
                                            .collect(Collectors.toSet());

        Set<Hazard> phantomsOnSight = game.getPhantomSet().stream()
                                            .filter(hazard -> !hazard.getTargettable().equals(Hazard.Targettability.NOPE))
                                            .collect(Collectors.toSet());

        Set<Hazard> phantomsTouching = phantomsOnSight.stream()
                                            .filter(hazard -> hazard.getDistanceToPlayer() == 1)
                                            .collect(Collectors.toSet());

        //TODO implement the logic

        //I can shoot
            //players on sight
                // only 1
                    //shoot him
                // more than 1 in line
                    //phantom or wall blocking 1 direction
                        //move the other
                    //everything blocked
                        //fuck it, shoot in the max points direction
                // more than 1 not in line
                    //fuck it, shoot in the max points direction
            //ghosts on sight and no players
                //No active close ones
                    //neutral close one
                        //move towards it if safe
                    //Shoot in the max points direction
                //1 active one close
                    //shoot it
                //2-3 active in line
                    //move in opposite direction (best for points perhaps not active ghost?)
                //active phantom kebab
                    //fuck it, shoot in the max points direction

        //calculate safe locations near and move towards one

        return OutputInstructionDTO.UP;
    }

    public OutputInstructionDTO processDangers(Game game) {

        return OutputInstructionDTO.UP;
    }

    public OutputInstructionDTO achieveProfit(Game game) {

        return OutputInstructionDTO.F_UP;
    }

    public OutputInstructionDTO moveTowardsProfit(Game game) {

        return OutputInstructionDTO.LEFT;
    }

    public OutputInstructionDTO returnDefaultMove(Game game) {


        return OutputInstructionDTO.RIGHT;
    }
}
