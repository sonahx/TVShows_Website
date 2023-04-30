package com.TVShows.exceptions;

public class WrongOperationException extends RuntimeException{

    private String message;

    public WrongOperationException(String errorMessage) {
        super(errorMessage);
    }

    public WrongOperationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
