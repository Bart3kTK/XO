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

    private String gameBoard;

    @Enumerated(EnumType.ORDINAL)
    private XOEnum winner;


    public int[] getGameBoard() {
        String[] board = gameBoard.split(",");
        int[] result = new int[9];
        for (int i = 0; i < 9; i++) {
            result[i] = Integer.parseInt(board[i]);
        }
        return result;
    }

    public void setGameBoard(int[] gameBoard) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            sb.append(gameBoard[i]);
            if (i < 8) {
                sb.append(",");
            }
        }
        this.gameBoard = sb.toString();
    }
    
}
