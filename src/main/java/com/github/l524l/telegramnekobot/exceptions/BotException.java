package com.github.l524l.telegramnekobot.exceptions;

public class BotException extends Exception {
    private final int code;
    public BotException(String message, int code, Throwable e){
        super(message, e);
        this.code = code;
    }
    public BotException(String message, int code){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
