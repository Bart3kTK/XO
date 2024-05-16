package com.game.xo.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.game.xo.model.Game;

public interface IGameReposytory extends CrudRepository<Game, String>{

}
