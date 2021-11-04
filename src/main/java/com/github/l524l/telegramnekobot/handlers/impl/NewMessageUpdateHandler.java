package com.github.l524l.telegramnekobot.handlers.impl;

import com.github.l524l.telegramnekobot.handlers.RequestContext;
import com.github.l524l.telegramnekobot.handlers.UpdateHandler;
import com.github.l524l.telegramnekobot.observers.NewMessageObserver;
import com.github.l524l.telegramnekobot.observers.NewMessageSubject;
import com.github.l524l.telegramnekobot.repositories.UserRepository;
import com.github.l524l.telegramnekobot.user.BotUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewMessageUpdateHandler extends UpdateHandler implements NewMessageSubject {

    private final List<NewMessageObserver> observers;
    private final UserRepository userRepository;
    private final RequestContext context;

    public NewMessageUpdateHandler(UserRepository userRepository, RequestContext context) {
        this.userRepository = userRepository;
        this.context = context;
        this.observers = new ArrayList<>();
    }

    @Override
    public String handleRequest(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            notifyObservers(context.getBotUser(), message);
        }
        return handleByNextHandler(update);
    }

    @Override
    public void registerObserver(NewMessageObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(NewMessageObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(BotUser sender, Message message) {
        if (observers.isEmpty()) return;

        observers.forEach((x)-> x.onNewMessage(sender, message));
    }
}
