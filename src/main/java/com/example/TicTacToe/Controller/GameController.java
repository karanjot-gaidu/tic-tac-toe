package com.example.TicTacToe.Controller;

import com.example.TicTacToe.Controller.dto.ConnectRequest;
import com.example.TicTacToe.exception.InvalidGameException;
import com.example.TicTacToe.exception.InvalidParamException;
import com.example.TicTacToe.exception.NotFoundException;
import com.example.TicTacToe.game.Game;
import com.example.TicTacToe.game.GamePlay;
import com.example.TicTacToe.game.Player;
import com.example.TicTacToe.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
// Define the GameController class
public class GameController {
    // instance variables for the game service and messaging template
    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    // Handle POST requests to start a new game
    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody Player player) {
        log.info("start game request: {}", player);
        // use the game service to create a new game and return the game as a response
        return ResponseEntity.ok(gameService.createGame(player));
    }

    // Handle POST requests to connect to an existing game
    @PostMapping("/connect")
    public ResponseEntity<Game> connect(@RequestBody ConnectRequest request) throws InvalidParamException, InvalidGameException {
        log.info("connect request: {}", request);
        // Use the game service to connect to an existing game and return the game as a response
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayer(), request.getGameId()));
    }

    // Handle POST requests to connect to a random game
    @PostMapping("/connect/random")
    public ResponseEntity<Game> connectRandom(@RequestBody Player player) throws NotFoundException {
        log.info("connect random {}", player);
        // use the game service to connect to a random game and return the game as a response
        return ResponseEntity.ok(gameService.connectToRandomGame(player));
    }

    // Handle POST requests for gameplay actions
    @PostMapping("/gameplay")
    public ResponseEntity<Game> gamePlay(@RequestBody GamePlay request) throws NotFoundException, InvalidGameException {
        log.info("gameplay: {}", request);
        // use the game service to perform the requested gameplay action
        Game game = gameService.gamePlay(request);
        // send an update to clients subscribed to the game progress topic
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getGameId(), game);
        // return the updated game as a response
        return ResponseEntity.ok(game);
    }
}
