package com.TVShows.exceptions;

public class ShowAlreadyExistsException extends RuntimeException {
    public ShowAlreadyExistsException(String message) {
        super(message);
    }
}
