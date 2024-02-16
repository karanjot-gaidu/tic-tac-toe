package com.example.TicTacToe.game;

import lombok.Data;

// Represents a game of Tic-Tac-Toe, with instance variables for players, status, board, and winner.
@Data
public class Game {

    private String gameId;
    private Player player1;
    private Player player2;
    private GameStatus status;
    private int[][] board;
    private TicToe winner;

}
