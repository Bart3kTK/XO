package com.game.xo.errors;

public class IllegalParmException extends Exception{
    private final String message;

    public IllegalParmException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
