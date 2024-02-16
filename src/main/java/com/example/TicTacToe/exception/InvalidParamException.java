package com.example.TicTacToe.exception;

// Define the InvalidParamException class, a subclass of the Exception class
public class InvalidParamException extends Exception {

    // instance variable for the error message associated with the exception
    private String message;

    // constructor for the InvalidParamException class that takes a message as a parameter
    public InvalidParamException(String message) {
        this.message = message;
    }

    // getter method for the error message instance variable
    public String getMessage() {
        return message;
    }
}
