package com.github.l524l.telegramnekobot.exceptions;

public class BotException extends Exception {

    public BotException(){
        super("Some exception");
    }

    public BotException(String message, Throwable e){
        super(message, e);
    }
    public BotException(String message){
        super(message);
    }
}
