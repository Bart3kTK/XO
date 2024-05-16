package com.game.xo.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.game.xo.model.Player;

public interface IPlayerReposytory extends CrudRepository<Player, String>{
    
}
