package com.game.xo.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

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
        return game;

    }   
    
}
