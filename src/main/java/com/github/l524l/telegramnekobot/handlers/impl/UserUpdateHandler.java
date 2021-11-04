package com.github.l524l.telegramnekobot.handlers.impl;

import com.github.l524l.telegramnekobot.handlers.RequestContext;
import com.github.l524l.telegramnekobot.handlers.UpdateHandler;
import com.github.l524l.telegramnekobot.repositories.UserRepository;
import com.github.l524l.telegramnekobot.user.BotUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class UserUpdateHandler extends UpdateHandler {

    private final UserRepository repository;
    private final RequestContext context;

    public UserUpdateHandler(UserRepository repository, RequestContext context) {
        this.repository = repository;
        this.context = context;
    }

    @Override
    public String handleRequest(Update update) {
        User user = null;

        if (update.hasMessage())
            user = update.getMessage().getFrom();
        if (update.hasInlineQuery())
            user =  update.getInlineQuery().getFrom();
        if (update.hasCallbackQuery())
            user =  update.getCallbackQuery().getFrom();

        if (user != null) {
            long userId = user.getId();

            if (!repository.existsById(userId)) {
                BotUser newBotUser = new BotUser(userId, user.getFirstName(), user.getLastName(), user.getUserName());
                context.setBotUser(repository.save(newBotUser));
            }

            context.setBotUser(repository.findById(userId).orElseThrow());
            context.setUpdate(update);
        }
        return handleByNextHandler(update);
    }
}
