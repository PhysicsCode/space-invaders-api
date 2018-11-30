package com.carcksoft.spaceinvaders.service;

import com.carcksoft.spaceinvaders.domain.Game;
import com.carcksoft.spaceinvaders.domain.constants.Strategy;
import com.carcksoft.spaceinvaders.dto.OutputInstructionDTO;
import org.springframework.stereotype.Service;

@Service
public class StrategyService {
    public Strategy defineStepStrategy(Game game) {

        return Strategy.DANGER;
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
