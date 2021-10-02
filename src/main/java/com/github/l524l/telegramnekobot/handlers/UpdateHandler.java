package com.github.l524l.telegramnekobot.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class UpdateHandler {
    private UpdateHandler nextHandler;

    public abstract String handleRequest(Update update);

    protected String handleByNextHandler(Update update){
        if (nextHandler != null)
            return nextHandler.handleRequest(update);
        else
            return "ok";
    }

    public void setNextHandler(UpdateHandler handler){
        nextHandler = handler;
    }
}
