package com.daddyrusher.petclinic.exception;

public class EntityException extends RuntimeException {
    public EntityException() {
    }

    public EntityException(String message) {
        super(message);
    }

    public EntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
