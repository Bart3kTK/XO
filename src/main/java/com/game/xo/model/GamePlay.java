package com.game.xo.model;

import lombok.Data;

@Data
public class GamePlay {
    private XOEnum type;
    private String gameId;
    private int x;
    private int y;
    
}
