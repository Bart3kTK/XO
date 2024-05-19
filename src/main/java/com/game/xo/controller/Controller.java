package com.game.xo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.xo.data.GameRepo;
import com.game.xo.data.PlayerRepo;
import com.game.xo.errors.IllegalGameException;
import com.game.xo.errors.IllegalParmException;
import com.game.xo.errors.NotFoundException;
import com.game.xo.model.ConnectionRequest;
import com.game.xo.model.Game;
import com.game.xo.model.GamePlay;
import com.game.xo.model.GameStatus;
import com.game.xo.model.Player;
import com.game.xo.model.XOEnum;
import com.game.xo.service.GameService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@Slf4j
@RequestMapping("/XO")
// @AllArgsConstructor
public class Controller {

    PlayerRepo playerRepo;
    GameRepo gameRepo;
    GameService gameService;

    public Controller(PlayerRepo playerRepo, GameRepo gameRepo, GameService gameService) {
        this.playerRepo = playerRepo;
        this.gameRepo = gameRepo;
        this.gameService = gameService;
        System.out.println("Controller created!");

    }
    
    @GetMapping("/players/{id}")
    public String getPlayer(@PathVariable String id) {
        return "Player " + id;
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        System.out.println("Add player");
        return player;
    }

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

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody Player player) {
        log.info("Start request: {}", player);
        return ResponseEntity.ok(gameService.createGame(player));
    }
    
    @PostMapping("/connect")
    public ResponseEntity<Game> connect(@RequestBody ConnectionRequest connectionRequest) 
                                        throws IllegalParmException, IllegalGameException {
        log.info("Connection request: {}", connectionRequest);
        return ResponseEntity.ok(gameService.joinGame(connectionRequest.getPlayer(),
                                    connectionRequest.getGameid()));
    }

    @PostMapping("/gameplay")
    public ResponseEntity<Game> gameplay(@RequestBody GamePlay gamePlay) 
                                        throws NotFoundException, IllegalGameException {
        log.info("Gameplay request: {}", gamePlay);
        Game game = gameService.playGame(gamePlay);


        return ResponseEntity.ok(game);
    }
    
    


    
    
}
