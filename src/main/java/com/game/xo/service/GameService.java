package com.game.xo.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.game.xo.data.GameRepo;
import com.game.xo.errors.IllegalGameExeption;
import com.game.xo.errors.IllegalParmExeption;
import com.game.xo.model.Game;
import com.game.xo.model.GameStatus;
import com.game.xo.model.Player;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameService {

    public Game createGame(Player player1) {
        Game game = new Game();
        game.setGameBoard(new ArrayList<>());
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player1);
        game.setStatus(GameStatus.WAITING_FOR_PLAYER);
        GameRepo.getInstance().saveGame(game);
        return game;

    }   

    public Game joinGame(Player player2, String gameId) throws IllegalParmExeption, IllegalGameExeption
    {
        if(!GameRepo.getInstance().containsGame(gameId))
        {
            throw new IllegalParmExeption("Game not found");
        }

        Game game = GameRepo.getInstance().getGame(gameId);

        if(game.getPlayer2() != null)
        {
            throw new IllegalGameExeption("Game is full");
        }

        game.setPlayer2(player2);
        game.setStatus(GameStatus.IN_PROGRESS);
        GameRepo.getInstance().updateGame(game);

        return game;
        
    }
    
}
