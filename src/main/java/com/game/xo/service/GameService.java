package com.game.xo.service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.xo.data.GameRepo;
import com.game.xo.data.PlayerRepo;
import com.game.xo.errors.IllegalGameException;
import com.game.xo.errors.IllegalParmException;
import com.game.xo.errors.NotFoundException;

import com.game.xo.model.Game;
import com.game.xo.model.GamePlay;
import com.game.xo.model.GameStatus;
import com.game.xo.model.Player;
import com.game.xo.model.XOEnum;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameService {

    GameRepo gameRepo;
    PlayerRepo playerRepo;

    public Game createGame(Player player1) {
        if (player1 == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        if(!playerRepo.containsPlayer(player1.getId()))
        {
            playerRepo.addPlayer(player1);
        }

        Game game = new Game();
        List<Integer> gameBoard = List.of(-1, -1, -1, -1, -1, -1, -1, -1, -1);
        game.setGameBoard(gameBoard);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player1);
        game.setStatus(GameStatus.WAITING_FOR_PLAYER);
        gameRepo.saveGame(game);
        return game;

    }   

    public Game joinGame(Player player2, String gameId) throws IllegalParmException, IllegalGameException
    {
        if(!gameRepo.containsGame(gameId))
        {
            throw new IllegalParmException("Game not found");
        }

        Game game = gameRepo.getGame(gameId);

        if(game.getPlayer2() != null)
        {
            throw new IllegalGameException("Game is full");
        }


        if(!playerRepo.containsPlayer(player2.getId()))
        {
            playerRepo.addPlayer(player2);
        }

        game.setPlayer2(player2);
        game.setStatus(GameStatus.IN_PROGRESS);
        gameRepo.updateGame(game);

        return game;
    }

    public Game playGame(GamePlay gamePlay) throws NotFoundException, IllegalGameException
    {
        if(!gameRepo.containsGame(gamePlay.getGameId()))
        {
            throw new NotFoundException("Game not found");
        }

        Game game = gameRepo.getGame(gamePlay.getGameId());

        if(game.getStatus() == GameStatus.FINISHED)
        {
            throw new IllegalGameException("Game is finished");
        }

        List<Integer> gameBoard = game.getGameBoard();
        gameBoard.set(gamePlay.getX() + gamePlay.getY() * 3, gamePlay.getType().ordinal());
        game.setGameBoard(gameBoard);
        checkWinner(gameBoard, XOEnum.X);
        checkWinner(gameBoard, XOEnum.O);

        gameRepo.updateGame(game);
        return game;
        
    }

    private boolean checkWinner(List<Integer> board, XOEnum en)
    {
        List<List<Integer>> winCombos = List.of(
            List.of(0, 1, 2),
            List.of(3, 4, 5),
            List.of(6, 7, 8),
            List.of(0, 3, 6),
            List.of(1, 4, 7),
            List.of(2, 5, 8),
            List.of(0, 4, 8),
            List.of(2, 4, 6)
        );

        for(List<Integer> combo : winCombos)
        {
            if( board.get(combo.get(0)) == en.ordinal() && 
                board.get(combo.get(1)) == en.ordinal() && 
                board.get(combo.get(2)) == en.ordinal())
            {
                return true;
            }
        }

        return false;
    }
    
}
