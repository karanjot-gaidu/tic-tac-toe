// This code defines a simple data transfer object (DTO) called ConnectRequest.
package com.example.TicTacToe.Controller.dto;

import com.example.TicTacToe.game.Player;
import lombok.Data;

@Data
public class ConnectRequest {
    // create private instance variables: player of type `Player` and gameId of type `String`
    private Player player;
    private String gameId;
}