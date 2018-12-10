package com.carcksoft.spaceinvaders.service;

import com.carcksoft.spaceinvaders.domain.VisibleArea;
import com.carcksoft.spaceinvaders.repository.GameRepository;
import com.carcksoft.spaceinvaders.domain.Game;
import com.carcksoft.spaceinvaders.domain.Location;
import com.carcksoft.spaceinvaders.domain.constants.LayoutEntity;
import com.carcksoft.spaceinvaders.dto.SpaceInvadersInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Game loadGameInformation(SpaceInvadersInputDTO input) {

        return gameRepository.retrieveGame(input.getGameDTO().getGameId(), input.getBoardDTO().getSize());
    }

    private void saveGameStatus(Game game) {

        gameRepository.saveGame(game);
    }

    public Game updateLayoutKnowledge(SpaceInvadersInputDTO input, Game game) {

        addWallStructures(game, input.getBoardDTO());
        fillEmptyGaps(game, input.getPlayerDTO().getVisibleArea());
        relocateCharacter(game, input.getPlayerDTO().getPosition());
        updateHazards(game, input);
        saveGameStatus(game);
        return game;
    }

    private void relocateCharacter(Game game, Location now) {

        game.setPlayerLocation(now);
    }

    private void addWallStructures(Game game, SpaceInvadersInputDTO.BoardDTO boardDTO) {

        boardDTO.getWallsLocation().forEach(game::upsertWall);
    }

    private void fillEmptyGaps(Game game, VisibleArea visibleArea) {
        game.fillBoardVoids(visibleArea);
    }

    private void updateHazards(Game game, SpaceInvadersInputDTO input) {

        game.destroyHazardEntities();
        game.relocatePhantom(input.getInvadersLocation());
        game.relocatePlayers(input.getPlayersLocation());
    }
}
