package com.game.xo.errors;

public class IllegalGameExeption extends Exception{

    private final String message;

    public IllegalGameExeption(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
}