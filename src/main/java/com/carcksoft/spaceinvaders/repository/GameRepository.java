package com.carcksoft.spaceinvaders.repository;

import com.carcksoft.spaceinvaders.domain.Game;
import com.carcksoft.spaceinvaders.domain.Sizing;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class GameRepository{

    private static final Integer MAX_ENTRIES = 100;

    private Map<String, Game> gameMap = new LinkedHashMap<String, Game>(MAX_ENTRIES,0.7f, true){

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() < MAX_ENTRIES;
        }
    };

    public Game retrieveGame(String id, Sizing sizing) {

        return Optional.of(gameMap.get(id)).orElseGet(() -> {
            Game game =  new Game(id, sizing);
            gameMap.put(id, game);
            return game;
        });
    }

    public void saveGame(Game game) {

        gameMap.replace(game.getGameId(), game);
    }
}
