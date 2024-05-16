package com.game.xo.model;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game {

    @Id
    private String gameId;

    @ManyToOne
    @JoinColumn
    private Player player1;

    @ManyToOne
    @JoinColumn
    private Player player2;

    @Enumerated(EnumType.ORDINAL)
    private GameStatus status;

    @ElementCollection
    @CollectionTable(name = "game_board", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "cell_value")
    private List<Integer> gameBoard;

    @Enumerated(EnumType.ORDINAL)
    private XOEnum winner;


    
}
