package com.example.TicTacToe.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

// this represents the two players in the game
@AllArgsConstructor
@Getter
public enum TicToe {
    // X player is represented by the value 1
    X(1),
    // O player is represented by the value 2
    O(2);

    // value of the player (1 or 2)
    private Integer value;
}
