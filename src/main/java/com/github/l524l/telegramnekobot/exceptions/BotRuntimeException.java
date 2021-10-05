package com.github.l524l.telegramnekobot.exceptions;

public class BotRuntimeException extends RuntimeException {

    public BotRuntimeException(){
        super("Some exception");
    }

    public BotRuntimeException(String message, Throwable e){
        super(message, e);
    }
    public BotRuntimeException(String message){
        super(message);
    }
}
