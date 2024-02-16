package com.example.TicTacToe.exception;

// define the NotFoundException class, a subclass of the Exception class
public class NotFoundException extends Exception {
    // instance variable for the error message associated with the exception
    private String message;
    // constructor for the "NotFoundException" class that takes a message as a parameter
    public NotFoundException(String message) {
        this.message = message;
    }
    // getter method for the error message instance variable
    public String getMessage() {
        return message;
    }
}
