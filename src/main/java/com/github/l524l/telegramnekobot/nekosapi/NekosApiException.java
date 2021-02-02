package com.github.l524l.telegramnekobot.nekosapi;

import com.github.l524l.telegramnekobot.exceptions.BotException;

public class NekosApiException extends BotException {
    public NekosApiException(String message, int code ,Throwable e) {
        super(message, code, e);
    }
    public NekosApiException(String message, int code){
        super(message, code);
    }
}
