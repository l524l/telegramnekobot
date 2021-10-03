package com.github.l524l.telegramnekobot.handlers.impl;

import com.github.l524l.telegramnekobot.handlers.UpdateHandler;
import com.github.l524l.telegramnekobot.repositories.UserRepository;
import com.github.l524l.telegramnekobot.user.BotUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class UserUpdateHandler extends UpdateHandler {

    private final UserRepository repository;

    public UserUpdateHandler(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String handleRequest(Update update) {
        if (update.hasMessage()) {
            User user = update.getMessage().getFrom();
            int userId = user.getId();

            if (!repository.existsById(userId)) {
                BotUser newBotUser = new BotUser(userId, user.getFirstName(), user.getLastName(), user.getUserName());
                repository.save(newBotUser);
            }
        }
        return handleByNextHandler(update);
    }
}
