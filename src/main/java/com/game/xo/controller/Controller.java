package com.game.xo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.xo.data.GameRepo;
import com.game.xo.data.PlayerRepo;
import com.game.xo.model.Game;
import com.game.xo.model.GameStatus;
import com.game.xo.model.Player;
import com.game.xo.model.XOEnum;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/XO")
public class Controller {

    PlayerRepo playerRepo;
    GameRepo gameRepo;

    public Controller(PlayerRepo playerRepo, GameRepo gameRepo) {
        this.playerRepo = playerRepo;
        this.gameRepo = gameRepo;
        System.out.println("Controller created!");
        playerRepo.addPlayer(new Player("Player1", "elelo"));
        playerRepo.addPlayer(new Player("Player2", "elelo"));
        gameRepo.saveGame(new Game("Game1", playerRepo.getPlayers().iterator().next(), playerRepo.getPlayers().iterator().next(), GameStatus.IN_PROGRESS,List.of(1,2,3,4,5) , XOEnum.X));


    }
    // @GetMapping("/players/{id}")
    // public String getPlayer(@PathVariable String id) {
    //     return "Player " + id;
    // }

    @GetMapping("/players")
    public Iterable<Player> getPlayer() {
        System.out.println("Get players");
        return playerRepo.getPlayers();
    }

    @GetMapping("/games")
    public Iterable<Game> getGames() {
        System.out.println("Get games");
        return gameRepo.getGames();
    }
    
    
}
