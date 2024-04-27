package com.tiagosilva.todo.exceptions;

public class UpdateStatusException extends RuntimeException{
    public UpdateStatusException(String message) {
        super(message);
    }
}
