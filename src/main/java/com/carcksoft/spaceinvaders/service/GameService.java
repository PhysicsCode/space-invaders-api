package com.carcksoft.spaceinvaders.service;

import com.carcksoft.spaceinvaders.domain.Game;
import com.carcksoft.spaceinvaders.dto.SpaceInvadersInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {


    public Game loadGameInformation(SpaceInvadersInputDTO input) {

        return new Game();
    }

    public Game updateLayoutKnowledge(SpaceInvadersInputDTO input, Game game) {

        return game;
    }
}
