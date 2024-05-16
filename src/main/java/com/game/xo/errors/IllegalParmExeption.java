package com.game.xo.errors;

public class IllegalParmExeption extends Exception{
    private final String message;

    public IllegalParmExeption(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
