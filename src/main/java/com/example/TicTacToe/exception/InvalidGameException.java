package com.example.TicTacToe.exception;
// Define the InvalidGameException class, a subclass of the Exception class
public class InvalidGameException extends Exception {
    // instance variable for the error message associated with the exception
    private String message;
    // constructor for the InvalidGameException class that takes a message as a parameter
    public InvalidGameException(String message) {
        this.message = message;
    }
    // getter method for the error message instance variable
    public String getMessage() {
        return message;
    }
}