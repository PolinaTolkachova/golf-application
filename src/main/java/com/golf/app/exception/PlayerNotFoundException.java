package com.golf.app.exception;

/**
 * This class is a custom PlayerNotFoundException exception class that extends RuntimeException.
 */
public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(String message) {
        super(message);
    }
}
