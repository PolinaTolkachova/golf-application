package com.golf.app.exception;

/**
 * This class is a custom PlayerNotFoundException exception class that extends RuntimeException.
 */
public class CompetitionNotFoundException extends RuntimeException {

    public CompetitionNotFoundException(String message) {
        super(message);
    }
}
