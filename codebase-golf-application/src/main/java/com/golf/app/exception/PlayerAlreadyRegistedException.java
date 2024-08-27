package com.golf.app.exception;

/**
 * This class is a custom PlayerAlreadyRegistedException exception class that extends RuntimeException.
 */
public class PlayerAlreadyRegistedException extends RuntimeException {

    public PlayerAlreadyRegistedException(String message) {
        super(message);
    }
}
