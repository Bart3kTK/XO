package com.game.xo.data;
import org.springframework.stereotype.Component;
import com.game.xo.interfaces.IGameReposytory;
import com.game.xo.model.Game;

@Component
public class GameRepo {

    private final IGameReposytory gameReposytory;

    public GameRepo(IGameReposytory gameReposytory) {
        this.gameReposytory = gameReposytory;
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
