package com.github.l524l.telegramnekobot.exceptions;

public class ValidationException extends BotException {

    public ValidationException(String message, Throwable e){
        super(message, e);
    }
    public ValidationException(String message){
        super(message);
    }
}
