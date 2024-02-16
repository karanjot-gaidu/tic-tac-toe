package com.example.TicTacToe.game;

/**
 * This class represents a single game play, including the type of move made by the player (X or O),
 * and the coordinates on the game board where the move was made.
 */
import lombok.Data;

@Data
public class GamePlay {
    // the type of move made by the player
    private TicToe type;
    // the X-coordinate of the move on the game board
    private Integer coordinateX;
    // the Y-coordinate of the move on the game board
    private Integer coordinateY;
    // the ID of the game in which the move was made
    private String gameId;
}
