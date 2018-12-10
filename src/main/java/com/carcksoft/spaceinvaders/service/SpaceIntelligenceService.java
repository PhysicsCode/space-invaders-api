package com.carcksoft.spaceinvaders.service;

import com.carcksoft.spaceinvaders.domain.Game;
import com.carcksoft.spaceinvaders.dto.OutputInstructionDTO;
import com.carcksoft.spaceinvaders.dto.SpaceInvadersInputDTO;
import com.carcksoft.spaceinvaders.domain.constants.Strategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceIntelligenceService {

    private final GameService gameService;
    private final StrategyService strategyService;

    public OutputInstructionDTO calculateNextStep(SpaceInvadersInputDTO input) {

        //Load game from id or create new one
        Game game = gameService.loadGameInformation(input);

        //Update all structure parameters
        game = gameService.updateLayoutKnowledge(input, game);

        //Calculate dangerousness/profitability of the position
        return strategyService.defineMoveByGame(game, input.getPlayerDTO().getCanFire());
    }
}
