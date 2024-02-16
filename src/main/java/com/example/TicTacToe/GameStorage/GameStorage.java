// this class represents a storage mechanism for the Tic Tac Toe games
package com.example.TicTacToe.GameStorage;

import com.example.TicTacToe.game.Game;

import java.util.HashMap;
import java.util.Map;

public class GameStorage {
    // map that stores all the games with their unique IDs as the keys
    private static Map<String, Game> games;
    private static GameStorage instance;

    // constructor that initializes the games map
    private GameStorage() {
        games = new HashMap<>();
    }

    // method to get the singleton instance of GameStorage class
    public static synchronized GameStorage getInstance() {
        if (instance == null) {
            instance = new GameStorage();
        }
        return instance;
    }

    // method to retrieve the map of games
    public Map<String, Game> getGames() {
        return games;
    }

    // method to set a game in the games map
    public void setGame(Game game) {
        games.put(game.getGameId(), game);
    }
}

