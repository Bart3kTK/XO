package com.game.xo.errors;

public class IllegalGameException extends Exception{

    private final String message;

    public IllegalGameException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
}