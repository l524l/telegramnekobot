package com.github.l524l.telegramnekobot.handlers.impl;

import com.github.l524l.telegramnekobot.handlers.UpdateHandler;
import com.github.l524l.telegramnekobot.observers.NewCallbackObserver;
import com.github.l524l.telegramnekobot.observers.NewCallbackSubject;
import com.github.l524l.telegramnekobot.observers.NewMessageObserver;
import com.github.l524l.telegramnekobot.repositories.UserRepository;
import com.github.l524l.telegramnekobot.user.BotUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
public class CallbackQuireUpdateHandler extends UpdateHandler implements NewCallbackSubject {

    private final List<NewCallbackObserver> observers;
    private final UserRepository userRepository;

    public CallbackQuireUpdateHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
        observers = new ArrayList<>();
    }

    @Override
    public String handleRequest(Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callback = update.getCallbackQuery();

            BotUser currentUser = userRepository.findById(callback.getFrom().getId()).orElseThrow(RuntimeException::new);
            notifyObservers(currentUser, callback);
        }
        return handleByNextHandler(update);
    }

    @Override
    public void registerObserver(NewCallbackObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(NewMessageObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(BotUser sender, CallbackQuery callback) {
        if (observers.isEmpty()) return;

        observers.forEach((x)-> x.onNewCallback(sender, callback));
    }
}
