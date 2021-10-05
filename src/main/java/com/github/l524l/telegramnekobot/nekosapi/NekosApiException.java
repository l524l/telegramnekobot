package com.github.l524l.telegramnekobot.nekosapi;

import com.github.l524l.telegramnekobot.exceptions.BotException;

public class NekosApiException extends BotException {
    public NekosApiException(String message, Throwable e) {
        super(message, e);
    }
    public NekosApiException(String message){
        super(message);
    }
}
