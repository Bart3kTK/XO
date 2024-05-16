package com.game.xo.data;

import org.springframework.beans.factory.annotation.Autowired;

import com.game.xo.interfaces.IGameReposytory;
import com.game.xo.model.Game;

public class GameRepo {

    @Autowired
    private static IGameReposytory gameReposytory;

    private static GameRepo instance;

    public static synchronized GameRepo getInstance() {
        if (instance == null) {
            instance = new GameRepo();
        }
        return instance;
    }

    public Iterable<Game> getGames() {
        return gameReposytory.findAll();
    } 

    public void saveGame(Game game) {
        gameReposytory.save(game);
    }

    public boolean containsGame(String gameId) {
        return gameReposytory.existsById(gameId);
    }

    public Game getGame(String gameId) {
        return gameReposytory.findById(gameId).get();
    }

    public void deleteGame(String gameId) {
        gameReposytory.deleteById(gameId);
    }

    public void updateGame(Game game) {
        deleteGame(game.getGameId());
        saveGame(game);
    }

}
