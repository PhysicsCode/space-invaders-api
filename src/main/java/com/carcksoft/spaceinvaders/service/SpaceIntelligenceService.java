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
    private final HackBean hackBean;
    private final HackBeanUpdater hackBeanUpdater;

    public OutputInstructionDTO calculateNextStep(SpaceInvadersInputDTO input) {

        //Load game from id or create new one
        Game game = gameService.loadGameInformation(input);

        //Update all structure parameters
        game = gameService.updateLayoutKnowledge(input, game);

        //Update HackBean

        hackBeanUpdater.updateHackBean(hackBean, game, input.getPlayerDTO().getCanFire());
        //UseHackBean if activated

        if (hackBean.isActive()) {

            try {
                Thread.sleep(800);
                //Retrieve instruction
                return hackBean.getInstruction();
            } catch (InterruptedException e) {
                return strategyService.defineMoveByGame(game, input.getPlayerDTO().getCanFire());
            }

        } else {

            //Else, move with AI - Calculate instruction by algorithm
            return strategyService.defineMoveByGame(game, input.getPlayerDTO().getCanFire());
        }
    }
}
