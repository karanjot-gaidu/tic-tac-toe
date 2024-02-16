package com.example.TicTacToe.service;
import com.example.TicTacToe.GameStorage.GameStorage;
import com.example.TicTacToe.exception.InvalidGameException;
import com.example.TicTacToe.exception.InvalidParamException;
import com.example.TicTacToe.exception.NotFoundException;
import com.example.TicTacToe.game.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.TicTacToe.game.GameStatus.*;

// this class provides methods to create and play games of Tic-Tac-Toe
@Service
@AllArgsConstructor
public class GameService {
    /**
     * Creates a new game with the provided player and initializes its properties.
     *
     * @param player The first player who creates the game.
     * @return The newly created game object.
     */
    public Game createGame(Player player) {
        // creates a new game object with an empty 3x3 board, unique id, player 1 and 'NEW' status
        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setStatus(NEW);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    /**
     * Connects the provided player to an existing game with the given game ID.
     *
     * @param player2 The second player who wants to join the game.
     * @param gameId The unique ID of the game to join.
     * @return The updated game object after the player has joined.
     * @throws InvalidParamException If the provided game ID does not exist.
     * @throws InvalidGameException If the game is already full (player 2 has already joined).
     */
    public Game connectToGame(Player player2, String gameId) throws InvalidParamException, InvalidGameException {
        // checks if the game with provided ID exists in the game storage
        if (!GameStorage.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidParamException("Game with provided id doesn't exist");
        }
        Game game = GameStorage.getInstance().getGames().get(gameId);

        // throws exception if the game already has a player 2 and cannot accept any more players
        if (game.getPlayer2() != null) {
            throw new InvalidGameException("Game is not valid anymore");
        }

        // adds the provided player as player 2 and sets game status to 'IN_PROGRESS'
        game.setPlayer2(player2);
        game.setStatus(IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    /**
     * Connects the provided player to an existing game with 'NEW' status randomly.
     *
     * @param player2 The second player who wants to join a random game.
     * @return The updated game object after the player has joined.
     * @throws NotFoundException If no games with 'NEW' status exist in the game storage.
     */
    public Game connectToRandomGame(Player player2) throws NotFoundException {
        // finds the first game with 'NEW' status in the game storage and adds the provided player as player 2
        Game game = GameStorage.getInstance().getGames().values().stream()
                .filter(it -> it.getStatus().equals(NEW))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Game not found"));
        game.setPlayer2(player2);
        game.setStatus(IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    /**
     * This method is used to update the game board with the latest move and check if there is a winner.
     * If a winner is found, the game status is updated with the winner and returned.
     *
     * @param gamePlay The latest move made by the player.
     * @return The updated game object.
     * @throws NotFoundException     if the game with the provided id is not found.
     * @throws InvalidGameException if the game is already finished.
     */
    public Game gamePlay(GamePlay gamePlay) throws NotFoundException, InvalidGameException {
        // check if the game with the provided id exists
        if (!GameStorage.getInstance().getGames().containsKey(gamePlay.getGameId())) {
            throw new NotFoundException("Game not found");
        }
        // get the game object
        Game game = GameStorage.getInstance().getGames().get(gamePlay.getGameId());
        // check if the game is already finished
        if (game.getStatus().equals(FINISHED)) {
            throw new InvalidGameException("Game is already finished");
        }
        // update the game board with the latest move
        int[][] board = game.getBoard();
        board[gamePlay.getCoordinateX()][gamePlay.getCoordinateY()] = gamePlay.getType().getValue();

        // check if there is a winner
        boolean xWinner = checkWinner(game.getBoard(), TicToe.X);
        boolean oWinner = checkWinner(game.getBoard(), TicToe.O);

        // update the game status with the winner
        if (xWinner) {
            game.setWinner(TicToe.X);
        } else if (oWinner) {
            game.setWinner(TicToe.O);
        }
        // save the updated game object in the storage
        GameStorage.getInstance().setGame(game);
        return game;
    }

    /**
     * This method checks if there is a winner on the game board for the given tic toe type.
     *
     * @param board   The game board.
     * @param ticToe The tic toe type to check for the winner.
     * @return True if there is a winner, false otherwise.
     */
    private boolean checkWinner(int[][] board, TicToe ticToe) {
        // list of all possible win combinations
        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };
        // iterate through all possible win combinations
        for (int[] combination : winCombinations) {
            boolean hasWon = true;
            // check if the tic toe type has all the required positions in the combination
            for (int i = 0; i < combination.length; i++) {
                int x = combination[i] / board.length;
                int y = combination[i] % board.length;
                if (board[x][y] != ticToe.getValue()) {
                    hasWon = false;
                    break;
                }
            }
            if (hasWon) {
                return true;
            }
        }
        return false;
    }
}