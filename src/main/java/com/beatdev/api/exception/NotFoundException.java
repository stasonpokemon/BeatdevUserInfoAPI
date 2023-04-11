package com.beatdev.api.exception;

/**
 * Exception for NotFound actions.
 */
public class NotFoundException extends RuntimeException {

    public <T, S> NotFoundException(Class<T> classA, S id) {
        super(String.format("Not found %s with id: %s", classA.getSimpleName(), id));
    }

}
