package com.github.l524l.telegramnekobot.handlers.impl;

import com.github.l524l.telegramnekobot.handlers.UpdateHandler;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NewMessageUpdateHandler extends UpdateHandler {
    @Override
    public String handleRequest(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

        }
        return handleByNextHandler(update);
    }
}
