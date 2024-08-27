package com.golf.app.exception;

/**
 * This class is a custom PlayerNotFoundException exception class that extends RuntimeException.
 */
public class CompetitionRoundNotFoundException extends RuntimeException {

    public CompetitionRoundNotFoundException(String message) {
        super(message);
    }
}
