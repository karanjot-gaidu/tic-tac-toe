package com.example.TicTacToe.game;

/**
 * The GameStatus enum represents the possible states of a Tic Tac Toe game.
 *
 * NEW: The game has just been created and is waiting for a player to join.
 * IN_PROGRESS: The game is currently being played by two players.
 * FINISHED: The game has ended and a winner or a draw has been determined.
 */
public enum GameStatus {
    NEW, IN_PROGRESS, FINISHED
}
