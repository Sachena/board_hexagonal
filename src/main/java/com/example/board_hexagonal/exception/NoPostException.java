package com.example.board_hexagonal.exception;

public class NoPostException extends RuntimeException{

    public NoPostException() {
        super();
    }

    public NoPostException(String message) {
        super(message);
    }

    public NoPostException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPostException(Throwable cause) {
        super(cause);
    }

    protected NoPostException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
