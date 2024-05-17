package com.game.xo.data;
import org.springframework.stereotype.Component;
import com.game.xo.interfaces.IPlayerReposytory;
import com.game.xo.model.Player;

@Component
public class PlayerRepo {
    
    private final IPlayerReposytory playerReposytory;

    public PlayerRepo(IPlayerReposytory playerReposytory) {
        this.playerReposytory = playerReposytory;
    }

    public Iterable<Player> getPlayers() {
        return playerReposytory.findAll();
    }

    public void addPlayer(Player player) {
        playerReposytory.save(player);
    }

    public boolean containsPlayer(String playerId) {
        return playerReposytory.existsById(playerId);
    }

    public Player getPlayer(String playerId) {
        return playerReposytory.findById(playerId).get();
    }

    public void deletePlayer(String playerId) {
        playerReposytory.deleteById(playerId);
    }

    public void updatePlayer(Player player) {
        deletePlayer(player.getId());
        addPlayer(player);
    }
}